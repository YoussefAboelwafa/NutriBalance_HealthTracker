package com.example.nutribalance.repositories;

import com.example.nutribalance.entities.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan,String> {
    Plan findByPlanName(String planName);
}
