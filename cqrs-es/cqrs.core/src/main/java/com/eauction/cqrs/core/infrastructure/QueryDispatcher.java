package com.eauction.cqrs.core.infrastructure;

import com.eauction.cqrs.core.domain.BaseEntity;
import com.eauction.cqrs.core.queries.BaseQuery;
import com.eauction.cqrs.core.queries.QueryHandlerMethod;

import java.util.List;

public interface QueryDispatcher {
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);
    <U extends BaseEntity> List<U> send(BaseQuery query);
}
