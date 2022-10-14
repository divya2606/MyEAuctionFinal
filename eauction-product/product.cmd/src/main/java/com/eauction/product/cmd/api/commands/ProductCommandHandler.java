package com.eauction.product.cmd.api.commands;

import com.eauction.cqrs.core.handlers.EventSourcingHandler;
import com.eauction.product.cmd.domain.ProductAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductCommandHandler implements CommandHandler{
    @Autowired
    private EventSourcingHandler<ProductAggregate> eventSourcingHandler;

    @Override
    public void handle(AddProductCommand command) {
        var aggregate = new ProductAggregate(command);
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(BidProductCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getProductId());
        Date today = new Date();
        if (today.compareTo(aggregate.getBidEndDate()) > 0) {
            throw new IllegalStateException("Bid is placed after Bid End Date");
        }
        aggregate = new ProductAggregate(command);
        //aggregate.bidProduct(command.getBuyerFName(), command.getBuyerLName(), command.getBuyerAddr(), command.getBuyerCity(), command.getBuyerState(), command.getBuyerPin(), command.getBuyerPhone(), command.getBuyerEmail(), command.getProductId(), command.getBidAmount());
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(UpdateBidProductCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getProductId());
        Date today = new Date();
        if (today.compareTo(aggregate.getBidEndDate()) > 0) {
            throw new IllegalStateException("Cannot update bid after Bid End Date");
        }
        aggregate.updateBidProduct(command.getId(), command.getProductId(), command.getBEmail(), command.getBidAmount());
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(DeleteProductCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        Date today = new Date();
        if (today.compareTo(aggregate.getBidEndDate()) > 0) {
            throw new IllegalStateException("Cannot delete product after Bid End Date");
        }
        aggregate.deleteProduct();
        eventSourcingHandler.save(aggregate);
    }
}
