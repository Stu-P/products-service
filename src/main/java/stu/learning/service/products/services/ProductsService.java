package stu.learning.service.products.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import stu.learning.service.products.controllers.ProductsController;
import stu.learning.service.products.entities.Product;
import stu.learning.service.products.entities.ProductOption;
import stu.learning.service.products.events.ProductCreatedEvent;
import stu.learning.service.products.exceptions.ResourceNotFoundException;
import stu.learning.service.products.persistence.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class ProductsService implements IProductsService {
    Logger logger = LoggerFactory.getLogger(ProductsController.class);

    private final IProductsRepository productsRepo;
    private final IProductOptionsRepository optionsRepo;
    private final IEventPublisher eventPublisher;
    private final ICorrelationProvider idProvider;

    @Autowired
    public ProductsService(
            IProductsRepository productsRepo,
            IProductOptionsRepository optionsRepo,
            @Qualifier("kafka") IEventPublisher eventPublisher,
            ICorrelationProvider idProvider){
        this.productsRepo = productsRepo;
        this.optionsRepo = optionsRepo;
        this.eventPublisher = eventPublisher;
        this.idProvider = idProvider;
    }

    public List<Product> getProducts(String nameFilter) {
        return nameFilter == null ?
                productsRepo.findAll() :
                productsRepo.findByNameContaining(nameFilter);
    }

    public Product getProduct(UUID productId) {
        return productsRepo.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("no product found with id %s", productId)));
    }

    public Product createProduct(Product product) {
        var created = productsRepo.save(product);

        eventPublisher.Publish(new ProductCreatedEvent(
                created,
                idProvider.getId(),
                Instant.now()));

        return created;
    }

    public void updateProduct(UUID productId, Product product) {
        Product existingProduct = productsRepo.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("no product found with id %s", productId)));

        productsRepo.save(product);
    }

    public void deleteProduct(UUID productId) {
        Product existingProduct = productsRepo.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("no product found with id %s", productId)));

        productsRepo.delete(existingProduct);
    }

    public List<ProductOption> getProductOptions(UUID productId) {
        var product = productsRepo.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("no product found with id %s", productId)));
        return product.getOptions();
    }

    public ProductOption getProductOption(UUID id, UUID productId) {
        return optionsRepo.findByIdAndProductId(id, productId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("no product found with id %s", productId)));
    }

    public ProductOption createProductOption(UUID productId, ProductOption productOption) {
        Product existingProduct = productsRepo
                .findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("no product found with id %s", productId)));

        productOption.setProduct(existingProduct);
        return optionsRepo.save(productOption);
    }

    public void updateProductOption(UUID id, UUID productId, ProductOption option) {
    }

    public void deleteProductOption(UUID id, UUID productId) {
    }
}
