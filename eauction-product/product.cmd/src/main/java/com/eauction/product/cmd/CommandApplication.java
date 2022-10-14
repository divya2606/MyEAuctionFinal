package com.eauction.product.cmd;

import com.eauction.cqrs.core.infrastructure.CommandDispatcher;
import com.eauction.product.cmd.api.commands.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CommandApplication {
	@Autowired
	private CommandDispatcher commandDispatcher;

	@Autowired
	private CommandHandler commandHandler;

	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {
		commandDispatcher.registerHandler(AddProductCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(BidProductCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(UpdateBidProductCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(DeleteProductCommand.class, commandHandler::handle);
	}
}
