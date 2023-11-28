package com.example.nutribalance.Controllers;


import org.antlr.v4.runtime.Token;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Authentication {


    @GetMapping("/user")
    public OidcUser get_User(@AuthenticationPrincipal OidcUser user) {
        return user;
    }
}
