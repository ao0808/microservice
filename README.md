# Интернет-магазин — микросервисы

Декомпозиция по бизнес-возможностям:

| Сервис | Порт | База H2 | Описание |
|--------|------|---------|----------|
| **order-service** | 8081 | `orderdb` | Заказы |
| **payment-service** | 8082 | `paymentdb` | Оплаты |
| **delivery-service** | 8083 | `deliverydb` | Доставка |

Каждый сервис — отдельное Spring Boot приложение со своей БД (паттерн **Database per Service**). Межсервисное взаимодействие не реализовано (по заданию).

## Сборка и запуск

```bash
mvn clean package
```

Запуск каждого сервиса в отдельном терминале:

```bash
mvn -pl order-service spring-boot:run
mvn -pl payment-service spring-boot:run
mvn -pl delivery-service spring-boot:run
```

H2 Console: `http://localhost:8081/h2-console` (JDBC URL: `jdbc:h2:mem:orderdb`, user: `sa`, password пустой).

## REST API (Postman)

### order-service — `http://localhost:8081`

| Метод | URL | Тело (JSON) |
|-------|-----|-------------|
| GET | `/api/orders` | — |
| GET | `/api/orders/{id}` | — |
| POST | `/api/orders` | см. ниже |
| PUT | `/api/orders/{id}` | см. ниже |
| DELETE | `/api/orders/{id}` | — |

```json
{
  "customerName": "Иван Иванов",
  "productName": "Ноутбук",
  "quantity": 1,
  "totalAmount": 89990.00,
  "status": "CREATED"
}
```

Статусы заказа: `CREATED`, `PAID`, `SHIPPED`, `DELIVERED`, `CANCELLED`.

### payment-service — `http://localhost:8082`

| Метод | URL | Тело (JSON) |
|-------|-----|-------------|
| GET | `/api/payments` | — |
| GET | `/api/payments/{id}` | — |
| GET | `/api/payments/order/{orderId}` | — |
| POST | `/api/payments` | см. ниже |
| PUT | `/api/payments/{id}` | см. ниже |
| DELETE | `/api/payments/{id}` | — |

```json
{
  "orderId": 1,
  "amount": 89990.00,
  "status": "PENDING"
}
```

Статусы оплаты: `PENDING`, `PAID`, `FAILED`, `REFUNDED`.

### delivery-service — `http://localhost:8083`

| Метод | URL | Тело (JSON) |
|-------|-----|-------------|
| GET | `/api/deliveries` | — |
| GET | `/api/deliveries/{id}` | — |
| GET | `/api/deliveries/order/{orderId}` | — |
| POST | `/api/deliveries` | см. ниже |
| PUT | `/api/deliveries/{id}` | см. ниже |
| DELETE | `/api/deliveries/{id}` | — |

```json
{
  "orderId": 1,
  "address": "Москва, ул. Примерная, д. 1",
  "status": "PENDING"
}
```

Статусы доставки: `PENDING`, `IN_TRANSIT`, `DELIVERED`, `CANCELLED`.

## Стек

- Java 21
- Spring Boot 3.3, Spring Data JPA
- H2 (in-memory)
- Lombok
