package com.eauction.product.cmd.api.commands;

import com.eauction.cqrs.core.commands.BaseCommand;
import com.eauction.product.common.dto.CategoryType;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class AddProductCommand extends BaseCommand{
    @NotNull(message = "Product Name should not be null")
    @Size(min = 5, max = 30, message = "Invalid Product Name. Should be 5 to 30 chars")
    private String productName;
    private String shortDesc;
    private String detailedDesc;
    private CategoryType category;
    //@Pattern(regexp = "^\\d.$", message = "Invalid Start Price")
    @DecimalMin(value = "0.0", message = "Start Price should be number")
    private double startPrice;
    @Future(message = "Bid End Date should be future date")
    private Date bidEndDate;
    //seller info
    @NotNull(message = "Seller First Name should not be null")
    @Size(min = 5, max = 30, message = "Invalid Seller First Name. Should be 5 to 30 chars")
    private String sellerFName;
    @NotNull(message = "Seller Last Name should not be null")
    @Size(min = 3, max = 25, message = "Invalid Seller Last Name. Should be 3 to 25 chars")
    private String sellerLName;
    private String sellerAddr;
    private String sellerCity;
    private String sellerState;
    private String sellerPin;
    @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number")
    private String sellerPhone;
    @NotNull(message = "Seller Email should not be null")
    @Email(message = "Invalid email address")
    private String sellerEmail;
}
