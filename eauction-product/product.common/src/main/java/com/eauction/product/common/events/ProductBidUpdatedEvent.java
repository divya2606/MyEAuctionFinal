package com.eauction.product.common.events;

import com.eauction.cqrs.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductBidUpdatedEvent extends BaseEvent {
    private String productId;
    private String bEmail;
    private double bidAmount;
}
