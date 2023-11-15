package com.example.nutribalance.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long admin_id;
    @Column
    private String user_name;
    @Column
    private String email;
    @Column
    private String password;
}
