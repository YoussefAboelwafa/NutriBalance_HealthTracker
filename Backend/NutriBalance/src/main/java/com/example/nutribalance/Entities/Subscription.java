package com.example.nutribalance.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "subscription")
@IdClass(SubscriptionId.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Subscription {
    @Id
    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Temporal(TemporalType.DATE)
    @Column
    private Date start_date;
    @Temporal(TemporalType.DATE)
    @Column
    private Date end_date;
    @Column
    private String comment;
}
