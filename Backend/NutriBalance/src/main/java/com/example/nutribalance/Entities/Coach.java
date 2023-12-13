package com.example.nutribalance.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private String username;
    @Column
    private String email;
    @Lob
    @Column(name = "image", columnDefinition = "longblob")
    private byte[] image;
    @Column
    private String password;
    @Column
    private String contact_number;
    @Column
    private String address;
    @Column(length = 10000)
    private String description;
    @Lob
    @Column(name = "cv", length = 2147483647)
    private byte[] cv;
    @Column
    private int rating;
    @Column
    private String price;
    @Column
    private int no_users_subscribed;
    @Column
    private int isapproved;
    @Column
    boolean isEnabled=false;
    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    @JsonIgnore
    List<User> users=new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="report",
            joinColumns = @JoinColumn(name="coach_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")

    )
    @JsonIgnore
    List<User> users_reports=new ArrayList<>();
    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Plan> plans;





}