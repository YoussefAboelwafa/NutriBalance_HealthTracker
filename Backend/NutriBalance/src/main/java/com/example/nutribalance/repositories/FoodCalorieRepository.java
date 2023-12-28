package com.example.nutribalance.repositories;

import com.example.nutribalance.entities.FoodCalorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCalorieRepository extends JpaRepository<FoodCalorie,String> {
}
