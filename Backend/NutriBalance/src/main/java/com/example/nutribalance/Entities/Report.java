package com.example.nutribalance.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private String message;
}
