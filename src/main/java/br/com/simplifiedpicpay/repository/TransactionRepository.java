package br.com.simplifiedpicpay.repository;

import br.com.simplifiedpicpay.domains.transaction.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findAll(Pageable pageable);

    Optional<Transaction> findTransactionById(Long id);
}
