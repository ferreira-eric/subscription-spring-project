package com.springpoo2023.rest.controllers;

import com.springpoo2023.dtos.UserDTO;
import com.springpoo2023.repository.UserRepository;
import com.springpoo2023.repository.entity.User;
import com.springpoo2023.rest.api.UserAPI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/management")
public class UserController implements UserAPI {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<User> saveUser(UserDTO userDTO) {
        var user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }

    @Override
    public ResponseEntity<Optional<User>> getUserById(UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findById(id));
    }

    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    @Override
    public ResponseEntity<Object> update(UUID idProduct, UserDTO userDTO) {

        Optional<User> user = userRepository.findById(idProduct);

        if (user.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        //TODO id

        var users = user.get();
        BeanUtils.copyProperties(userDTO, users);

        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(users));
    }

    public ResponseEntity<Object> deleteUser(UUID idProduct) {

        Optional<User> user = userRepository.findById(idProduct);

        if (user.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        userRepository.deleteById(idProduct);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
