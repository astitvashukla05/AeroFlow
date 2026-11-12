package com.aeroflow.service;

import com.aeroflow.payload.dto.UserDTO;
import com.aeroflow.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse login(String email, String password) throws Exception;

    AuthResponse signup(UserDTO req) throws Exception;
}
