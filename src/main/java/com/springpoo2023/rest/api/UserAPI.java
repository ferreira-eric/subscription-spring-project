package com.springpoo2023.rest.api;

import com.springpoo2023.dtos.UserDTO;
import com.springpoo2023.repository.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserAPI {

    @PostMapping("/user")
    ResponseEntity<User> saveUser(@RequestBody @Valid UserDTO userDTO);

    @GetMapping("/user/{id}")
    ResponseEntity<Optional<User>> getUserById(@PathVariable(value = "id") UUID idProduct);

    @GetMapping("/user/all")
    ResponseEntity<List<User>> getAllUser();

    @PutMapping("/user/{id}")
    ResponseEntity<Object> update(@PathVariable(value = "id") UUID idProduct,
                                  @RequestBody @Valid UserDTO userDTO);

    @DeleteMapping("/user/{id}")
    ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID idProduct);

}
