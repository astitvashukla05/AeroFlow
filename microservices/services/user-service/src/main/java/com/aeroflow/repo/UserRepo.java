package com.aeroflow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeroflow.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
