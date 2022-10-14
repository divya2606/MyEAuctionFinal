package com.eauction.product.query.api.dto;

import com.eauction.product.common.dto.BaseResponse;
import com.eauction.product.common.dto.CategoryType;
import com.eauction.product.query.domain.BuyerDetails;
import com.eauction.product.query.domain.EauctionProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsResponse extends BaseResponse {
    private List<EauctionProduct> products;

    public ProductDetailsResponse(String message) {
        super(message);
    }
}
