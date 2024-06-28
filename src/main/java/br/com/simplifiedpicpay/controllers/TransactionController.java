package br.com.simplifiedpicpay.controllers;

import br.com.simplifiedpicpay.domains.transaction.Transaction;
import br.com.simplifiedpicpay.dtos.TransactionDtoRequest;
import br.com.simplifiedpicpay.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<Page<Transaction>> getAllTransactions(@PageableDefault(size = 10, sort = {"realizedAt"}) Pageable pageable) {
        var page = transactionService.findAllTransactions(pageable);

        return ResponseEntity.ok(page);
    }

    @GetMapping("{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") @RequestBody Long id) {
        Transaction transaction = transactionService.findTransactionById(id);

        return ResponseEntity.ok().body(transaction);
    }
}
