package com.example.nutribalance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequest {
	private String name;
	private String email;
	private String password;
	private String phoneNumber;
	private String imageUrl;
	private String providerUserID;
	private String role;
}
