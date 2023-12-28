package com.example.nutribalance.dto;

import lombok.Getter;

@Getter
public enum SocialProvider {

 	GOOGLE("google"), LOCAL("local");

	private final String providerType;

	SocialProvider(final String providerType) {
		this.providerType = providerType;
	}

}

