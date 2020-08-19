package stu.learning.service.products.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.*;

import stu.learning.service.products.entities.Product;
import stu.learning.service.products.events.PriceChangedEvent;
import stu.learning.service.products.persistence.IProductsRepository;

@Service
public class PriceChangeService implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(PriceChangeService.class);

    private final IProductsRepository repo;
    private final IEventPublisher publisher;
    private final FeatureFlagProvider flags;
    private final Random randomGen;
    private List<Product> products;

    @Autowired
    public PriceChangeService(
            IProductsRepository repo,
            FeatureFlagProvider flags,
            @Qualifier("kafka") IEventPublisher publisher
    ) {
        this.repo = repo;
        this.flags = flags;
        this.publisher = publisher;
        this.randomGen = new Random();
    }

    @Override
    public void run(String... args) throws Exception {

        // get all products
        products = repo.findAll();

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                products = repo.findAll();
            }
        }, 1 * 60 * 1000, 1 * 60 * 1000);

        while (true) {
            if (flags.isPricePublishEnabled()) {
                for (Product p : products) {

                    try {
                        var event = new PriceChangedEvent(
                                UUID.randomUUID(),
                                Instant.now(),
                                p.getId(),
                                generateNewPrice());
                        logger.info("publish price change *** product: {} | price: {}", event.getProductId(), event.getNewPrice());

                        publisher.Publish(event);

                    } catch (Exception ex) {
                        logger.error("error publishing {} | {}", ex.getMessage(), ex.getCause());
                    }
                }
            }
            Thread.sleep(150);
        }
    }

    private BigDecimal generateNewPrice() {
        return new BigDecimal(randomGen.nextDouble() * 100).setScale(2, RoundingMode.HALF_EVEN);
    }
}
