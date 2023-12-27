package com.example.nutribalance.security.config;


import com.example.nutribalance.security.jwt.TokenAuthenticationFilter;
import com.example.nutribalance.security.oauth2.*;
import com.example.nutribalance.service.LocalUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    private CustomOidcUserService customOidcUserService;

    @Autowired
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Autowired
    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private LocalUserDetailService userDetailsService;
//
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//    }


    @Bean
    public OAuth2AuthorizationRequestResolver customOAuth2AuthorizationRequestResolverBean() {
        return new CustomOAuth2AuthorizationRequestResolver(clientRegistrationRepository);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .cors(cors -> {
                    cors.configurationSource(request -> {
                        var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
                        corsConfiguration.setAllowedOrigins(java.util.List.of("http://localhost:4200"));
                        corsConfiguration.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                        corsConfiguration.setAllowedHeaders(java.util.List.of("*"));
                        return corsConfiguration;
                    });
                })
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint((request, response, authException) -> response.sendError(401))
                )
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/", "/error", "/api/all", "/api/auth/**", "/oauth2/**", "/api/**", "/**").permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2Login(
                        oauth2Login -> oauth2Login
                                .authorizationEndpoint(authorizationEndpoint -> {
                                            authorizationEndpoint.authorizationRequestRepository(cookieAuthorizationRequestRepository());
                                            authorizationEndpoint.authorizationRequestResolver(customOAuth2AuthorizationRequestResolverBean());

                                        }
                                )

                                .userInfoEndpoint(userInfoEndpoint -> {
                                            userInfoEndpoint.userService(customOAuth2UserService);
                                            userInfoEndpoint.oidcUserService(customOidcUserService);
                                        }

                                )
                                .tokenEndpoint(tokenEndpoint -> tokenEndpoint.accessTokenResponseClient(authorizationCodeTokenResponseClient()))
                                .successHandler(oAuth2AuthenticationSuccessHandler)
                                .failureHandler(oAuth2AuthenticationFailureHandler)
                )

                .formLogin(AbstractHttpConfigurer::disable)

                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }


    private OAuth2AuthorizationRequestResolver authorizationRequestResolver(
            ClientRegistrationRepository clientRegistrationRepository) {

        DefaultOAuth2AuthorizationRequestResolver authorizationRequestResolver =
                new DefaultOAuth2AuthorizationRequestResolver(
                        clientRegistrationRepository, "/oauth2/authorization");

        return  authorizationRequestResolver;
    }


    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }


    private OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> authorizationCodeTokenResponseClient() {
        OAuth2AccessTokenResponseHttpMessageConverter tokenResponseHttpMessageConverter = new OAuth2AccessTokenResponseHttpMessageConverter();
        tokenResponseHttpMessageConverter.setAccessTokenResponseConverter(tokenResponseParameters -> {
            Set<String> TOKEN_RESPONSE_PARAMETER_NAMES = new HashSet<>(Arrays.asList(OAuth2ParameterNames.ACCESS_TOKEN, OAuth2ParameterNames.TOKEN_TYPE, OAuth2ParameterNames.EXPIRES_IN, OAuth2ParameterNames.REFRESH_TOKEN));
            String accessToken = (String) tokenResponseParameters.get(OAuth2ParameterNames.ACCESS_TOKEN);

            OAuth2AccessToken.TokenType accessTokenType = OAuth2AccessToken.TokenType.BEARER;
            tokenResponseParameters.get(OAuth2ParameterNames.TOKEN_TYPE);
            Set<String> scopes = Collections.emptySet();
            if (tokenResponseParameters.containsKey(OAuth2ParameterNames.SCOPE)) {
                String scope = (String) tokenResponseParameters.get(OAuth2ParameterNames.SCOPE);
                scopes = Arrays.stream(StringUtils.delimitedListToStringArray(scope, " ")).collect(Collectors.toSet());
            }

            Map<String, Object> additionalParameters = new LinkedHashMap<>();
            tokenResponseParameters.entrySet().stream().filter(e -> !TOKEN_RESPONSE_PARAMETER_NAMES.contains(e.getKey()))
                    .forEach(e -> additionalParameters.put(e.getKey(), e.getValue()));

            return OAuth2AccessTokenResponse.withToken(accessToken).tokenType(accessTokenType).scopes(scopes).additionalParameters(additionalParameters).build();
        });
        RestTemplate restTemplate = new RestTemplate(Arrays.asList(new FormHttpMessageConverter(), tokenResponseHttpMessageConverter));
        restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
        DefaultAuthorizationCodeTokenResponseClient tokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
        tokenResponseClient.setRestOperations(restTemplate);
        return tokenResponseClient;
    }

    @Bean
    public AuthenticationManager authenticationManager(@Qualifier("userDetails") UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return providerManager;
    }

    @Bean("userDetails")
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(
                User.builder()
                        .username("ahmed@gmail.com")
                        .password(passwordEncoder.encode("ahmed1234"))
                        .roles("ADMIN")
                        .build()
        );
        inMemoryUserDetailsManager.createUser(
                User.builder()
                        .username("mohamed@gmail.com")
                        .password(passwordEncoder.encode("mohamed2002"))
                        .roles("ADMIN")
                        .build()
        );
        inMemoryUserDetailsManager.createUser(
                User.builder()
                        .username("aboelwafa@gmail.com")
                        .password(passwordEncoder.encode("aboelwafa2001"))
                        .roles("ADMIN")
                        .build()
        );
        inMemoryUserDetailsManager.createUser(
                User.builder()
                        .username("medany@gmail.com")
                        .password(passwordEncoder.encode("medany1234"))
                        .roles("ADMIN")
                        .build()
        );
        inMemoryUserDetailsManager.createUser(
                User.builder()
                        .username("ayman@gmail.com")
                        .password(passwordEncoder.encode("ayman9999"))
                        .roles("ADMIN")
                        .build()
        );
        return inMemoryUserDetailsManager;
    }


}
