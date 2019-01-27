package com.bsilva.dao;

import com.bsilva.domain.Product;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
    private MappingManager mappingManager;
    private Mapper<Product> mapper;

    @Autowired
    public void ProductDao(MappingManager mappingManager){
        this.mappingManager = mappingManager;
        this.mapper = mappingManager.mapper(Product.class);
    }

    public void save(Product product){
        mapper.save(product);
    }

    public Product find(String productId){
        return mapper.get(productId);
    }

    public void delete(String prductId){
        mapper.delete(prductId);
    }

}
