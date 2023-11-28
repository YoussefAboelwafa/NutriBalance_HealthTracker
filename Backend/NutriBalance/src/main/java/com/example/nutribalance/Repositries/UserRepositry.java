package com.example.nutribalance.Repositries;

import com.example.nutribalance.Entities.Coach;
import com.example.nutribalance.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositry extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
