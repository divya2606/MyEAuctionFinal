package com.eauction.product.query.infrastructure.handlers;

import com.eauction.product.common.events.ProductAddedEvent;
import com.eauction.product.common.events.ProductBidUpdatedEvent;
import com.eauction.product.common.events.ProductBidedEvent;
import com.eauction.product.common.events.ProductDeletedEvent;
import com.eauction.product.query.domain.BuyerDetails;
import com.eauction.product.query.domain.BuyerRepository;
import com.eauction.product.query.domain.EauctionProduct;
import com.eauction.product.query.domain.ProductRepository;
import com.eauction.product.query.infrastructure.handlers.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductEventHandler implements EventHandler {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Override
    public void on(ProductAddedEvent event) {
        var eauctionProduct = EauctionProduct.builder()
                .id(event.getId())
                .productName(event.getProductName())
                .shortDesc(event.getShortDesc())
                .detailedDesc(event.getDetailedDesc())
                .category(event.getCategory())
                .startPrice(event.getStartPrice())
                .bidEndDate(event.getBidEndDate())
                .sellerFName(event.getSellerFName())
                .sellerLName(event.getSellerLName())
                .sellerAddr(event.getSellerAddr())
                .sellerCity(event.getSellerCity())
                .sellerState(event.getSellerState())
                .sellerPin(event.getSellerPin())
                .sellerPhone(event.getSellerPhone())
                .sellerEmail(event.getSellerEmail())
                .build();
        productRepository.save(eauctionProduct);
    }

    @Override
    public void on(ProductBidedEvent event) {
        var buyerDetails = BuyerDetails.builder()
                .id(event.getId())
                .buyerFName(event.getBuyerFName())
                .buyerLName(event.getBuyerLName())
                .buyerAddr(event.getBuyerAddr())
                .buyerCity(event.getBuyerCity())
                .buyerState(event.getBuyerState())
                .buyerPin(event.getBuyerPin())
                .buyerPhone(event.getBuyerPhone())
                .buyerEmail(event.getBuyerEmail())
                .bidAmount(event.getBidAmount())
                .productId(event.getProductId())
                .build();
        buyerRepository.save(buyerDetails);
        /*var buyerDetails = buyerRepository.findByProductId(event.getId());
        if (buyerDetails.isEmpty()) {
            return;
        }
        buyerDetails.get().setId(event.getId());
        buyerDetails.get().setBuyerFName(event.getBuyerFName());
        buyerDetails.get().setBuyerLName(event.getBuyerLName());
        buyerDetails.get().setBuyerAddr(event.getBuyerAddr());
        buyerDetails.get().setBuyerCity(event.getBuyerCity());
        buyerDetails.get().setBuyerState(event.getBuyerState());
        buyerDetails.get().setBuyerPin(event.getBuyerPin());
        buyerDetails.get().setBuyerPhone(event.getBuyerPhone());
        buyerDetails.get().setBuyerEmail(event.getBuyerEmail());
        buyerDetails.get().setBidAmount(event.getBidAmount());
        buyerRepository.save(buyerDetails.get());*/
    }

    @Override
    public void on(ProductBidUpdatedEvent event) {
        List<BuyerDetails> buyerDetails = buyerRepository.findByBuyerEmail(event.getBEmail());
        if (buyerDetails.isEmpty()) {
            return;
        }
        for (BuyerDetails l:buyerDetails) {
            if (l.getProductId().equalsIgnoreCase(event.getProductId())) {
                l.setBidAmount(event.getBidAmount());
                buyerRepository.save(l);
            }
        }
    }

    @Override
    public void on(ProductDeletedEvent event) {
        productRepository.deleteById(event.getId());
    }

    public void onTest(ProductAddedEvent event) {
        var eauctionProduct = EauctionProduct.builder()
                .id(event.getId())
                .productName(event.getProductName())
                .shortDesc(event.getShortDesc())
                .detailedDesc(event.getDetailedDesc())
                .category(event.getCategory())
                .startPrice(event.getStartPrice())
                .bidEndDate(event.getBidEndDate())
                .sellerFName(event.getSellerFName())
                .sellerLName(event.getSellerLName())
                .sellerAddr(event.getSellerAddr())
                .sellerCity(event.getSellerCity())
                .sellerState(event.getSellerState())
                .sellerPin(event.getSellerPin())
                .sellerPhone(event.getSellerPhone())
                .sellerEmail(event.getSellerEmail())
                .build();
        productRepository.save(eauctionProduct);
    }
}
