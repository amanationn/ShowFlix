package com.project.show_flix.service;

import com.project.show_flix.dto.CreateUserRequest;
import com.project.show_flix.dto.UserResponse;
import com.project.show_flix.entity.User;
import com.project.show_flix.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToResponse).toList();
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToResponse(user);
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return mapToResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUser(Long id, CreateUserRequest request) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setUsername(request.getUsername());
        existingUser.setEmail(request.getEmail());
        existingUser.setPassword(request.getPassword());
        return mapToResponse(userRepository.save(existingUser));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserResponse mapToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        return response;
    }
}
