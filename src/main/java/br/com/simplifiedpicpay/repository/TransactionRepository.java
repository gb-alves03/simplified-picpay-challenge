package br.com.simplifiedpicpay.repository;

import br.com.simplifiedpicpay.domains.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
