package com.eauction.product.query.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDBRepository extends JpaRepository<EauctionProduct, String> {
}
