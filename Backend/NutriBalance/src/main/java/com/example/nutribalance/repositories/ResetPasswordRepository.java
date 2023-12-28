package com.example.nutribalance.repositories;

import com.example.nutribalance.entities.ResetPassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResetPasswordRepository extends JpaRepository<ResetPassword, Long>{
    ResetPassword findByToken(String token);
    ResetPassword findByEmail(String email);
}
