package com.elliotmcs.book_exchange_api.service;

import com.elliotmcs.book_exchange_api.model.User;
import com.elliotmcs.book_exchange_api.model.UserDTO;
import com.elliotmcs.book_exchange_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserById(UUID id) {
        return userRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UUID id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        user.setRating(userDTO.rating());
        user.setIsActive(userDTO.isActive());
        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    // Convert User Entity to UserDTO
    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRating(), user.getIsActive());
    }

    // Convert UserDTO to User Entity
    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        user.setRating(userDTO.rating());
        user.setIsActive(userDTO.isActive());
        return user;
    }
}