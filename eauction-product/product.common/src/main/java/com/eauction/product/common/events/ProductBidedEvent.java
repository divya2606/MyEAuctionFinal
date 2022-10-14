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
public class ProductBidedEvent extends BaseEvent {
    //buyer info
    private String buyerFName;
    private String buyerLName;
    private String buyerAddr;
    private String buyerCity;
    private String buyerState;
    private String buyerPin;
    private String buyerPhone;
    private String buyerEmail;
    private String productId;
    private double bidAmount;
}
