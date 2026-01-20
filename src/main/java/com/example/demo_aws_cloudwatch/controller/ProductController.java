package com.example.demo_aws_cloudwatch.controller;

import com.example.demo_aws_cloudwatch.modal.Product;
import com.example.demo_aws_cloudwatch.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("products")
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }


}
