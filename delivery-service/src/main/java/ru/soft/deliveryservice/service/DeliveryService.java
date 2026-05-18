package ru.soft.deliveryservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.soft.deliveryservice.dto.DeliveryRequest;
import ru.soft.deliveryservice.dto.DeliveryResponse;
import ru.soft.deliveryservice.exception.DeliveryNotFoundException;
import ru.soft.deliveryservice.mapper.DeliveryMapper;
import ru.soft.deliveryservice.model.Delivery;
import ru.soft.deliveryservice.repository.DeliveryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;

    @Transactional(readOnly = true)
    public List<DeliveryResponse> findAll() {
        return deliveryRepository.findAll().stream()
                .map(deliveryMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public DeliveryResponse findById(Long id) {
        return deliveryMapper.toResponse(getDelivery(id));
    }

    @Transactional(readOnly = true)
    public List<DeliveryResponse> findByOrderId(Long orderId) {
        return deliveryRepository.findByOrderId(orderId).stream()
                .map(deliveryMapper::toResponse)
                .toList();
    }

    @Transactional
    public DeliveryResponse create(DeliveryRequest request) {
        Delivery delivery = deliveryRepository.save(deliveryMapper.toEntity(request));
        return deliveryMapper.toResponse(delivery);
    }

    @Transactional
    public DeliveryResponse update(Long id, DeliveryRequest request) {
        Delivery delivery = getDelivery(id);
        deliveryMapper.updateEntity(delivery, request);
        return deliveryMapper.toResponse(deliveryRepository.save(delivery));
    }

    @Transactional
    public void delete(Long id) {
        deliveryRepository.deleteById(id);
    }

    private Delivery getDelivery(Long id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new DeliveryNotFoundException(id));
    }
}
