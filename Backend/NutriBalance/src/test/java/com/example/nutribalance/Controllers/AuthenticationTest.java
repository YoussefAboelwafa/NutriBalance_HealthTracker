package com.example.nutribalance.Controllers;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.nutribalance.Entities.ResetPassword;
import com.example.nutribalance.Entities.User;
import com.example.nutribalance.Services.Iservice;
import com.example.nutribalance.dto.ChangePasswordDto;
import com.example.nutribalance.dto.LocalUser;
import com.example.nutribalance.dto.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {Authentication.class})
@ExtendWith(SpringExtension.class)
class AuthenticationTest {
    @Autowired
    private Authentication authentication;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private Iservice iservice;

    /**
     * Method under test: {@link Authentication#checkOtp(String, String)}
     */
    @Test
    void testCheckOtp() throws Exception {
        ResetPassword resetPassword = new ResetPassword();
        resetPassword.setEmail("jane.doe@example.org");
        resetPassword.setId(1L);
        resetPassword.setToken("ABC123");
        resetPassword.setUsername("janedoe");
        when(iservice.get_reset_password(Mockito.<String>any())).thenReturn(resetPassword);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/checkOtp")
                .param("email", "foo")
                .param("otp", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authentication).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Error: OTP is not valid!"));
    }

    /**
     * Method under test: {@link Authentication#get_User(OidcUser)}
     */
    @Test
    void testGet_User() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: java.lang.IllegalStateException: No primary or single unique constructor found for interface org.springframework.security.oauth2.core.oidc.user.OidcUser
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   java.lang.IllegalStateException: No primary or single unique constructor found for interface org.springframework.security.oauth2.core.oidc.user.OidcUser
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        Authentication authentication = new Authentication();
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        LocalUser user = new LocalUser("User ID", "iloveyou", true, true, true, true, authorities, new User());

        assertSame(user, authentication.get_User(user));
    }

    /**
     * Method under test: {@link Authentication#get_User(OidcUser)}
     */
    @Test
    void testGet_User2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: java.lang.IllegalStateException: No primary or single unique constructor found for interface org.springframework.security.oauth2.core.oidc.user.OidcUser
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   java.lang.IllegalStateException: No primary or single unique constructor found for interface org.springframework.security.oauth2.core.oidc.user.OidcUser
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        Authentication authentication = new Authentication();
        LocalUser user = new LocalUser("User ID", "iloveyou", true, true, true, true, new ArrayList<>(), mock(User.class));

        assertSame(user, authentication.get_User(user));
    }

    /**
     * Method under test: {@link Authentication#resetPassword(LoginRequest, String)}
     */
    @Test
    void testResetPassword() throws Exception {
        doNothing().when(iservice).resetPassword(Mockito.<LoginRequest>any(), Mockito.<String>any());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("jane.doe@example.org");
        loginRequest.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(loginRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/resetPassword")
                .param("role", "foo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(authentication)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link Authentication#checkOtp(String, String)}
     */
    @Test
    void testCheckOtp2() throws Exception {
        ResetPassword resetPassword = new ResetPassword();
        resetPassword.setEmail("jane.doe@example.org");
        resetPassword.setId(1L);
        resetPassword.setToken("foo");
        resetPassword.setUsername("janedoe");
        when(iservice.get_reset_password(Mockito.<String>any())).thenReturn(resetPassword);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/checkOtp")
                .param("email", "foo")
                .param("otp", "foo");
        MockMvcBuilders.standaloneSetup(authentication)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link Authentication#forgetPassword(String, String)}
     */
    @Test
    void testForgetPassword() throws Exception {
        doNothing().when(iservice).create_reset_password(Mockito.<ResetPassword>any());
        doNothing().when(iservice).sendForgetPasswordEmail(Mockito.<ResetPassword>any());
        when(iservice.findByEmailRole(Mockito.<String>any(), Mockito.<String>any())).thenReturn("jane.doe@example.org");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/forgetPassword")
                .param("email", "foo")
                .param("role", "foo");
        MockMvcBuilders.standaloneSetup(authentication)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link Authentication#forgetPassword(String, String)}
     */
    @Test
    void testForgetPassword2() throws Exception {
        doNothing().when(iservice).create_reset_password(Mockito.<ResetPassword>any());
        doNothing().when(iservice).sendForgetPasswordEmail(Mockito.<ResetPassword>any());
        when(iservice.findByEmailRole(Mockito.<String>any(), Mockito.<String>any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/forgetPassword")
                .param("email", "foo")
                .param("role", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authentication).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Error: Email is not valid!"));
    }


    /**
     * Method under test:  {@link Authentication#login(LoginRequest)}
     */
    @Test
    void testLogin() throws Exception {
        when(authenticationManager.authenticate(Mockito.<org.springframework.security.core.Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("jane.doe@example.org");
        loginRequest.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(loginRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/adminLogin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(authentication)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Principal"));
    }

    /**
     * Method under test:  {@link Authentication#changePassword(ChangePasswordDto)}
     */
    @Test
    void testChangePassword() throws Exception {
        when(iservice.changePassword(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn("foo");
        ChangePasswordDto changePasswordDto = new ChangePasswordDto();
        changePasswordDto.setOldPassword("iloveyou");
        changePasswordDto.setNewPassword("helloworld");
        changePasswordDto.setEmail("jane.doe@example.org");
        changePasswordDto.setRole("foo");
        String content = (new ObjectMapper()).writeValueAsString(changePasswordDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/changePassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(authentication)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}