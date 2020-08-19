package stu.learning.service.products.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stu.learning.service.products.services.FeatureFlagProvider;

@RestController
public class SupportController {

    @Autowired
    private FeatureFlagProvider provider;

    @GetMapping("/publishprices")
    public ResponseEntity getPublishPrices() {
        return ResponseEntity.ok(provider.isPricePublishEnabled());
    }

    @PostMapping("/publishprices")
    public ResponseEntity updatePublishPrices(@RequestParam(name = "enable") boolean enable) {
        provider.setPricePublishEnabled(enable);
        return ResponseEntity.noContent().build();
    }
}
