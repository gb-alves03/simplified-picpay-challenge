package br.com.simplifiedpicpay.services;

import br.com.simplifiedpicpay.domains.transaction.Transaction;
import br.com.simplifiedpicpay.domains.user.User;
import br.com.simplifiedpicpay.dtos.TransactionDtoRequest;
import br.com.simplifiedpicpay.enums.WalletType;
import br.com.simplifiedpicpay.infra.exceptions.UnauthorizedTransactionException;
import br.com.simplifiedpicpay.services.utils.ApiResponse;
import br.com.simplifiedpicpay.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDtoRequest transactionDto) {
        User payer = userService.findUserById(transactionDto.payerId());
        User receiver = userService.findUserById(transactionDto.receiverId());

        userService.validateTransaction(payer, transactionDto.amount());

        boolean isAuthorized = this.authorizeTransaction(payer, transactionDto.amount());
        if (!isAuthorized) {
            throw new UnauthorizedTransactionException("Unauthorized transaction");
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDto.amount());
        transaction.setSender(payer);
        transaction.setReceiver(receiver);
        transaction.setRealizedAt(LocalDateTime.now());

        payer.setBalance(payer.getBalance().subtract(transactionDto.amount()));
        receiver.setBalance(receiver.getBalance().add(transactionDto.amount()));

        this.repository.save(transaction);
        this.userService.saveUser(payer);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(payer, "Transaction was sent with success");
        this.notificationService.sendNotification(receiver, "Transaction was received with success");

        return transaction;
    }

    public boolean authorizeTransaction(User payer, BigDecimal value) {
        ResponseEntity<ApiResponse> authorizationResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", ApiResponse.class);

        if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
            ApiResponse responseBody = authorizationResponse.getBody();
            if (responseBody != null && responseBody.getData() != null) {
                String status = responseBody.getStatus();
                boolean authorization = responseBody.getData().isAuthorized();

                System.out.println("Status: " + status);
                System.out.println("Authorization: " + authorization);

                return authorization;
            }
        }
        return false;
    }
}
