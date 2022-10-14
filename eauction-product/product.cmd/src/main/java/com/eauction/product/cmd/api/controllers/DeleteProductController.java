package com.eauction.product.cmd.api.controllers;

import com.eauction.cqrs.core.exceptions.AggregateNotFoundException;
import com.eauction.cqrs.core.infrastructure.CommandDispatcher;
import com.eauction.product.cmd.api.commands.DeleteProductCommand;
import com.eauction.product.common.dto.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/buyer/delete")
public class DeleteProductController {
    private final Logger logger = Logger.getLogger(DeleteProductController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> deleteProduct(@PathVariable(value = "id") String id) {
        try {
            commandDispatcher.send(new DeleteProductCommand(id));
            return new ResponseEntity<>(new BaseResponse("Delete product request successfully completed!"), HttpStatus.OK);
        } catch (IllegalStateException | AggregateNotFoundException e) {
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            var safeErrorMessage = MessageFormat.format("Error while processing request to delete product for id - {0}.", id);
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
