package com.eauction.product.query.api.dto;

import com.eauction.product.common.dto.BaseResponse;
import com.eauction.product.query.domain.EauctionProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductIdsReponse extends BaseResponse {
    private List productIds;

    public ProductIdsReponse(String message) {
        super(message);
    }
}
