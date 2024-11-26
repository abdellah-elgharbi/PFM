package com.example.backend_pfm.repository;

import com.example.backend_pfm.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRespository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
}
