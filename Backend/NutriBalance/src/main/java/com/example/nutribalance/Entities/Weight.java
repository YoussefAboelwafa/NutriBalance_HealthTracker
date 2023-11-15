package com.example.nutribalance.Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "weights")
@IdClass(WeightId.class)
public class Weight {
@Id
@Temporal(TemporalType.DATE)
    private Date date;
@Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private String weight;
}
