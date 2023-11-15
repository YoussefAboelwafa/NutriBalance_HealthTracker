package com.example.nutribalance.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "food_calorie")
public class FoodCalorie {
    @Id
    @Column(unique = true, nullable = false)
    private String food_name;
    @Column
    private int calorie;

}
