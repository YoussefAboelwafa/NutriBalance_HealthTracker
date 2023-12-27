package com.example.nutribalance.Repositries;

import com.example.nutribalance.Entities.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoachRepositry extends JpaRepository<Coach, Long> {
    Optional<Coach> findByEmail(String email);
    Optional<Coach> findByUsername(String user_name);

    boolean existsByEmail(String email);
    List<Coach> findByisapproved(int isApproved);

    @Override
    void deleteById(Long aLong);
}
