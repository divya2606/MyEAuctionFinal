package com.eauction.product.query;

import com.eauction.cqrs.core.infrastructure.QueryDispatcher;
import com.eauction.product.query.api.queries.FindBidsByProductQuery;
import com.eauction.product.query.api.queries.FindProductDetailsQuery;
import com.eauction.product.query.api.queries.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class QueryApplication {
	@Autowired
	private QueryDispatcher queryDispatcher;

	@Autowired
	private QueryHandler queryHandler;

	public static void main(String[] args) {
		SpringApplication.run(QueryApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {
		queryDispatcher.registerHandler(FindBidsByProductQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindProductDetailsQuery.class, queryHandler::handle);
	}
}
