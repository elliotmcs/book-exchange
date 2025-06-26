package com.elliotmcs.book_exchange_api.service;

import com.elliotmcs.book_exchange_api.model.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<UserDTO> getAllUsers();
    Optional<UserDTO> getUserById(UUID id);
    UserDTO saveUser(UserDTO userDTO);
    UserDTO updateUser(UUID id, UserDTO userDTO);
    void deleteUser(UUID id);
}