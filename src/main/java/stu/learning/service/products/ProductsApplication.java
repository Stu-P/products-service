package stu.learning.service.products;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import stu.learning.service.products.controllers.ProductsController;

@SpringBootApplication
public class ProductsApplication {

	Logger logger = LoggerFactory.getLogger(ProductsController.class);
	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}
}
