package com.example.nutribalance.security.config;


import com.example.nutribalance.security.jwt.TokenAuthenticationFilter;
import com.example.nutribalance.security.oauth2.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private CustomOidcUserService customOidcUserService;

    @Autowired
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Autowired
    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }


   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .cors(cors->{
                    cors.configurationSource(request->{
                        var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
                        corsConfiguration.setAllowedOrigins(java.util.List.of("http://localhost:4200"));
                        corsConfiguration.setAllowedMethods(java.util.List.of("GET","POST","PUT","DELETE","OPTIONS"));
                        corsConfiguration.setAllowedHeaders(java.util.List.of("*"));
                        return corsConfiguration;
                    });
                })
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint((request, response, authException) -> response.sendError(401))
                )
                .authorizeHttpRequests(auth->
                        auth.requestMatchers("/", "/error", "/api/all", "/api/auth/**", "/oauth2/**" ,"/api/**","/**").permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2Login(
                        oauth2Login -> oauth2Login
                                .authorizationEndpoint(authorizationEndpoint -> {
                                            authorizationEndpoint.authorizationRequestRepository(cookieAuthorizationRequestRepository());
//                                            authorizationEndpoint.baseUri("/oauth2/authorize/google");
                                        }
                                )

                                .userInfoEndpoint(userInfoEndpoint ->{
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
//            long expiresIn = 0;
//            if (tokenResponseParameters.containsKey(OAuth2ParameterNames.EXPIRES_IN)) {
//                try {
//                    expiresIn = Long.parseLong((String) tokenResponseParameters.get(OAuth2ParameterNames.EXPIRES_IN));
//                } catch (NumberFormatException ex) {
//                }
//            }

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





}
