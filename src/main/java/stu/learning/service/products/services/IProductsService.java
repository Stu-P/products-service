package stu.learning.service.products.services;

import stu.learning.service.products.entities.Product;
import stu.learning.service.products.entities.ProductOption;

import java.util.List;
import java.util.UUID;

public interface IProductsService {
    List<Product> getProducts(String nameFilter);

    Product getProduct(UUID productId);

    Product createProduct(Product product);

    void updateProduct(UUID productId, Product product);

    void deleteProduct(UUID productId);

    List<ProductOption> getProductOptions(UUID productId);

    ProductOption getProductOption(UUID id, UUID productId);

    ProductOption createProductOption(UUID productId, ProductOption productOption);

    void updateProductOption(UUID id, UUID productId, ProductOption option);

    void deleteProductOption(UUID id, UUID productId);
}
