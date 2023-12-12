package com.example.nutribalance.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
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
    @Column
    boolean isEnabled=false;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Weight> weights;
    @Lob
    @Column(name = "image", columnDefinition = "longblob")
    private byte[] image;
    @ManyToOne
    @JoinColumn(name = "planName")
    private Plan plan;
    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;
    @ManyToMany(mappedBy = "users_reports")
    List<Coach> coaches_reports;
    @Column
    private String comment;
}
