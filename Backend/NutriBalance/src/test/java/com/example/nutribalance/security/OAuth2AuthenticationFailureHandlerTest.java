package com.example.nutribalance.security;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import com.example.nutribalance.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.example.nutribalance.security.oauth2.OAuth2AuthenticationFailureHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OAuth2AuthenticationFailureHandler.class})
@ExtendWith(SpringExtension.class)
class OAuth2AuthenticationFailureHandlerTest {
    @MockBean
    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Autowired
    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;



    /**
     * Method under test: {@link OAuth2AuthenticationFailureHandler#onAuthenticationFailure(HttpServletRequest, HttpServletResponse, AuthenticationException)}
     */
    @Test
    void testOnAuthenticationFailure3() throws IOException {
        doNothing().when(httpCookieOAuth2AuthorizationRequestRepository)
                .removeAuthorizationRequestCookies((HttpServletRequest) any(), (HttpServletResponse) any());
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        oAuth2AuthenticationFailureHandler.onAuthenticationFailure(request, response, new AccountExpiredException("Msg"));
        verify(httpCookieOAuth2AuthorizationRequestRepository)
                .removeAuthorizationRequestCookies((HttpServletRequest) any(), (HttpServletResponse) any());
    }


}

