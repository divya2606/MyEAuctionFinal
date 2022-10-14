package com.eauction.product.cmd.api.controllers;

import com.eauction.cqrs.core.exceptions.AggregateNotFoundException;
import com.eauction.cqrs.core.infrastructure.CommandDispatcher;
import com.eauction.product.cmd.api.commands.BidProductCommand;
import com.eauction.product.cmd.api.commands.UpdateBidProductCommand;
import com.eauction.product.common.dto.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/buyer/update-bid")
public class UpdateBidController {
    private final Logger logger = Logger.getLogger(UpdateBidController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    @PutMapping(path = "/{productId}/{buyerEmail}/{newBidAmount}")
    public ResponseEntity<BaseResponse> updateBid(@PathVariable(value = "productId") String productId,
                                                   @PathVariable(value = "buyerEmail") String bEmail,
                                                   @PathVariable(value = "newBidAmount") double newBid)
                                                   //@RequestBody UpdateBidProductCommand command)
                                                  {
        try {
            //command.setId(id);
            //command.setBidAmount(newBid);
            var id = UUID.randomUUID().toString();
            commandDispatcher.send(new UpdateBidProductCommand(id, productId, bEmail, newBid));
            return new ResponseEntity<>(new BaseResponse("Update bid request completed successfully!"), HttpStatus.OK);
        } catch (IllegalStateException | AggregateNotFoundException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            var safeErrorMessage = MessageFormat.format("Error while processing request to update bid for id - {0}.", productId);
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
