package com.eauction.product.query.api.queries;

import com.eauction.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindBidsByProductQuery extends BaseQuery {
    private String id;
}
