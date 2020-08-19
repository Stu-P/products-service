package stu.learning.service.products.events;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class PriceChangedEvent implements IEvent {
    private final UUID correlationId;
    private final Instant timeStamp;
    private final UUID productId;
    private final BigDecimal newPrice;

    public PriceChangedEvent(UUID correlationId, Instant timeStamp, UUID productId, BigDecimal newPrice) {
        this.correlationId = correlationId;
        this.timeStamp = timeStamp;
        this.productId = productId;
        this.newPrice = newPrice;
    }


    // region getters

    public UUID getCorrelationId() {
        return correlationId;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public UUID getProductId() {
        return productId;
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    @Override
    public String getKey() {
        return getProductId().toString();
    }

    // endregion

}
