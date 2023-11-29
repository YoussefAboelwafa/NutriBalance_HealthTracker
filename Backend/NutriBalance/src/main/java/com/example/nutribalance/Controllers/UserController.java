package com.example.nutribalance.Controllers;

import com.example.nutribalance.Entities.User;
import com.example.nutribalance.Services.Iservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    Iservice service;
    @PostMapping("/save")
    public User saveUser(@RequestBody User user){
        return service.saveuser(user);
    }

    @GetMapping("/usersignin/{email}/{password}")
    public User signIn(@PathVariable String email,@PathVariable String password){
        return service.usersignin(email,password);
    }
}

