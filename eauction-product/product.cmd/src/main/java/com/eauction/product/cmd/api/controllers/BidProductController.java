package com.eauction.product.cmd.api.controllers;

import com.eauction.cqrs.core.exceptions.AggregateNotFoundException;
import com.eauction.cqrs.core.infrastructure.CommandDispatcher;
import com.eauction.product.cmd.api.commands.BidProductCommand;
import com.eauction.product.cmd.api.dto.AddProductResponse;
import com.eauction.product.common.dto.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/buyer/place-bid")
public class BidProductController {
    private final Logger logger = Logger.getLogger(BidProductController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> bidProduct(@PathVariable(value = "id") String id,
                                                   @RequestBody @Valid BidProductCommand command) {
        try {
            var buyerId = UUID.randomUUID().toString();
            command.setId(buyerId);
            command.setProductId(id);
            commandDispatcher.send(command);
            return new ResponseEntity<>(new BaseResponse("Product bid request completed successfully!"), HttpStatus.OK);
        } catch (IllegalStateException | AggregateNotFoundException e) {
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            var safeErrorMessage = MessageFormat.format("Error while processing request to bid product for id - {0}.", id);
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
