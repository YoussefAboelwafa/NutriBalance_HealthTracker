package com.example.nutribalance.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.nutribalance.Entities.User;
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

@ContextConfiguration(classes = {TestController.class})
@ExtendWith(SpringExtension.class)
class TestControllerTest {
    @Autowired
    private TestController testController;

    /**
     * Method under test: {@link TestController#getAdminContent()}
     */
    @Test
    void testGetAdminContent() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/admin");
        MockMvcBuilders.standaloneSetup(testController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Admin content goes here"));
    }

    /**
     * Method under test: {@link TestController#getCurrentUser(LocalUser)}
     */
    @Test
    void testGetCurrentUser() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: java.lang.IllegalStateException: No primary or single unique constructor found for class com.example.nutribalance.dto.LocalUser
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   java.lang.IllegalStateException: No primary or single unique constructor found for class com.example.nutribalance.dto.LocalUser
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        TestController testController = new TestController();
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        ResponseEntity<?> actualCurrentUser = testController
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
     * Method under test: {@link TestController#getCurrentUser(LocalUser)}
     */
    @Test
    void testGetCurrentUser2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: java.lang.IllegalStateException: No primary or single unique constructor found for class com.example.nutribalance.dto.LocalUser
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   java.lang.IllegalStateException: No primary or single unique constructor found for class com.example.nutribalance.dto.LocalUser
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        TestController testController = new TestController();
        User user = mock(User.class);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        when(user.getUsername()).thenReturn("janedoe");
        when(user.getUser_id()).thenReturn(1L);
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        ResponseEntity<?> actualCurrentUser = testController
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

    /**
     * Method under test: {@link TestController#getAdminContent()}
     */
    @Test
    void testGetAdminContent2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/admin", "Uri Variables");
        MockMvcBuilders.standaloneSetup(testController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Admin content goes here"));
    }

    /**
     * Method under test: {@link TestController#getContent()}
     */
    @Test
    void testGetContent() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/all");
        MockMvcBuilders.standaloneSetup(testController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Public content goes here"));
    }

    /**
     * Method under test: {@link TestController#getContent()}
     */
    @Test
    void testGetContent2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/all");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(testController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Public content goes here"));
    }

    /**
     * Method under test: {@link TestController#getModeratorContent()}
     */
    @Test
    void testGetModeratorContent() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/mod");
        MockMvcBuilders.standaloneSetup(testController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Moderator content goes here"));
    }

    /**
     * Method under test: {@link TestController#getModeratorContent()}
     */
    @Test
    void testGetModeratorContent2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/mod");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(testController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Moderator content goes here"));
    }

    /**
     * Method under test: {@link TestController#getUserContent()}
     */
    @Test
    void testGetUserContent() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user");
        MockMvcBuilders.standaloneSetup(testController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("User content goes here"));
    }

    /**
     * Method under test: {@link TestController#getUserContent()}
     */
    @Test
    void testGetUserContent2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(testController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("User content goes here"));
    }
}
