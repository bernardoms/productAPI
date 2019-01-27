package com.bsilva.service;

import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.bsilva.domain.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NotifyServiceTest {
    @Mock
    AmazonSQSAsyncClient amazonSQSAsyncClient;

    @Mock
    SendMessageRequest sendMessageRequest;

    @InjectMocks
    NotifyService notifyService;
    @Test
    public void test_notify_product() throws JsonProcessingException {
        Product product = new Product();
        product.setProductId("1");
        product.setName("Green Table");
        product.setPrice(100.00);
        notifyService.notifyProduct(product);
        ObjectMapper objectMapper = new ObjectMapper();
        Mockito.verify(sendMessageRequest, Mockito.times(1)).withMessageBody(objectMapper.writeValueAsString(product));
    }
}
