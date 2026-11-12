package com.aeroflow.service.impl;

import com.aeroflow.mapper.UserMapper;
import com.aeroflow.model.User;
import com.aeroflow.payload.dto.UserDTO;
import com.aeroflow.repo.UserRepo;
import com.aeroflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public UserDTO getUserByEmail(String email) throws Exception {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new Exception("user not found with email");
        }

        return UserMapper.toDTO(user);
    }

    @Override
    public UserDTO getUserById(Long id) throws Exception {
        User user = userRepo.findById(id).orElseThrow(
                () -> new Exception("user not found with id " + id));
        return UserMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        return UserMapper.toDTOList(users);
    }
}
