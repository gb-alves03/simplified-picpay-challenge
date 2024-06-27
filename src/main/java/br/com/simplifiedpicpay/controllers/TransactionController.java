package br.com.simplifiedpicpay.controllers;

import br.com.simplifiedpicpay.domains.transaction.Transaction;
import br.com.simplifiedpicpay.dtos.TransactionDtoRequest;
import br.com.simplifiedpicpay.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDtoRequest transactionDto) {
        Transaction transaction = this.transactionService.createTransaction(transactionDto);

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
}
