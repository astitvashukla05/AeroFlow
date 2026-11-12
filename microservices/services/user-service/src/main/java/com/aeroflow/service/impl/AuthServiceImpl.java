package com.aeroflow.service.impl;

import java.time.LocalDateTime;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aeroflow.config.JwtProvider;
import com.aeroflow.enums.UserRole;
import com.aeroflow.mapper.UserMapper;
import com.aeroflow.model.User;
import com.aeroflow.payload.dto.UserDTO;
import com.aeroflow.payload.response.AuthResponse;
import com.aeroflow.repo.UserRepo;
import com.aeroflow.service.AuthService;
import com.aeroflow.service.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService customUserDetailsService;

    // Email already exists
    // Encode the pass using bcrypt
    // save user in db
    // generate jwt token
    // return auth response
    @Override
    public AuthResponse signup(UserDTO req) throws Exception {

        User existingUser = userRepo.findByEmail(req.getEmail());
        if (existingUser != null) {
            throw new Exception("email already registered!");
        }
        if (req.getRole() == UserRole.ROLE_SYSTEM_ADMIN) {
            throw new Exception("You cannot sign up system admins!");
        }

        User newUser = User.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .phone(req.getPhone())
                .role(req.getRole())
                .fullName(req.getFullName())
                .lastLogin(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        User savedUser = userRepo.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                savedUser.getEmail(), savedUser.getPassword());

        String jwt = jwtProvider.generateToken(
                authentication, savedUser.getId());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setUser(UserMapper.toDTO(savedUser));
        authResponse.setTitle("Welcome " + savedUser.getFullName());
        authResponse.setMessage("Registered Successfully!");

        return authResponse;

    }

    @Override
    public AuthResponse login(String email, String password) throws Exception {
        Authentication authentication = authenticate(email, password);

        User user = userRepo.findByEmail(email);
        user.setLastLogin(LocalDateTime.now());
        userRepo.save(user);

        String jwt = jwtProvider.generateToken(authentication, user.getId());

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setUser(UserMapper.toDTO(user));
        authResponse.setTitle("Welcome Back " + user.getFullName());
        authResponse.setMessage("Login Successfully!");
        return authResponse;
    }

    private Authentication authenticate(String email, String password) throws Exception {

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        if (!passwordEncoder.matches(
                password, userDetails.getPassword())) {
            throw new Exception("invalid password!");
        }
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());

    }

}
