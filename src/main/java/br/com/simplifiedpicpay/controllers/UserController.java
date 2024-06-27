package br.com.simplifiedpicpay.controllers;

import br.com.simplifiedpicpay.domains.user.User;
import br.com.simplifiedpicpay.dtos.UserDtoRequest;
import br.com.simplifiedpicpay.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDtoRequest userDtoRequest) {
        User user = service.createUser(userDtoRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(service.findAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") @RequestBody UUID id) {
        User user = service.findUserById(id);
        return ResponseEntity.ok().body(user);
    }

}
