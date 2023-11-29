package com.example.nutribalance.Controllers;

import com.example.nutribalance.dto.CurrentUser;
import com.example.nutribalance.dto.LocalUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.nutribalance.util.GeneralUtils;


@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/user/me")
//    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getCurrentUser(@CurrentUser LocalUser user) {
        System.out.println("user------------------------------------------------------------------>>>>: " + user);
        return ResponseEntity.ok(GeneralUtils.buildUserInfo(user));
    }

    @GetMapping("/all")
//    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getContent() {
        return ResponseEntity.ok("Public content goes here");
    }

    @GetMapping("/user")
//    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getUserContent() {
        return ResponseEntity.ok("User content goes here");
    }

    @GetMapping("/admin")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAdminContent() {
        return ResponseEntity.ok("Admin content goes here");
    }

    @GetMapping("/mod")
//    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<?> getModeratorContent() {
        return ResponseEntity.ok("Moderator content goes here");
    }
}


