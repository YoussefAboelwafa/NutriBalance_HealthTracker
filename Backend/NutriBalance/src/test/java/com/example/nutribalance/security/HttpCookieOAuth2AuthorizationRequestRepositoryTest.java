package com.example.nutribalance.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.nutribalance.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.IOException;

import org.apache.catalina.connector.CoyoteOutputStream;
import org.apache.catalina.connector.Response;
import org.apache.catalina.connector.ResponseFacade;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.DelegatingServletInputStream;
import org.springframework.mock.web.MockHttpServletMapping;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {HttpCookieOAuth2AuthorizationRequestRepository.class})
@ExtendWith(SpringExtension.class)
class HttpCookieOAuth2AuthorizationRequestRepositoryTest {
    @Autowired
    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    /**
     * Method under test: {@link HttpCookieOAuth2AuthorizationRequestRepository#loadAuthorizationRequest(HttpServletRequest)}
     */
    @Test
    void testLoadAuthorizationRequest() {
        assertNull(httpCookieOAuth2AuthorizationRequestRepository.loadAuthorizationRequest(new MockHttpServletRequest()));
    }



    /**
     * Method under test: {@link HttpCookieOAuth2AuthorizationRequestRepository#loadAuthorizationRequest(HttpServletRequest)}
     */
    @Test
    void testLoadAuthorizationRequest3() {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getCookies()).thenReturn(new Cookie[]{new Cookie("Name", "https://example.org/example")});
        assertNull(httpCookieOAuth2AuthorizationRequestRepository.loadAuthorizationRequest(httpServletRequest));
        verify(httpServletRequest).getCookies();
    }


    /**
     * Method under test: {@link HttpCookieOAuth2AuthorizationRequestRepository#loadAuthorizationRequest(HttpServletRequest)}
     */
    @Test
    void testLoadAuthorizationRequest5() {
        Cookie cookie = mock(Cookie.class);
        when(cookie.getName()).thenReturn("https://example.org/example");
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getCookies()).thenReturn(new Cookie[]{cookie});
        assertNull(httpCookieOAuth2AuthorizationRequestRepository.loadAuthorizationRequest(httpServletRequest));
        verify(httpServletRequest).getCookies();
        verify(cookie).getName();
    }





    /**
     * Method under test: {@link HttpCookieOAuth2AuthorizationRequestRepository#loadAuthorizationRequest(HttpServletRequest)}
     */
    @Test
    void testLoadAuthorizationRequest8() {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getCookies()).thenReturn(new Cookie[]{});
        assertNull(httpCookieOAuth2AuthorizationRequestRepository.loadAuthorizationRequest(httpServletRequest));
        verify(httpServletRequest).getCookies();
    }

    /**
     * Method under test: {@link HttpCookieOAuth2AuthorizationRequestRepository#saveAuthorizationRequest(OAuth2AuthorizationRequest, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testSaveAuthorizationRequest() {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        httpCookieOAuth2AuthorizationRequestRepository.saveAuthorizationRequest(null, mockHttpServletRequest,
                new Response());
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



    /**
     * Method under test: {@link HttpCookieOAuth2AuthorizationRequestRepository#saveAuthorizationRequest(OAuth2AuthorizationRequest, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testSaveAuthorizationRequest3() throws IOException {
        HttpServletRequestWrapper httpServletRequestWrapper = mock(HttpServletRequestWrapper.class);
        when(httpServletRequestWrapper.getCookies())
                .thenReturn(new Cookie[]{new Cookie("Name", "https://example.org/example")});
        Response response = new Response();
        httpCookieOAuth2AuthorizationRequestRepository.saveAuthorizationRequest(null, httpServletRequestWrapper,
                response);
        verify(httpServletRequestWrapper, atLeast(1)).getCookies();
        HttpServletResponse response1 = response.getResponse();
        assertTrue(response1 instanceof ResponseFacade);
        assertSame(response.getOutputStream(), response1.getOutputStream());
    }


    /**
     * Method under test: {@link HttpCookieOAuth2AuthorizationRequestRepository#saveAuthorizationRequest(OAuth2AuthorizationRequest, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testSaveAuthorizationRequest5() throws IOException {
        Cookie cookie = mock(Cookie.class);
        when(cookie.getName()).thenReturn("https://example.org/example");
        HttpServletRequestWrapper httpServletRequestWrapper = mock(HttpServletRequestWrapper.class);
        when(httpServletRequestWrapper.getCookies()).thenReturn(new Cookie[]{cookie});
        Response response = new Response();
        httpCookieOAuth2AuthorizationRequestRepository.saveAuthorizationRequest(null, httpServletRequestWrapper,
                response);
        verify(httpServletRequestWrapper, atLeast(1)).getCookies();
        verify(cookie, atLeast(1)).getName();
        HttpServletResponse response1 = response.getResponse();
        assertTrue(response1 instanceof ResponseFacade);
        assertSame(response.getOutputStream(), response1.getOutputStream());
    }



    /**
     * Method under test: {@link HttpCookieOAuth2AuthorizationRequestRepository#saveAuthorizationRequest(OAuth2AuthorizationRequest, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testSaveAuthorizationRequest7() throws IOException {
        HttpServletRequestWrapper httpServletRequestWrapper = mock(HttpServletRequestWrapper.class);
        when(httpServletRequestWrapper.getCookies()).thenReturn(new Cookie[]{});
        Response response = new Response();
        httpCookieOAuth2AuthorizationRequestRepository.saveAuthorizationRequest(null, httpServletRequestWrapper,
                response);
        verify(httpServletRequestWrapper, atLeast(1)).getCookies();
        HttpServletResponse response1 = response.getResponse();
        assertTrue(response1 instanceof ResponseFacade);
        assertSame(response.getOutputStream(), response1.getOutputStream());
    }

    /**
     * Method under test: {@link HttpCookieOAuth2AuthorizationRequestRepository#saveAuthorizationRequest(OAuth2AuthorizationRequest, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testSaveAuthorizationRequest8() {
        Cookie cookie = mock(Cookie.class);
        doNothing().when(cookie).setMaxAge(anyInt());
        doNothing().when(cookie).setPath((String) any());
        doNothing().when(cookie).setValue((String) any());
        when(cookie.getName())
                .thenReturn(HttpCookieOAuth2AuthorizationRequestRepository.OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
        HttpServletRequestWrapper httpServletRequestWrapper = mock(HttpServletRequestWrapper.class);
        when(httpServletRequestWrapper.getCookies()).thenReturn(new Cookie[]{cookie});
        HttpServletResponseWrapper httpServletResponseWrapper = mock(HttpServletResponseWrapper.class);
        doNothing().when(httpServletResponseWrapper).addCookie((Cookie) any());
        httpCookieOAuth2AuthorizationRequestRepository.saveAuthorizationRequest(null, httpServletRequestWrapper,
                httpServletResponseWrapper);
        verify(httpServletRequestWrapper, atLeast(1)).getCookies();
        verify(cookie, atLeast(1)).getName();
        verify(cookie).setMaxAge(anyInt());
        verify(cookie).setPath((String) any());
        verify(cookie).setValue((String) any());
        verify(httpServletResponseWrapper).addCookie((Cookie) any());
    }


    /**
     * Method under test: {@link HttpCookieOAuth2AuthorizationRequestRepository#removeAuthorizationRequest(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRemoveAuthorizationRequest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        assertNull(httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequest(request, new Response()));
    }


    /**
     * Method under test: {@link HttpCookieOAuth2AuthorizationRequestRepository#removeAuthorizationRequest(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRemoveAuthorizationRequest3() {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getCookies()).thenReturn(new Cookie[]{new Cookie("Name", "https://example.org/example")});
        assertNull(httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequest(httpServletRequest,
                new Response()));
        verify(httpServletRequest).getCookies();
    }


    /**
     * Method under test: {@link HttpCookieOAuth2AuthorizationRequestRepository#removeAuthorizationRequest(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRemoveAuthorizationRequest5() {
        Cookie cookie = mock(Cookie.class);
        when(cookie.getName()).thenReturn("https://example.org/example");
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getCookies()).thenReturn(new Cookie[]{cookie});
        assertNull(httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequest(httpServletRequest,
                new Response()));
        verify(httpServletRequest).getCookies();
        verify(cookie).getName();
    }





    /**
     * Method under test: {@link HttpCookieOAuth2AuthorizationRequestRepository#removeAuthorizationRequest(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRemoveAuthorizationRequest8() {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getCookies()).thenReturn(new Cookie[]{});
        assertNull(httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequest(httpServletRequest,
                new Response()));
        verify(httpServletRequest).getCookies();
    }

    /**
     * Method under test: {@link HttpCookieOAuth2AuthorizationRequestRepository#removeAuthorizationRequestCookies(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRemoveAuthorizationRequestCookies() {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(mockHttpServletRequest,
                new Response());
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


    /**
     * Method under test: {@link HttpCookieOAuth2AuthorizationRequestRepository#removeAuthorizationRequestCookies(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRemoveAuthorizationRequestCookies3() throws IOException {
        HttpServletRequestWrapper httpServletRequestWrapper = mock(HttpServletRequestWrapper.class);
        when(httpServletRequestWrapper.getCookies())
                .thenReturn(new Cookie[]{new Cookie("Name", "https://example.org/example")});
        Response response = new Response();
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(httpServletRequestWrapper,
                response);
        verify(httpServletRequestWrapper, atLeast(1)).getCookies();
        HttpServletResponse response1 = response.getResponse();
        assertTrue(response1 instanceof ResponseFacade);
        assertSame(response.getOutputStream(), response1.getOutputStream());
    }



    /**
     * Method under test: {@link HttpCookieOAuth2AuthorizationRequestRepository#removeAuthorizationRequestCookies(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRemoveAuthorizationRequestCookies5() throws IOException {
        Cookie cookie = mock(Cookie.class);
        when(cookie.getName()).thenReturn("https://example.org/example");
        HttpServletRequestWrapper httpServletRequestWrapper = mock(HttpServletRequestWrapper.class);
        when(httpServletRequestWrapper.getCookies()).thenReturn(new Cookie[]{cookie});
        Response response = new Response();
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(httpServletRequestWrapper,
                response);
        verify(httpServletRequestWrapper, atLeast(1)).getCookies();
        verify(cookie, atLeast(1)).getName();
        HttpServletResponse response1 = response.getResponse();
        assertTrue(response1 instanceof ResponseFacade);
        assertSame(response.getOutputStream(), response1.getOutputStream());
    }



    /**
     * Method under test: {@link HttpCookieOAuth2AuthorizationRequestRepository#removeAuthorizationRequestCookies(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRemoveAuthorizationRequestCookies7() throws IOException {
        HttpServletRequestWrapper httpServletRequestWrapper = mock(HttpServletRequestWrapper.class);
        when(httpServletRequestWrapper.getCookies()).thenReturn(new Cookie[]{});
        Response response = new Response();
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(httpServletRequestWrapper,
                response);
        verify(httpServletRequestWrapper, atLeast(1)).getCookies();
        HttpServletResponse response1 = response.getResponse();
        assertTrue(response1 instanceof ResponseFacade);
        assertSame(response.getOutputStream(), response1.getOutputStream());
    }

    /**
     * Method under test: {@link HttpCookieOAuth2AuthorizationRequestRepository#removeAuthorizationRequestCookies(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRemoveAuthorizationRequestCookies8() {
        Cookie cookie = mock(Cookie.class);
        doNothing().when(cookie).setMaxAge(anyInt());
        doNothing().when(cookie).setPath((String) any());
        doNothing().when(cookie).setValue((String) any());
        when(cookie.getName())
                .thenReturn(HttpCookieOAuth2AuthorizationRequestRepository.OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
        HttpServletRequestWrapper httpServletRequestWrapper = mock(HttpServletRequestWrapper.class);
        when(httpServletRequestWrapper.getCookies()).thenReturn(new Cookie[]{cookie});
        HttpServletResponseWrapper httpServletResponseWrapper = mock(HttpServletResponseWrapper.class);
        doNothing().when(httpServletResponseWrapper).addCookie((Cookie) any());
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(httpServletRequestWrapper,
                httpServletResponseWrapper);
        verify(httpServletRequestWrapper, atLeast(1)).getCookies();
        verify(cookie, atLeast(1)).getName();
        verify(cookie).setMaxAge(anyInt());
        verify(cookie).setPath((String) any());
        verify(cookie).setValue((String) any());
        verify(httpServletResponseWrapper).addCookie((Cookie) any());
    }
}

