package com.eauction.product.cmd.api.commands;

import com.eauction.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class UpdateBidProductCommand extends BaseCommand {
    private String productId;
    private String bEmail;
    private double bidAmount;
    public UpdateBidProductCommand(String id, String productId, String bEmail, double bidAmount) {
        super(id);
        this.productId = productId;
        this.bEmail = bEmail;
        this.bidAmount = bidAmount;
    }
}
