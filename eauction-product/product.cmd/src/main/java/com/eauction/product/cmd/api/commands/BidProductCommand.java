package com.eauction.product.cmd.api.commands;

import com.eauction.cqrs.core.commands.BaseCommand;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class BidProductCommand extends BaseCommand {
    //buyer info
    @NotNull(message = "Buyer First Name should not be null")
    @Size(min = 5, max = 30, message = "Invalid Buyer First Name. Should be 5 to 30 chars")
    private String buyerFName;
    @NotNull(message = "Buyer Last Name should not be null")
    @Size(min = 3, max = 25, message = "Invalid Buyer Last Name. Should be 3 to 25 chars")
    private String buyerLName;
    private String buyerAddr;
    private String buyerCity;
    private String buyerState;
    private String buyerPin;
    @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number")
    private String buyerPhone;
    @NotNull(message = "Buyer Email should not be null")
    @Email(message = "Invalid email address")
    private String buyerEmail;
    private String productId;
    private double bidAmount;
}
