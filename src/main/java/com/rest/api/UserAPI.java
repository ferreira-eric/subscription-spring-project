package com.rest.api;

import com.dtos.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserAPI {

    @PostMapping("/user")
    ResponseEntity<Object> saveUser(@RequestBody @Valid UserDTO userDTO);

    @GetMapping("/user/{id}")
    ResponseEntity<?> getUserById(@PathVariable(value = "id") Long idProduct);

    @GetMapping("/user/all")
    ResponseEntity<List<?>> getAllUser();

    @PutMapping("/user/{id}")
    ResponseEntity<Object> update(@RequestBody @Valid UserDTO userDTO);

    @DeleteMapping("/user/{id}")
    ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long idProduct);

}
