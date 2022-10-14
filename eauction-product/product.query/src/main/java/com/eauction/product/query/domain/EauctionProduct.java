package com.eauction.product.query.domain;

import com.eauction.cqrs.core.domain.BaseEntity;
import com.eauction.product.common.dto.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class EauctionProduct extends BaseEntity {
    @Id //Indicate Primary Key
    private String id;
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
    //buyer info
    /*private String buyerFName;
    private String buyerLName;
    private String buyerAddr;
    private String buyerCity;
    private String buyerState;
    private String buyerPin;
    private Integer buyerPhone;
    private String buyerEmail;
    private String productId;
    private double bidAmount;*/
}
