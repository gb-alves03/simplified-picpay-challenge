package br.com.simplifiedpicpay.repository;

import br.com.simplifiedpicpay.domains.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}
