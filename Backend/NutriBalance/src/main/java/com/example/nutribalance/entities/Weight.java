package com.example.nutribalance.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@Data
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
    private double weight;
}
