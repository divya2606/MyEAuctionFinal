package com.eauction.product.query.infrastructure.consumers;

import com.eauction.product.common.events.ProductAddedEvent;
import com.eauction.product.common.events.ProductBidUpdatedEvent;
import com.eauction.product.common.events.ProductBidedEvent;
import com.eauction.product.common.events.ProductDeletedEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consume(@Payload ProductAddedEvent event, Acknowledgment ack);
    void consume(@Payload ProductBidedEvent event, Acknowledgment ack);
    void consume(@Payload ProductBidUpdatedEvent event, Acknowledgment ack);
    void consume(@Payload ProductDeletedEvent event, Acknowledgment ack);
}
