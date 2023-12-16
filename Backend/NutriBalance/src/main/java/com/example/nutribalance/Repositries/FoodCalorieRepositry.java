package com.example.nutribalance.Repositries;

import com.example.nutribalance.Entities.FoodCalorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCalorieRepositry extends JpaRepository<FoodCalorie,String> {
}
