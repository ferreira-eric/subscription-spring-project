package com.rest.controllers;

import com.dtos.UserDTO;
import com.rest.api.UserAPI;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/management")
public class UserController implements UserAPI {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<Object> createUser(UserDTO userDTO) {
        var user = userService.create(userDTO);
        return ok(user);
    }

    @Override
    public ResponseEntity<?> getUserById(Long id) {
        return ok(userService.findById(id));
    }

    @Override
    public ResponseEntity<List<?>> getAllUser() {
        return ok(userService.findAll());
    }

    @Override
    public ResponseEntity<Object> updateUser(UserDTO userDTO) {
        var user = userService.update(userDTO);

        return ok(user);
    }

    @Override
    public ResponseEntity<?> deleteUser(Long idUser) {
        userService.deleteById(idUser);

        return ok().build();
    }
}
