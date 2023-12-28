package com.example.nutribalance.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@IdClass(ReportId.class)
@Table(name = "report")
public class Report {
    @Id
    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private String message;
    @Column
    private String author;

}
