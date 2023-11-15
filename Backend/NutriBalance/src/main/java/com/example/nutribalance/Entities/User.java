package com.example.nutribalance.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String user_name;
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
    @ManyToMany(mappedBy = "users")
    List<Coach> coaches;

    @ManyToMany(mappedBy = "users_reports")
    List<Coach> coaches_reports;


}
