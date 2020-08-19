package stu.learning.service.products.services;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FeatureFlagProvider {

    @Value("${features.price-publishing.enabled}")
    private boolean pricePublishEnabled;

    public boolean isPricePublishEnabled() {
        return pricePublishEnabled;
    }

    public void setPricePublishEnabled(boolean pricePublishEnabled) {
        this.pricePublishEnabled = pricePublishEnabled;
    }
}