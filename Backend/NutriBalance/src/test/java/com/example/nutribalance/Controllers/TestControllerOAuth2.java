package com.example.nutribalance.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.nutribalance.entities.User;
import com.example.nutribalance.dto.LocalUser;
import com.example.nutribalance.dto.UserInfo;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {OAuth2Controller.class})
@ExtendWith(SpringExtension.class)
class TestControllerOAuth2 {
    /**
     * Method under test: {@link OAuth2Controller#getCurrentUser(LocalUser)}
     */
    @Test
    void testGetCurrentUser() {
        OAuth2Controller OAuth2Controller = new OAuth2Controller();
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        ResponseEntity<?> actualCurrentUser = OAuth2Controller
                .getCurrentUser(new LocalUser("User ID", "iloveyou", true, true, true, true, authorities, new User()));
        assertEquals("0", ((UserInfo) actualCurrentUser.getBody()).getId());
        assertNull(((UserInfo) actualCurrentUser.getBody()).getDisplayName());
        assertNull(((UserInfo) actualCurrentUser.getBody()).getEmail());
        assertEquals(200, actualCurrentUser.getStatusCodeValue());
        assertTrue(actualCurrentUser.hasBody());
        assertTrue(actualCurrentUser.getHeaders().isEmpty());
        assertEquals(authorities, ((UserInfo) actualCurrentUser.getBody()).getRoles());
    }

    /**
     * Method under test: {@link OAuth2Controller#getCurrentUser(LocalUser)}
     */
    @Test
    void testGetCurrentUser2() {
        OAuth2Controller OAuth2Controller = new OAuth2Controller();
        User user = mock(User.class);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        when(user.getUsername()).thenReturn("janedoe");
        when(user.getUser_id()).thenReturn(1L);
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        ResponseEntity<?> actualCurrentUser = OAuth2Controller
                .getCurrentUser(new LocalUser("User ID", "iloveyou", true, true, true, true, authorities, user));
        verify(user).getEmail();
        verify(user).getUser_id();
        verify(user).getUsername();
        assertEquals("1", ((UserInfo) actualCurrentUser.getBody()).getId());
        assertEquals("jane.doe@example.org", ((UserInfo) actualCurrentUser.getBody()).getEmail());
        assertEquals("janedoe", ((UserInfo) actualCurrentUser.getBody()).getDisplayName());
        assertEquals(200, actualCurrentUser.getStatusCodeValue());
        assertTrue(actualCurrentUser.hasBody());
        assertTrue(actualCurrentUser.getHeaders().isEmpty());
        assertEquals(authorities, ((UserInfo) actualCurrentUser.getBody()).getRoles());
    }
}
