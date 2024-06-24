package br.com.simplifiedpicpay.infra;

import br.com.simplifiedpicpay.dtos.ExceptionDto;
import br.com.simplifiedpicpay.infra.exceptions.UnauthorizedUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity threatUnauthorizedUser(UnauthorizedUserException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authorized");
    }
}
