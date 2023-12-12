package com.example.nutribalance.Entities;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table
@Data
public class ResetPassword {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column
    private String token;
    @Column
    private String username;
    @Column
    private String email;
}
