package com.example.nutribalance.util;

import com.example.nutribalance.dto.LocalUser;
import com.example.nutribalance.dto.UserInfo;
import com.example.nutribalance.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GeneralUtils {

	public static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Set<String> roles) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
	public static UserInfo buildUserInfo(LocalUser localUser) {
		List<String> roles = localUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
		User user = localUser.getUser();
		return new UserInfo(String.valueOf(user.getUser_id()), user.getUsername(), user.getEmail(), roles);
	}
}
