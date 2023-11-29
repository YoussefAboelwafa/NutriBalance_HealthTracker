package com.example.nutribalance.service;

import com.example.nutribalance.dto.LocalUser;
import com.example.nutribalance.dto.SignUpRequest;
//import com.example.nutribalance.exception.UserAlreadyExistAuthenticationException;
import com.example.nutribalance.Entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import java.util.Map;
import java.util.Optional;

public interface UserService{

	public User registerNewUser(SignUpRequest signUpRequest) throws Exception;

	User findUserByEmail(String email);

	Optional<User> findUserById(Long id);

	LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) throws Exception;
}
