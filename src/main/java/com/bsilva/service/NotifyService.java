package com.bsilva.service;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.bsilva.domain.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifyService {

    private AmazonSQSAsync amazonSQSAsync;

    private SendMessageRequest sendMessageRequest;

    @Autowired
    public NotifyService(AmazonSQSAsync amazonSQSAsync, SendMessageRequest sendMessageRequest){
        this.amazonSQSAsync = amazonSQSAsync;
        this.sendMessageRequest = sendMessageRequest;
    }

    public void notifyProduct(Product product) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String message = mapper.writeValueAsString(product);
        amazonSQSAsync.sendMessage(sendMessageRequest.withMessageBody(message));
    }
}
