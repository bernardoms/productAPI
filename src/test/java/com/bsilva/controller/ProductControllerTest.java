package com.bsilva.controller;

import com.bsilva.domain.Product;
import com.bsilva.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
    private MockMvc mockMvc;
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;
    @Captor
    private ArgumentCaptor<Product> productArgumentCaptor;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .build();
    }

    @Test
    public void test_get_product() throws Exception {
        Product product = new Product();
        product.setProductId("1");
        product.setName("Green Table");
        product.setPrice(100.00);

        Mockito.when(productService.find(product.getProductId())).thenReturn(product);

        ObjectMapper mapper = new ObjectMapper();
        String productMapped = mapper.writeValueAsString(product);

        MockHttpServletResponse response = mockMvc.perform(get("/product").param("productId",product.getProductId()).accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), productMapped);

    }

    @Test
    public void test_create_product() throws Exception {
        Product product = new Product();
        product.setProductId("1");
        product.setName("Green Table");
        product.setPrice(100.00);


        ObjectMapper mapper = new ObjectMapper();
        String productMapped = mapper.writeValueAsString(product);

        mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON).content(productMapped)).andExpect(status().isOk());

        Mockito.verify(productService, Mockito.times(1)).save(productArgumentCaptor.capture());

        assertEquals(productArgumentCaptor.getValue().getProductId(), product.getProductId());
        assertEquals(productArgumentCaptor.getValue().getName(), product.getName());
        assertEquals(productArgumentCaptor.getValue().getPrice(),product.getPrice(), 0);
    }

    @Test
    public void test_delete_product() throws Exception {
        Product product = new Product();
        product.setProductId("1");
        product.setName("Green Table");
        product.setPrice(100.00);

        ObjectMapper mapper = new ObjectMapper();
        String productMapped = mapper.writeValueAsString(product);

       mockMvc.perform(delete("/product").param("productId",product.getProductId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        Mockito.verify(productService, Mockito.times(1)).delete(product.getProductId());

    }
}
