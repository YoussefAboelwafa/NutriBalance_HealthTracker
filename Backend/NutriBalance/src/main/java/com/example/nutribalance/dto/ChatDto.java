package com.example.nutribalance.dto;

import lombok.Data;

@Data
public class ChatDto {
    Long user_id;
    Long coach_id;
    String message;
    String sent_by;
    int seen;
}
