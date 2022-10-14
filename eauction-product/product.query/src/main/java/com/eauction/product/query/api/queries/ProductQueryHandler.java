package com.eauction.product.query.api.queries;

import com.eauction.cqrs.core.domain.BaseEntity;
import com.eauction.product.query.domain.BuyerDetails;
import com.eauction.product.query.domain.BuyerRepository;
import com.eauction.product.query.domain.EauctionProduct;
import com.eauction.product.query.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class ProductQueryHandler implements QueryHandler {
    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<BaseEntity> handle(FindBidsByProductQuery query) {
        List<BuyerDetails> productAccount = buyerRepository.findByProductIdOrderByBidAmountDesc(query.getId());
        List<BaseEntity> productAccountList = new ArrayList<>();
        if (productAccount.isEmpty()) {
            return null;
        }
        for (BuyerDetails l:productAccount) {
            //productAccountList = new ArrayList<>();
            productAccountList.add(l);
        }
        return productAccountList;
    }

    @Override
    public List<BaseEntity> handle(FindProductDetailsQuery query) {
        var productDetails = productRepository.findById(query.getId());
        List<BaseEntity> productDetailsList = new ArrayList<>();
        productDetailsList.add(productDetails.get());
        return productDetailsList;
    }

    /*@Override
    public List<String> handle(FindAllProductIdsQuery query) {
        //var productIds = productRepository.findAllId();
        List productIdsList = productRepository.getAllIds();
       //productIdsList.add(productIds.get());
        return productIdsList;
    }*/
}
