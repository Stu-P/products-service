package stu.learning.service.products.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.UUID;
import stu.learning.service.products.dto.GetAllResponse;
import stu.learning.service.products.entities.Product;
import stu.learning.service.products.entities.ProductOption;
import stu.learning.service.products.services.IProductsService;

import javax.validation.Valid;

@RestController
public class ProductsController {
    Logger logger = LoggerFactory.getLogger(ProductsController.class);

    @Autowired
    private IProductsService productService;

    @GetMapping("/products")
    public GetAllResponse<Product> getProducts(@RequestParam(name = "name", required = false) String nameFilter) {
        return new GetAllResponse<>(productService.getProducts(nameFilter));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductsById(@PathVariable(value = "id") UUID productId) {
        logger.info("looking for product with {}", productId);
        Product product = productService.getProduct(productId);

        logger.info("found product {}", product);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping("/products")
    public ResponseEntity createProduct(@Valid @RequestBody Product product) {
        Product created = productService.createProduct(product);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/products/{id}")
    public ResponseEntity updateProduct(@PathVariable(value = "id") UUID productId, @Valid @RequestBody Product product) {
        productService.updateProduct(productId, product);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable(value = "id") UUID productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/products/{productId}/options")
    public GetAllResponse<ProductOption> getProductOptions(@PathVariable(value = "productId") UUID productId) {
        return new GetAllResponse<ProductOption>(productService.getProductOptions(productId));
    }

    @GetMapping("/products/{productId}/options/{id}")
    public ResponseEntity<ProductOption> getProductOption(
            @PathVariable(value = "productId") UUID productId,
            @PathVariable(value = "id") UUID id) {

        ProductOption option = productService.getProductOption(id, productId);
        return ResponseEntity.ok().body(option);
    }

    @PostMapping("/products/{productId}/options")
    public ResponseEntity createProductOption(
            @PathVariable(value = "productId") UUID productId,
            @RequestBody ProductOption productOption) {

        ProductOption created = productService.createProductOption(productId, productOption);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/products/{productId}/options/{id}")
    public ResponseEntity updateProductOption(
            @PathVariable(value = "productId") UUID productId,
            @PathVariable(value = "id") UUID id,
            @RequestBody ProductOption productOption) {

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/products/{productId}/options/{id}")
    public ResponseEntity deleteProductOption(
            @PathVariable(value = "productId") UUID productId,
            @PathVariable(value = "id") UUID id) {

        return ResponseEntity.noContent().build();
    }
}


//    @PutMapping("/posts/{postId}")
//    public Post updatePost(@PathVariable Long postId, @Valid @RequestBody Post postRequest) {
//        return postRepository.findById(postId).map(post -> {
//            post.setTitle(postRequest.getTitle());
//            post.setDescription(postRequest.getDescription());
//            post.setContent(postRequest.getContent());
//            return postRepository.save(post);
//        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
//    }
//
//    @DeleteMapping("/posts/{postId}")
//    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
//        return postRepository.findById(postId).map(post -> {
//            postRepository.delete(post);
//            return ResponseEntity.ok().build();
//        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
//    }
//
//}