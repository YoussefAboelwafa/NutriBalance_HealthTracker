package com.example.nutribalance.Repositries;

import com.example.nutribalance.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.OptionalInt;

public interface UserRepositry extends JpaRepository<User, Long>{
    Optional <User> findByEmail(String username);
    Optional <User> findByUsername(String username);
}
