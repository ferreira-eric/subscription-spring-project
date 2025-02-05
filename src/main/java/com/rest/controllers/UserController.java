package com.rest.controllers;

import com.dtos.UserDTO;
import com.rest.api.UserAPI;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/management")
public class UserController implements UserAPI {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<Object> saveUser(UserDTO userDTO) {
        var user = userService.create(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Override
    public ResponseEntity<?> getUserById(UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @Override
    public ResponseEntity<List<?>> getAllUser() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @Override
    public ResponseEntity<Object> update(UserDTO userDTO) {
        var user = userService.update(userDTO);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Override
    public ResponseEntity<?> deleteUser(UUID idUser) {
        userService.deleteById(idUser);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
