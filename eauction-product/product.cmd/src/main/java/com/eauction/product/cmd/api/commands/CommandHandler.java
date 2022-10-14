package com.eauction.product.cmd.api.commands;

public interface CommandHandler {
    void handle(AddProductCommand command);
    void handle(BidProductCommand command);
    void handle(UpdateBidProductCommand command);
    void handle(DeleteProductCommand command);
}
