package com.example.nutribalance.repositories;

import com.example.nutribalance.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    @Override
    void deleteById(Long aLong);
}
