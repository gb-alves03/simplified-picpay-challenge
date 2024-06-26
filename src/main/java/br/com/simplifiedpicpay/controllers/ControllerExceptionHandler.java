package br.com.simplifiedpicpay.controllers;

import br.com.simplifiedpicpay.infra.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity threatUnauthorizedUser(UnauthorizedUserException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authorized to realize the transaction");
    }

    @ExceptionHandler
    public ResponseEntity threatAlreadyExistentUser(UserAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already exists an user with this document or email");
    }

    @ExceptionHandler
    public ResponseEntity threatInsufficientBalance(InsufficientBalanceException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The user has insufficient balance to realize the transaction");
    }

    @ExceptionHandler
    public ResponseEntity threatAuthorizationFalse(HttpClientErrorException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"status\" : \"fail\", \"data\" : { \"authorization\" : false }}\"");
    }

    @ExceptionHandler
    public ResponseEntity threatTransactionNotFound(TransactionNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found");
    }

    @ExceptionHandler
    public ResponseEntity threatUserNotFound(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
}
