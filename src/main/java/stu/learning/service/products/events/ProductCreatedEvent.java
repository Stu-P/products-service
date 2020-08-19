package stu.learning.service.products.events;

import stu.learning.service.products.entities.Product;

import java.time.Instant;
import java.util.UUID;

public class ProductCreatedEvent implements IEvent {
    private final Product product;
    private final UUID correlationId;
    private final Instant timeStamp;

    public ProductCreatedEvent(Product product, UUID correlationId, Instant timeStamp) {
        this.product = product;
        this.correlationId = correlationId;
        this.timeStamp = timeStamp;
    }

    //region get/set
    public Product getProduct() {
        return product;
    }

    public UUID getCorrelationId() {
        return correlationId;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }
    // endregion

    @Override
    public String getKey() {
        return getProduct().getId().toString();
    }

    @Override
    public String toString() {
        return "ProductCreatedEvent{" +
                "product=" + product +
                ", correlationId=" + correlationId +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
