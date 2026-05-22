package ru.soft.paymentservice.interseptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingResponseWrapper;
import ru.soft.paymentservice.filter.ResponseCachingFilter;
import ru.soft.paymentservice.enums.IdempotencyKeyStatus;
import ru.soft.paymentservice.model.IdempotencyKey;
import ru.soft.paymentservice.service.IdempotencyService;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class IdempotencyInterceptor implements HandlerInterceptor {

    private static final String IDEMPOTENCY_KEY_HEADER = "Order-Idempotency-Key";
    private final IdempotencyService idempotencyService;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws Exception {
        var method = HttpMethod.valueOf(request.getMethod());
        if (!method.equals(HttpMethod.POST) && !method.equals(HttpMethod.PATCH)) {
            return true;
        }
        String key = request.getHeader(IDEMPOTENCY_KEY_HEADER);
        if (key == null || key.isBlank()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write("X-Idempotency-Key header is required");
            response.getWriter().close();
            return false;
        }

        Optional<IdempotencyKey> existing = idempotencyService.getByKey(key);
        if (existing.isPresent()) {
            return handleExistingKey(existing.get(), response);
        }

        idempotencyService.createPendingKey(key);
        return true;
}

    private boolean handleExistingKey(IdempotencyKey idempotencyKey, HttpServletResponse response) throws IOException {
        if(idempotencyKey.getStatus() == IdempotencyKeyStatus.PENDING){
            response.setStatus(HttpStatus.CONFLICT.value());
            response.getWriter().write("Request is already in progress");
            response.getWriter().close();
            return false;
        }
        if(idempotencyKey.getStatus() == IdempotencyKeyStatus.COMPLETED){
            response.setStatus(HttpStatus.OK.value());
            response.setContentType("application/json");
            response.getWriter().write(idempotencyKey.getResponse());
            response.getWriter().close();
            return false;
        }
        throw new IllegalArgumentException("Unknown idempotency key status: " + idempotencyKey.getStatus());
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request,
                                @NonNull HttpServletResponse response,
                                @NonNull Object handler,
                                Exception ex) throws Exception {
        var method = HttpMethod.valueOf(request.getMethod());
        if (!method.equals(HttpMethod.POST) && !method.equals(HttpMethod.PATCH)) {
            return;
        }

        String key = request.getHeader(IDEMPOTENCY_KEY_HEADER);
        if (key == null || key.isBlank()) {
            return;
        }

        var wrappedResponse = (ContentCachingResponseWrapper) request.getAttribute(
                ResponseCachingFilter.WRAPPED_RESPONSE_ATTRIBUTE);

        String responseBody = new String(wrappedResponse.getContentAsByteArray(),
                wrappedResponse.getCharacterEncoding());

        String idempotencyKey = request.getHeader(IDEMPOTENCY_KEY_HEADER);

        idempotencyService.markAsCompleted(idempotencyKey, responseBody,
                response.getStatus());
    }

}

