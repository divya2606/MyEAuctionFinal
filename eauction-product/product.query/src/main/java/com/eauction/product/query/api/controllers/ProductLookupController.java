package com.eauction.product.query.api.controllers;

import com.eauction.cqrs.core.infrastructure.QueryDispatcher;
import com.eauction.product.common.dto.BaseResponse;
import com.eauction.product.query.api.dto.ProductDetailsResponse;
import com.eauction.product.query.api.dto.ProductIdsReponse;
import com.eauction.product.query.api.dto.ProductLookupResponse;
import com.eauction.product.query.api.queries.FindAllProductIdsQuery;
import com.eauction.product.query.api.queries.FindBidsByProductQuery;
import com.eauction.product.query.api.queries.FindProductDetailsQuery;
import com.eauction.product.query.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

//@CrossOrigin(origins = "http://localhost:3000","http://div-eauction-react-app.s3-website.ap-south-1.amazonaws.com/")
@CrossOrigin(origins = "http://div-eauction-react-app.s3-website.ap-south-1.amazonaws.com/")
@RestController
@RequestMapping(path = "/api/v1/productLookup")
public class ProductLookupController {
    private final Logger logger = Logger.getLogger(ProductLookupController.class.getName());

    @Autowired
    private QueryDispatcher queryDispatcher;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDBRepository productDBRepository;

    @Autowired
    private BuyerDBRepository buyerDBRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @GetMapping(path = "/byId/{id}")
    public ResponseEntity<ProductLookupResponse> getBidsById(@PathVariable(value = "id") String id) {
        try {
            List<BuyerDetails> bids = queryDispatcher.send(new FindBidsByProductQuery(id));
            if (bids == null || bids.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            var response = ProductLookupResponse.builder()
                    .bids(bids)
                    .message("Successfully returned bids!")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get bids by ID request!";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new ProductLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/getProductDetailsById/{id}")
    public ResponseEntity<ProductDetailsResponse> getProductDetailsById(@PathVariable(value = "id") String id) {
        try {
            List<EauctionProduct> products = queryDispatcher.send(new FindProductDetailsQuery(id));
            if (products == null || products.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            /*List<BuyerDetails> bids = queryDispatcher.send(new FindBidsByProductQuery(id));
            if (bids == null || bids.size() == 0) {
                bids = new ArrayList<BuyerDetails>();
                //return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }*/
            var response = ProductDetailsResponse.builder()
                    .products(products)
                    //.bids(bids)
                    .message("Successfully returned product and bids details!")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get product and bids details request!";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new ProductDetailsResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/getProductIds")
    public @ResponseBody List<String> getAllEmail() {
        return productRepository.getAllIds();
    }

    @PostMapping(path = "/addProductDB")
    public ResponseEntity addProductDB(@RequestBody @Valid EauctionProduct product) {
        var id = UUID.randomUUID().toString();
        product.setId(id);
        productDBRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product add request completed successfully!");
    }

    @PutMapping(path = "/bidProductDB/{id}")
    public ResponseEntity bidProductDB(@PathVariable(value = "id") String id,
                                       @RequestBody @Valid BuyerDetails buyerDetails) {
        var buyerId = UUID.randomUUID().toString();
        buyerDetails.setId(buyerId);
        buyerDetails.setProductId(id);
        buyerDBRepository.save(buyerDetails);
        return ResponseEntity.status(HttpStatus.OK).body("Product bid request completed successfully!");
    }

    @PutMapping(path = "/updateBidDB/{productId}/{buyerEmail}/{newBidAmount}")
    public ResponseEntity updateBidDB(@PathVariable(value = "productId") String productId,
                                      @PathVariable(value = "buyerEmail") String bEmail,
                                      @PathVariable(value = "newBidAmount") double newBid) {
        List<BuyerDetails> buyerDetails = buyerDBRepository.findByBuyerEmail(bEmail);
        if (buyerDetails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client made a bad request");
        }
        for (BuyerDetails l:buyerDetails) {
            if (l.getProductId().equalsIgnoreCase(productId)) {
                l.setBidAmount(newBid);
                buyerDBRepository.save(l);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("Update bid request completed successfully!");
    }

    @DeleteMapping(path = "/deleteProductDB/{id}")
    public ResponseEntity deleteProductDB(@PathVariable(value = "id") String id) {
        List<BuyerDetails> buyerDetails = buyerDBRepository.findByProductId(id);
        if (buyerDetails.isEmpty()) {
            productDBRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Delete product request successfully completed!");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot delete product. Bid is placed for this product!");
        }
    }

    /*public ResponseEntity<ProductIdsReponse> getProductIds() {
        try {
            List productIds = queryDispatcher.send(new FindAllProductIdsQuery());
            if (productIds == null || productIds.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            var response = ProductIdsReponse.builder()
                    .productIds(productIds)
                    .message("Successfully returned product ids!")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get product ids request!";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new ProductIdsReponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}
