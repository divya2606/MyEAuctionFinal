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
public class BuyerDetails extends BaseEntity {
    //buyer info
    @Id //Indicate Primary Key
    private String id;
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
