package com.example.nutribalance.Repositries;

import com.example.nutribalance.Entities.ResetPassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResetPasswordRepository extends JpaRepository<ResetPassword, Long>{
    ResetPassword findByToken(String token);
    ResetPassword findByEmail(String email);
}
