package br.com.simplifiedpicpay.services;

import br.com.simplifiedpicpay.domains.user.User;
import br.com.simplifiedpicpay.dtos.UserDtoRequest;
import br.com.simplifiedpicpay.enums.WalletType;
import br.com.simplifiedpicpay.infra.exceptions.InsufficientBalanceException;
import br.com.simplifiedpicpay.infra.exceptions.UnauthorizedUserException;
import br.com.simplifiedpicpay.infra.exceptions.UserAlreadyExistsException;
import br.com.simplifiedpicpay.infra.exceptions.UserNotFoundException;
import br.com.simplifiedpicpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void validateTransaction(User payer, BigDecimal amount) {
        if (payer.getWalletType().equals(WalletType.MERCHANT)) {
            throw new UnauthorizedUserException("User of Merchant type is not authorized to realize the transaction!");
        }

        if (payer.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient Balance");
        }
    }

    public User createUser(UserDtoRequest userDtoRequest) {
        var userDb = repository.findUserByDocumentOrEmail(userDtoRequest.document(), userDtoRequest.email());

        if (userDb.isPresent()) {
            throw new UserAlreadyExistsException("Already exists an user with this document or email");
        }

        String encryptedPassword = passwordEncoder.encode(userDtoRequest.password());

        User user = new User(userDtoRequest);
        user.setPassword(encryptedPassword);
        this.saveUser(user);

        return user;
    }

    public User findUserById(UUID id) {
        return repository.findUserById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public Page<User> findAllUsers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void saveUser(User user) {
        repository.save(user);
    }
}
