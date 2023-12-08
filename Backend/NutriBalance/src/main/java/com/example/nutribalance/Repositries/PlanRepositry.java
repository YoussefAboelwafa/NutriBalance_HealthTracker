package com.example.nutribalance.Repositries;

import com.example.nutribalance.Entities.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepositry extends JpaRepository<Plan,String> {
    Plan findByPlanName(String planName);
}
