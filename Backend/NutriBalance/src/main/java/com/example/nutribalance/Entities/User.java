package com.example.nutribalance.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String contact_number;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Weight> weights;

    @ManyToOne
    @JoinColumn(name = "plan_name")
    private Plan plan;
   @JsonIgnoreProperties("users")
    @ManyToMany(mappedBy = "users")
    List<Coach> coaches;
    public void addCoach(Coach coach) {
        if (coaches == null) {
            coaches = new ArrayList<>();
        }
        coaches.add(coach);
        coach.getUsers().add(this);
    }
    @JsonIgnoreProperties("users_reports")
    @ManyToMany(mappedBy = "users_reports")
    List<Coach> coaches_reports;


}
