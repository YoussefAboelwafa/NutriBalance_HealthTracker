package com.example.nutribalance.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "coach")
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coach_id;
    @Column
    private String user_name;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String contact_number;
    @Column
    private String Description;
    @Lob
    @Column(name = "cv", length = 1000)
    private byte[] cv;
    @Column
    private int rating;
    @Column
    private String price;
    @Column
    private int no_users_subscribed;
    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL)
            @JoinTable(
                    name="subscription",
                    joinColumns = @JoinColumn(name="coach_id"),
                    inverseJoinColumns = @JoinColumn(name="user_id")

            )
    List<User> users;
    public void addUsertoCoach(User user) {
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(user);
        user.getCoaches().add(this);
    }
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="report",
            joinColumns = @JoinColumn(name="coach_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")

    )
    List<User> users_reports;
@OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    List<Plan> plans;



}
