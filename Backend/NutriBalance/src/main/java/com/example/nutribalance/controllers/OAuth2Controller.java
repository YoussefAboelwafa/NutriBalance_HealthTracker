package com.example.nutribalance.controllers;

import com.example.nutribalance.dto.CurrentUser;
import com.example.nutribalance.dto.LocalUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.nutribalance.util.GeneralUtils;


@RestController
@RequestMapping("/api")
public class OAuth2Controller {
    @GetMapping("/user/me")
    public ResponseEntity<?> getCurrentUser(@CurrentUser LocalUser user) {
        return ResponseEntity.ok(GeneralUtils.buildUserInfo(user));
    }

}


