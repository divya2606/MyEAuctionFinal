package com.eauction.product.query.infrastructure.handlers;

import com.eauction.product.common.events.ProductAddedEvent;
import com.eauction.product.common.events.ProductBidUpdatedEvent;
import com.eauction.product.common.events.ProductBidedEvent;
import com.eauction.product.common.events.ProductDeletedEvent;

public interface EventHandler {
    void on(ProductAddedEvent event);
    void onTest(ProductAddedEvent event);
    void on(ProductBidedEvent event);
    void on(ProductBidUpdatedEvent event);
    void on(ProductDeletedEvent event);
}
