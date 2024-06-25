package br.com.simplifiedpicpay.services;

import br.com.simplifiedpicpay.domains.user.User;
import br.com.simplifiedpicpay.dtos.UserDtoRequest;
import br.com.simplifiedpicpay.enums.UserType;
import br.com.simplifiedpicpay.infra.exceptions.InsufficientBalanceException;
import br.com.simplifiedpicpay.infra.exceptions.UnauthorizedUserException;
import br.com.simplifiedpicpay.infra.exceptions.UserNotFoundException;
import br.com.simplifiedpicpay.mapper.UserMapper;
import br.com.simplifiedpicpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    public void validateTransaction(User payer, BigDecimal amount) {
        if (payer.getUserType().equals(UserType.MERCHANT)) {
            throw new UnauthorizedUserException("User of Merchant type is not authorized to realize the transaction!");
        }

        if (payer.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient Balance");
        }
    }

    public User createUser(UserDtoRequest userDtoRequest) {
        User user = new User(userDtoRequest);
        this.saveUser(user);

        return user;
    }

    public User findUserById(UUID id) {
        return repository.findUserById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public List<User> findAllUsers() {
        return repository.findAll();
    }

    public void saveUser(User user) {
        repository.save(user);
    }
}
