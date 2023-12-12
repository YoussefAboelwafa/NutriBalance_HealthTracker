package com.example.nutribalance.Controllers;

import com.example.nutribalance.Entities.User;
import com.example.nutribalance.Services.Iservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    Iservice service;

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return service.saveuser(user);
    }

    @GetMapping("/usersignin/{email}/{password}")
    public User signIn(@PathVariable String email, @PathVariable String password) {
        return service.usersignin(email, password);
    }
    @GetMapping("/subscribe")
    public User subscribe(@Param("planName") String planName, @Param("user_id") Long user_id) {
        return service.subscribe_to_plan(planName, user_id);
    }
    @GetMapping("/verify")
    public ResponseEntity<?> verifyUser(@Param("code") String code) {
        if (service.verify(code)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Error: Verification code is invalid!");
        }
    }

}

