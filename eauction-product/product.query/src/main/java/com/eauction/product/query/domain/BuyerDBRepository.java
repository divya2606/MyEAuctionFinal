package com.eauction.product.query.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyerDBRepository extends JpaRepository<BuyerDetails, String> {
    List<BuyerDetails> findByBuyerEmail(String id);
    List<BuyerDetails> findByProductId(String id);
}
