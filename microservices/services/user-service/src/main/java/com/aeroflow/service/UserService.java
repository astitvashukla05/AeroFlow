package com.aeroflow.service;

import com.aeroflow.payload.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getUserByEmail(String email) throws Exception;

    UserDTO getUserById(Long id) throws Exception;

    List<UserDTO> getAllUsers();
}
