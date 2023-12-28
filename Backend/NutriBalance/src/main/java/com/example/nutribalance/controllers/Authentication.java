package com.example.nutribalance.controllers;


import com.example.nutribalance.entities.ResetPassword;
import com.example.nutribalance.services.IService;
import com.example.nutribalance.dto.ApiResponse;
import com.example.nutribalance.dto.ChangePasswordDto;
import com.example.nutribalance.dto.LoginRequest;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

@RestController
public class Authentication {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IService service;


    @PostMapping("/adminLogin")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
        org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok().body(authentication.getPrincipal());
        }
        catch (Exception e){
            return ResponseEntity.ok().body(null);
        }
    }


    @GetMapping("/user")
    public OidcUser getUser(@AuthenticationPrincipal OidcUser user) {
        return user;
    }

    @GetMapping("/forgetPassword")
    public ResponseEntity<?> forgetPassword(@Param("email") String email, @Param("role") String role){
        String username = service.findByEmailRole(email, role);
        if (username == null) {
            return ResponseEntity.badRequest().body("Error: Email is not valid!");
        }
        ResetPassword resetPassword = new ResetPassword();
        resetPassword.setEmail(email);
        resetPassword.setUsername(username);
        String randomCode = RandomString.make(8);
        resetPassword.setToken(randomCode);
        try{
            service.sendForgetPasswordEmail(resetPassword);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error: Email is not valid!");
        }
        service.createResetPassword(resetPassword);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/checkOtp")
    public ResponseEntity<?> checkOtp(@Param("otp") String otp,@Param("email") String email) {
        ResetPassword user = service.getResetPassword(email);
        if (user == null) {
            return ResponseEntity.badRequest().body("Error: Email is not valid!");
        }
        else if (!user.getToken().equals(otp)) {
            return ResponseEntity.badRequest().body("Error: OTP is not valid!");
        }
        return ResponseEntity.ok().build();
    }
    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody LoginRequest loginRequest,@Param("role") String role) {
        service.resetPassword(loginRequest,role);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto loginRequest){
        String email = loginRequest.getEmail();
        String oldPassword = loginRequest.getOldPassword();
        String password = loginRequest.getNewPassword();
        String role = loginRequest.getRole();
        String message = service.changePassword(email,oldPassword,password,role);
        if(message.equals("success")){
            return ResponseEntity.ok().body(new ApiResponse(true, message));
        }
        else{
            return ResponseEntity.ok().body(new ApiResponse(false, message));
        }
    }
}
