package com.eauction.product.query.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BuyerRepository extends CrudRepository<BuyerDetails, String> {
    List<BuyerDetails> findByProductId(String id);
    List<BuyerDetails> findByBuyerEmail(String id);
    List<BuyerDetails> findByProductIdOrderByBidAmountDesc(String id);
}
