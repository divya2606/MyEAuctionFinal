package com.eauction.product.cmd.domain;

import com.eauction.cqrs.core.domain.AggregateRoot;
import com.eauction.product.cmd.api.commands.AddProductCommand;
import com.eauction.product.cmd.api.commands.BidProductCommand;
import com.eauction.product.common.events.ProductAddedEvent;
import com.eauction.product.common.events.ProductBidUpdatedEvent;
import com.eauction.product.common.events.ProductBidedEvent;
import com.eauction.product.common.events.ProductDeletedEvent;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class ProductAggregate extends AggregateRoot {
    private Boolean active;
    private double bid;

    public Date getBidEndDate() {
        return bidEndDate;
    }

    private Date bidEndDate;

    public String getEmail() {
        return email;
    }

    private String email;



    public ProductAggregate(AddProductCommand command) {
        raiseEvent(ProductAddedEvent.builder()
                    .id(command.getId())
                    .productName(command.getProductName())
                    .shortDesc(command.getShortDesc())
                    .detailedDesc(command.getDetailedDesc())
                    .category(command.getCategory())
                    .startPrice(command.getStartPrice())
                    .bidEndDate(command.getBidEndDate())
                    .sellerFName(command.getSellerFName())
                    .sellerLName(command.getSellerLName())
                    .sellerAddr(command.getSellerAddr())
                    .sellerCity(command.getSellerCity())
                    .sellerState(command.getSellerState())
                    .sellerPin(command.getSellerPin())
                    .sellerPhone(command.getSellerPhone())
                    .sellerEmail(command.getSellerEmail())
                    .build());
    }

    public void apply(ProductAddedEvent event) {
        this.id = event.getId();
        this.active = true;
        this.bid = event.getStartPrice();
        this.bidEndDate = event.getBidEndDate();
    }

    /*public void bidProduct(String buyerFName,
                           String buyerLName,
                           String buyerAddr,
                           String buyerCity,
                           String buyerState,
                           String buyerPin,
                           Integer buyerPhone,
                           String buyerEmail,
                           String productId,
                           double bidAmount) {
        if(!this.active) {
            throw new IllegalStateException("Cannot bid a product which is not available");
        }
        raiseEvent(ProductBidedEvent.builder()
                    .id(this.id)
                    .buyerFName(buyerFName)
                    .buyerLName(buyerLName)
                    .buyerAddr(buyerAddr)
                    .buyerCity(buyerCity)
                    .buyerState(buyerState)
                    .buyerPin(buyerPin)
                    .buyerPhone(buyerPhone)
                    .buyerEmail(buyerEmail)
                    .productId(productId)
                    .bidAmount(bidAmount)
                    .build());
    }*/

    public ProductAggregate(BidProductCommand command) {
        raiseEvent(ProductBidedEvent.builder()
                .id(command.getId())
                .buyerFName(command.getBuyerFName())
                .buyerLName(command.getBuyerLName())
                .buyerAddr(command.getBuyerAddr())
                .buyerCity(command.getBuyerCity())
                .buyerState(command.getBuyerState())
                .buyerPin(command.getBuyerPin())
                .buyerPhone(command.getBuyerPhone())
                .buyerEmail(command.getBuyerEmail())
                .productId(command.getProductId())
                .bidAmount(command.getBidAmount())
                .build());
    }

    public void apply(ProductBidedEvent event) {
        this.id = event.getId();
        this.bid = event.getBidAmount();
    }

    public void updateBidProduct(String id, String productId, String bEmail, double bidAmount) {
        if(!this.active) {
            throw new IllegalStateException("Cannot update bid a product which is not available");
        }
        raiseEvent(ProductBidUpdatedEvent.builder()
                    .id(this.id)
                    .productId(productId)
                    .bEmail(bEmail)
                    .bidAmount(bidAmount)
                    .build());
    }

    public void apply(ProductBidUpdatedEvent event) {
        this.id = event.getId();
        this.bid = event.getBidAmount();
    }

    public void deleteProduct() {
        if(!this.active) {
            throw new IllegalStateException("Cannot delete a product which is not available");
        }
        raiseEvent(ProductDeletedEvent.builder()
                    .id(this.id)
                    .build());
    }

    public void apply(ProductDeletedEvent event) {
        this.id = event.getId();
        this.active = false;
    }
}
