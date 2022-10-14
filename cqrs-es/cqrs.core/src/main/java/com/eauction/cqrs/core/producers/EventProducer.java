package com.eauction.cqrs.core.producers;

import com.eauction.cqrs.core.events.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
