package br.com.simplifiedpicpay.repository;

import br.com.simplifiedpicpay.domains.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Page<User> findAll(Pageable pageable);

    Optional<User> findUserById(UUID id);

    Optional<User> findUserByDocumentOrEmail(String document, String email);
}
