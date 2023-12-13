package com.example.nutribalance.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "food_calorie")
public class FoodCalorie {
    @Id
    @Column(unique = true, nullable = false)
    private String food_name;
    @Column
    private int calorie;

}
