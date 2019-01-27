package com.bsilva.controller;

import com.bsilva.domain.Product;
import com.bsilva.service.NotifyService;
import com.bsilva.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService, NotifyService notifyService){
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Validated  Product reqProduct) throws JsonProcessingException {
        this.productService.save(reqProduct);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<Product> getProduct(@RequestParam String productId){
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.find(productId));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProdut(@RequestParam String productId) throws JsonProcessingException {
        this.productService.delete(productId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
