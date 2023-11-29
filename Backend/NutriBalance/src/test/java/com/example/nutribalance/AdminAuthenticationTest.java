package com.example.nutribalance;


import com.example.nutribalance.Controllers.Authentication;
import com.example.nutribalance.dto.LoginRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminAuthenticationTest {
    @InjectMocks
    private Authentication adminController;

    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void login_withValidCredentials_shouldReturnAuthentication() {
        LoginRequest loginRequest = new LoginRequest("mohamed", "mohamed2002");
        org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        ResponseEntity<?> response = adminController.login(loginRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authentication.getPrincipal(), response.getBody());
    }

    @Test
    public void login_withInvalidCredentials_shouldReturnUnauthorized() {
        LoginRequest loginRequest = new LoginRequest("ahmed", "ahmed1234");
        ResponseEntity<?> response = adminController.login(loginRequest);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void login_withNullEmail_shouldReturnBadRequest() {
        LoginRequest loginRequest = new LoginRequest(null, "ahmed1234");
        ResponseEntity<?> response = adminController.login(loginRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void login_withNullPassword_shouldReturnBadRequest() {
        LoginRequest loginRequest = new LoginRequest("medany", null);
        ResponseEntity<?> response = adminController.login(loginRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }
}
