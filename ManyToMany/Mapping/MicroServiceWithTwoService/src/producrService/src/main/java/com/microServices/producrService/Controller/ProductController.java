package com.microServices.producrService.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import producrService.src.main.java.com.microServices.producrService.Entity.Product;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private List<Product> productList = Arrays.asList(
            new Product(1, "Laptop", 50000.0),
            new Product(2, "Phone", 20000.0),
            new Product(3, "Tablet", 15000.0)
    );

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productList.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }
}

