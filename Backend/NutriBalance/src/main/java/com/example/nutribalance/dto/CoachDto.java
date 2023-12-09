package com.example.nutribalance.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoachDto {
    private Long coach_id;
    private String username;
    private String email;
    private byte[] image;
    private String password;
    private String contact_number;
    private String address;
    private String description;
    private byte[] cv;
    private int rating;
    private String price;
    private int no_users_subscribed;
    private int isapproved;
}
