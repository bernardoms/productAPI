package com.bsilva.dao;

import com.bsilva.domain.Product;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductDaoTest {

    @Mock
    private Mapper<Product> productMapper;

    @InjectMocks
    private ProductDao productDao;

    @Test
    public void saveProduct(){
        Product product = new Product();
        product.setProductId("1");
        product.setName("Green Table");
        product.setPrice(100.00);
        productDao.save(product);
        Mockito.verify(productMapper, Mockito.times(1)).save(product);
    }

    @Test
    public void findProduct(){
        Product product = new Product();
        product.setProductId("1");
        product.setName("Green Table");
        product.setPrice(100.00);
        productDao.find(product.getProductId());
        Mockito.verify(productMapper, Mockito.times(1)).get(product.getProductId());
    }

    @Test
    public void deleteProduct(){
        Product product = new Product();
        product.setProductId("1");
        product.setName("Green Table");
        product.setPrice(100.00);
        productDao.delete(product.getProductId());
        Mockito.verify(productMapper, Mockito.times(1)).delete(product.getProductId());
    }

}
