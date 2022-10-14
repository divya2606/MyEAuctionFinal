package com.eauction.product.query.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<EauctionProduct, String> {
    Optional<EauctionProduct> findById(String id);
    @Query("select id from EauctionProduct")
    List<String> getAllIds();
}
