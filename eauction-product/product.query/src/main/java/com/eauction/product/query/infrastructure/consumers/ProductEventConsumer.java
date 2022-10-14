package com.eauction.product.query.infrastructure.consumers;

import com.eauction.product.common.events.ProductAddedEvent;
import com.eauction.product.common.events.ProductBidUpdatedEvent;
import com.eauction.product.common.events.ProductBidedEvent;
import com.eauction.product.common.events.ProductDeletedEvent;
import com.eauction.product.query.infrastructure.handlers.EventHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ProductEventConsumer {//implements EventConsumer {
    @Autowired
    private EventHandler eventHandler;

   /* @KafkaListener(topics = "ProductAddedEvent", groupId = "eauctionConsumer")
    @Override
    public void consume(ProductAddedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }*/

    private static final Logger LOGGER = Logger.getLogger(ProductEventConsumer.class.getName());
    @KafkaListener(topics = "ProductAddedEvent", groupId = "eauctionConsumer")
    public void addProductconsume(String Message) {
        LOGGER.info(String.format("Message received -> %s", Message));
         try {
            ProductAddedEvent productAddedEvent = new ObjectMapper().readValue(Message, ProductAddedEvent.class);
            System.out.println("Product Name="+productAddedEvent.getProductName());
             eventHandler.onTest(productAddedEvent);
             System.out.println("Product Name="+productAddedEvent.getProductName());
             //ack.acknowledge();

        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @KafkaListener(topics = "ProductBidedEvent", groupId = "${spring.kafka.consumer.group-id}")
    //@Override
    public void bidProductConsume(String Message) {
        try {
            ProductBidedEvent productBidedEvent = new ObjectMapper().readValue(Message, ProductBidedEvent.class);
            eventHandler.on(productBidedEvent);
            //ack.acknowledge();

        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        //eventHandler.on(event);
        //ack.acknowledge();
    }

    @KafkaListener(topics = "ProductBidUpdatedEvent", groupId = "${spring.kafka.consumer.group-id}")
    //@Override
    public void updateBidConsume(String Message) {
        try {
            ProductBidUpdatedEvent productBidUpdatedEvent = new ObjectMapper().readValue(Message, ProductBidUpdatedEvent.class);
            eventHandler.on(productBidUpdatedEvent);
            //ack.acknowledge();

        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        //this.eventHandler.on(event);
        //ack.acknowledge();
    }

    /*@KafkaListener(topics = "ProductDeletedEvent", groupId = "${spring.kafka.consumer.group-id}")
    //@Override
    public void deleteConsume(String Message) {
        try {
            ProductDeletedEvent productDeletedEvent = new ObjectMapper().readValue(Message, ProductDeletedEvent.class);
            eventHandler.on(productDeletedEvent);
            //ack.acknowledge();

        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        //this.eventHandler.on(event);
        //ack.acknowledge();
    }*/
}
