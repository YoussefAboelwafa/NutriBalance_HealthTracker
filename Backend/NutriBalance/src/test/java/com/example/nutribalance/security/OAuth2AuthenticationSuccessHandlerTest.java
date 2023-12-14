package com.example.nutribalance.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.nutribalance.security.jwt.TokenProvider;
import com.example.nutribalance.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.example.nutribalance.security.oauth2.OAuth2AuthenticationSuccessHandler;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.DelegatingServletInputStream;
import org.springframework.mock.web.MockHttpServletMapping;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OAuth2AuthenticationSuccessHandler.class})
@ExtendWith(SpringExtension.class)
class OAuth2AuthenticationSuccessHandlerTest {
    @MockBean
    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Autowired
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @MockBean
    private TokenProvider tokenProvider;




    /**
     * Method under test: {@link OAuth2AuthenticationSuccessHandler#onAuthenticationSuccess(HttpServletRequest, HttpServletResponse, Authentication)}
     */
    @Test
    void testOnAuthenticationSuccess3() throws ServletException, IOException {
        when(tokenProvider.createToken((Authentication) any())).thenReturn("ABC123");
        doNothing().when(httpCookieOAuth2AuthorizationRequestRepository)
                .removeAuthorizationRequestCookies((HttpServletRequest) any(), (HttpServletResponse) any());
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        oAuth2AuthenticationSuccessHandler.onAuthenticationSuccess(request, response,
                new TestingAuthenticationToken("Principal", "Credentials"));
        verify(tokenProvider).createToken((Authentication) any());
        verify(httpCookieOAuth2AuthorizationRequestRepository)
                .removeAuthorizationRequestCookies((HttpServletRequest) any(), (HttpServletResponse) any());
    }

    /**
     * Method under test: {@link OAuth2AuthenticationSuccessHandler#onAuthenticationSuccess(HttpServletRequest, HttpServletResponse, Authentication)}
     */
    @Test
    void testOnAuthenticationSuccess4() throws ServletException, IOException {
        when(tokenProvider.createToken((Authentication) any())).thenReturn("ABC123");
        doNothing().when(httpCookieOAuth2AuthorizationRequestRepository)
                .removeAuthorizationRequestCookies((HttpServletRequest) any(), (HttpServletResponse) any());
        MockHttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        when(httpServletResponse.isCommitted()).thenReturn(true);
        when(httpServletResponse.encodeRedirectURL((String) any())).thenReturn("https://example.org/example");
        doNothing().when(httpServletResponse).sendRedirect((String) any());
        oAuth2AuthenticationSuccessHandler.onAuthenticationSuccess(request, httpServletResponse,
                new TestingAuthenticationToken("Principal", "Credentials"));
        verify(tokenProvider).createToken((Authentication) any());
        verify(httpServletResponse).isCommitted();
    }


    @Test
    void testDetermineTargetUrl() {
        when(tokenProvider.createToken((Authentication) any())).thenReturn("ABC123");
        MockHttpServletRequest request = new MockHttpServletRequest();
        Response response = new Response();
        assertEquals("/?token=ABC123", oAuth2AuthenticationSuccessHandler.determineTargetUrl(request, response,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(tokenProvider).createToken((Authentication) any());
    }





    @Test
    void testClearAuthenticationAttributes() {
        doNothing().when(httpCookieOAuth2AuthorizationRequestRepository)
                .removeAuthorizationRequestCookies((HttpServletRequest) any(), (HttpServletResponse) any());
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        oAuth2AuthenticationSuccessHandler.clearAuthenticationAttributes(mockHttpServletRequest, new Response());
        verify(httpCookieOAuth2AuthorizationRequestRepository).removeAuthorizationRequestCookies((HttpServletRequest) any(),
                (HttpServletResponse) any());
        assertFalse(mockHttpServletRequest.isAsyncStarted());
        assertTrue(mockHttpServletRequest.isActive());
        assertTrue(mockHttpServletRequest.getSession() instanceof MockHttpSession);
        assertEquals("", mockHttpServletRequest.getServletPath());
        assertEquals(80, mockHttpServletRequest.getServerPort());
        assertEquals("localhost", mockHttpServletRequest.getServerName());
        assertEquals("http", mockHttpServletRequest.getScheme());
        assertEquals("", mockHttpServletRequest.getRequestURI());
        assertEquals(80, mockHttpServletRequest.getRemotePort());
        assertEquals("localhost", mockHttpServletRequest.getRemoteHost());
        assertEquals("HTTP/1.1", mockHttpServletRequest.getProtocol());
        assertEquals("", mockHttpServletRequest.getMethod());
        assertEquals(80, mockHttpServletRequest.getLocalPort());
        assertEquals("localhost", mockHttpServletRequest.getLocalName());
        assertTrue(mockHttpServletRequest.getInputStream() instanceof DelegatingServletInputStream);
        assertTrue(mockHttpServletRequest.getHttpServletMapping() instanceof MockHttpServletMapping);
        assertEquals(DispatcherType.REQUEST, mockHttpServletRequest.getDispatcherType());
        assertEquals("", mockHttpServletRequest.getContextPath());
        assertEquals(-1L, mockHttpServletRequest.getContentLengthLong());
    }




}

