package com.example.nutribalance.Repositries;

import com.example.nutribalance.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositry extends JpaRepository<User, Long>{
}
