package br.com.simplifiedpicpay.controllers;

import br.com.simplifiedpicpay.infra.exceptions.InsufficientBalanceException;
import br.com.simplifiedpicpay.infra.exceptions.UnauthorizedUserException;
import br.com.simplifiedpicpay.infra.exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity threatUnauthorizedUser(UnauthorizedUserException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authorized");
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
}
