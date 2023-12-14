package com.example.nutribalance.security;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.nutribalance.security.oauth2.CustomOAuth2AuthorizationRequestResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

@ContextConfiguration(classes = {CustomOAuth2AuthorizationRequestResolver.class})
@ExtendWith(SpringExtension.class)
class CustomOAuth2AuthorizationRequestResolverTest {
    @MockBean
    private ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    private CustomOAuth2AuthorizationRequestResolver customOAuth2AuthorizationRequestResolver;

    /**
     * Method under test: {@link CustomOAuth2AuthorizationRequestResolver#resolve(HttpServletRequest)}
     */
    @Test
    void testResolve() {
        assertNull(customOAuth2AuthorizationRequestResolver.resolve(new MockHttpServletRequest()));
    }




    /**
     * Method under test: {@link CustomOAuth2AuthorizationRequestResolver#resolve(HttpServletRequest)}
     */
    @Test
    void testResolve4() {
        HttpServletRequestWrapper httpServletRequestWrapper = mock(HttpServletRequestWrapper.class);
        when(httpServletRequestWrapper.getParameter((String) any())).thenReturn("Parameter");
        when(httpServletRequestWrapper.getPathInfo()).thenReturn("https://example.org/example");
        when(httpServletRequestWrapper.getServletPath()).thenReturn("https://example.org/example");
        assertNull(customOAuth2AuthorizationRequestResolver.resolve(httpServletRequestWrapper));
        verify(httpServletRequestWrapper).getParameter((String) any());
        verify(httpServletRequestWrapper).getPathInfo();
        verify(httpServletRequestWrapper).getServletPath();
    }

    /**
     * Method under test: {@link CustomOAuth2AuthorizationRequestResolver#resolve(HttpServletRequest, String)}
     */

}

