package com.example.nutribalance.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "chat")
@IdClass(ChatID.class)
public class Chat {
    @Id
    @Column(name = "user_id")
    private Long user;

    @Id
    @Column(name = "coach_id")
    private Long coach;

    @Id
    @Column(name = "chat_date_time")
    private LocalDateTime localDateTime;
    @Column(length = 10000)
    private String message;
    @Column
    private String sent_by;
    @Column
    private int seen;

}
