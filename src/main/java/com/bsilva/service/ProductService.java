package com.bsilva.service;

import com.bsilva.dao.ProductDao;
import com.bsilva.domain.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private ProductDao productDao;
    private NotifyService notifyService;

    @Autowired
    public ProductService(ProductDao productDao, NotifyService notifyService){
        this.productDao = productDao;
        this.notifyService = notifyService;
    }

    public void save(Product product) throws JsonProcessingException {
        this.notifyService.notifyProduct(product);
        this.productDao.save(product);
    }

    public Product find(String productId){
        return this.productDao.find(productId);
    }

    public void delete(String productId) throws JsonProcessingException {
        Product product = this.productDao.find(productId);
        this.notifyService.notifyProduct(product);
        this.productDao.delete(productId);
    }
}
