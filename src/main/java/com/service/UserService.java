package com.service;

import com.dtos.UserDTO;
import com.exceptions.EntityNotFoundException;
import com.repository.UserRepository;
import com.repository.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public UserDTO create(UserDTO userDTO) {
        return UserDTO.deserialize(userRepository.save(User.deserialize(userDTO)));
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDTO::deserialize)
                .collect(Collectors.toList());
    }

    public UserDTO findById(Long id) {
        return userRepository.findById(id)
                .map(UserDTO::deserialize)
                .orElseThrow();
    }

    public void deleteById(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));

        userRepository.deleteById(id);
    }

    public UserDTO update(UserDTO userDTO) {
        var user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("User", userDTO.getId()));

        BeanUtils.copyProperties(userDTO, user, "id");

        return UserDTO.deserialize(userRepository.save(user));
    }
}
