package com.example.nutribalance.repositories;

import com.example.nutribalance.entities.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoachRepository extends JpaRepository<Coach, Long> {
    Optional<Coach> findByEmail(String email);
    boolean existsByEmail(String email);
    List<Coach> findByisapproved(int isApproved);
    @Override
    void deleteById(Long aLong);
}
