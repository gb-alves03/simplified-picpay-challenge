package br.com.simplifiedpicpay.controllers;

import br.com.simplifiedpicpay.domains.user.User;
import br.com.simplifiedpicpay.dtos.UserDtoRequest;
import br.com.simplifiedpicpay.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<User>> getAllUsers(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        var page = service.findAllUsers(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") @RequestBody UUID id) {
        User user = service.findUserById(id);
        return ResponseEntity.ok().body(user);
    }



}
