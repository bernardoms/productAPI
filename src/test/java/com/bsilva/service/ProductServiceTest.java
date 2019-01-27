package com.bsilva.service;

import com.bsilva.dao.ProductDao;
import com.bsilva.domain.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @Mock
    private ProductDao productDao;

    @Mock
    private NotifyService notifyService;

    @InjectMocks
    private ProductService productService;

    @Test
    public void test_save_product() throws JsonProcessingException {
        Product product = new Product();
        product.setProductId("1");
        product.setName("Green Table");
        product.setPrice(100.00);
        productService.save(product);
        Mockito.verify(productDao, Mockito.times(1)).save(product);
        Mockito.verify(notifyService, Mockito.times(1)).notifyProduct(product);
    }

    @Test
    public void test_find_product() throws JsonProcessingException {
        Product product = new Product();
        product.setProductId("1");
        product.setName("Green Table");
        product.setPrice(100.00);
        productService.find(product.getProductId());
        Mockito.verify(productDao, Mockito.times(1)).find(product.getProductId());
        Mockito.verify(notifyService, Mockito.never()).notifyProduct(product);
    }

    @Test
    public void test_delete_product() throws JsonProcessingException {
        Product product = new Product();
        product.setProductId("1");
        product.setName("Green Table");
        product.setPrice(100.00);
        productService.delete(product.getProductId());
        Mockito.verify(productDao, Mockito.times(1)).delete(product.getProductId());
        Mockito.verify(notifyService, Mockito.never()).notifyProduct(product);
    }

}
