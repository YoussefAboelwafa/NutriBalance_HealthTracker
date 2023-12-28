package com.example.nutribalance.security.securityService;

import com.example.nutribalance.entities.User;
import com.example.nutribalance.repositories.CoachRepository;
import com.example.nutribalance.repositories.UserRepository;
import com.example.nutribalance.dto.LocalUser;
import com.example.nutribalance.dto.SignUpRequest;
import com.example.nutribalance.security.oauth2.user.OAuth2UserInfo;
import com.example.nutribalance.security.oauth2.user.OAuth2UserInfoFactory;
import com.example.nutribalance.security.securityService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CoachRepository coachRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional()
	public User registerNewUser(final SignUpRequest signUpRequest) throws Exception {
		if (signUpRequest == null) {
			throw new Exception("User is null");
		}
		if(signUpRequest.getRole().equals("USER") ){
			if (userRepository.existsByEmail(signUpRequest.getEmail())) {
				throw new Exception("Display name already exists: " + signUpRequest.getEmail());
			}
		}
		else if(signUpRequest.getRole().equals("COACH")){
			if (coachRepository.existsByEmail(signUpRequest.getEmail())) {
				throw new Exception("Display name already exists: " + signUpRequest.getEmail());
			}
		}

		User user = buildUser(signUpRequest);
		user = userRepository.save(user);
		userRepository.flush();
		return user;
	}

	private User buildUser(final SignUpRequest formDTO) {
		User user = new User();
		user.setEmail(formDTO.getEmail());
		user.setContact_number(formDTO.getPhoneNumber());
		user.setPassword(passwordEncoder.encode(formDTO.getPassword()));
		user.setUsername(formDTO.getName());
		return user;
	}

	@Override
	public User findUserByEmail(final String email) {
		return userRepository.findByEmail(email).orElse(null);
	}

	@Override
	@Transactional
	public LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) throws Exception {
		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);
		if (StringUtils.isEmpty(oAuth2UserInfo.getName())) {
			throw new IllegalStateException("Name not found from OAuth2 provider");
		} else if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
			throw new IllegalStateException("Email not found from OAuth2 provider");
		}

		System.out.println("s---------------------------: " +oAuth2UserInfo.getAttributes());

		SignUpRequest userDetails = toUserRegistrationObject(registrationId, oAuth2UserInfo);
		User user = findUserByEmail(oAuth2UserInfo.getEmail());
		if (user != null) {

			user = updateExistingUser(user, oAuth2UserInfo);
		} else {
			user = registerNewUser(userDetails);
		}

		return LocalUser.create(user, attributes, idToken, userInfo);
	}

	private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
		existingUser.setUsername(oAuth2UserInfo.getName());
		return userRepository.save(existingUser);
	}

	private SignUpRequest toUserRegistrationObject(String registrationId, OAuth2UserInfo oAuth2UserInfo) {
		return SignUpRequest.builder()
				.providerUserID(oAuth2UserInfo.getId())
				.imageUrl(oAuth2UserInfo.getImageUrl())
				.phoneNumber(oAuth2UserInfo.getPhoneNumber())
				.email(oAuth2UserInfo.getEmail())
				.password("password")
				.name(oAuth2UserInfo.getName())
				.role("USER")
				.build();
	}

	@Override
	public Optional<User> findUserById(Long id) {
		return userRepository.findById(id);
	}

}
