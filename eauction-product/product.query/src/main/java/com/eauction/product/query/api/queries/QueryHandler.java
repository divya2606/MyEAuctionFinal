package com.eauction.product.query.api.queries;

import com.eauction.cqrs.core.domain.BaseEntity;

import java.util.List;

public interface QueryHandler {
    List<BaseEntity> handle(FindBidsByProductQuery query);
    List<BaseEntity> handle(FindProductDetailsQuery query);
    //List<String> handle(FindAllProductIdsQuery query);
}
