package com.project.show_flix.service;

import com.project.show_flix.dto.CreateUserRequest;
import com.project.show_flix.dto.UserResponse;
import com.project.show_flix.entity.User;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse createUser(CreateUserRequest user);

    UserResponse updateUser(Long id, CreateUserRequest user);

    void deleteUser(Long id);
}
