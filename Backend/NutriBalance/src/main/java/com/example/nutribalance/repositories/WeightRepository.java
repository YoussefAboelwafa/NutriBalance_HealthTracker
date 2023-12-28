package com.example.nutribalance.repositories;

import com.example.nutribalance.entities.Weight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightRepository extends JpaRepository<Weight, Long> {
}
