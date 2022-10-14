package com.eauction.product.common.events;

import com.eauction.cqrs.core.events.BaseEvent;
import com.eauction.product.common.dto.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductAddedEvent extends BaseEvent {
    private String productName;
    private String shortDesc;
    private String detailedDesc;
    private CategoryType category;
    private double startPrice;
    private Date bidEndDate;
    //seller info
    private String sellerFName;
    private String sellerLName;
    private String sellerAddr;
    private String sellerCity;
    private String sellerState;
    private String sellerPin;
    private String sellerPhone;
    private String sellerEmail;
}
