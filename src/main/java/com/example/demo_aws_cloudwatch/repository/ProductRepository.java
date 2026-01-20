package com.example.demo_aws_cloudwatch.repository;

import com.example.demo_aws_cloudwatch.modal.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Repository
public class ProductRepository {

    ObjectMapper mapper;

    List<Product> products;

    public ProductRepository(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @PostConstruct
    public void init() {
        try {
            this.products = mapper.readValue(new ClassPathResource("mock_data.json").getInputStream(),
                    new TypeReference<List<Product>>() {
                    });
        } catch (IOException e) {
            System.err.println("Lỗi đọc file json");
            System.out.println(e.getMessage());
        }

    }

    public List<Product> getProducts() {
        return List.copyOf(products);
    }

    public Product getProduct(int id) {
        return products.get(id);
    }

    public String createProduct(Product product) {
        products.add(product);
        return "Add product successfully";
    }

    public String deleteProduct(int id) {
        boolean removed = products.removeIf(p -> p.getProductId() == id);

        if (!removed)
            return "Product not found";

        return "Delete product successfully";
    }
}
