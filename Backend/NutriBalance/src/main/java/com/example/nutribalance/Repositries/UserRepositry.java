package com.example.nutribalance.Repositries;

import com.example.nutribalance.Entities.Coach;
import com.example.nutribalance.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import java.util.Optional;
import java.util.OptionalInt;

public interface UserRepositry extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional <User> findByUsername(String username);

    @Override
    void deleteById(Long aLong);
}
