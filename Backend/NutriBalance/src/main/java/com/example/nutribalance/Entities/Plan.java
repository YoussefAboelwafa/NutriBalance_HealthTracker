package com.example.nutribalance.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "plan")
public class Plan {
    @Id
    @Column(unique = true, nullable = false)
    private String plan_name;
    @Column
    private String description;
    @Column
    private String Goal;
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    List<User> users;
    @JsonIgnoreProperties("plan")
    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;
}
