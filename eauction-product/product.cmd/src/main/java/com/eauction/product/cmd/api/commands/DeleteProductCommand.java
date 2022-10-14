package com.eauction.product.cmd.api.commands;

import com.eauction.cqrs.core.commands.BaseCommand;

public class DeleteProductCommand extends BaseCommand {
    public DeleteProductCommand(String id) {
        super(id);
    }
}
