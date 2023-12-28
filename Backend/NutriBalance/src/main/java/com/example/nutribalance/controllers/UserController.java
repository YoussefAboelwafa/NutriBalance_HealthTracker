package com.example.nutribalance.controllers;

import com.example.nutribalance.entities.FoodCalorie;
import com.example.nutribalance.entities.Report;
import com.example.nutribalance.entities.User;
import com.example.nutribalance.entities.Weight;
import com.example.nutribalance.services.IService;
import com.example.nutribalance.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IService service;

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return service.saveuser(user);
    }

    @GetMapping("/usersignin/{email}/{password}")
    public User signIn(@PathVariable String email, @PathVariable String password) {
        return service.userSignIn(email, password);
    }
    @GetMapping("/getUser")
    public User getUser(@Param("id") Long id) {
        return service.getUser(id);
    }
    @GetMapping("/subscribe")
    public User subscribe(@Param("planName") String planName, @Param("user_id") Long user_id) {
        return service.subscribeToPlan(planName, user_id);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    @PostMapping("/addImageToUser/{Email}")
    public User addImageToUser(@PathVariable String Email, @RequestParam("file") MultipartFile image) {
        return service.addImageToUser(Email, image);
    }
    @GetMapping("/verify")
    public ResponseEntity<?> verifyUser(@Param("code") String code) {
        if (service.verify(code)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Error: Verification code is invalid!");
        }
    }
    @GetMapping("/food_calorie")
    public List<FoodCalorie> getFoodCalorie(){
        return service.getFoodCalorie();
    }

    @PostMapping("/addweight/{id}")
    public User addWeight(@PathVariable Long id, @RequestParam("weight") Double weight, @RequestBody Date date){
        return service.addWeight(id, weight, date);
    }
    @GetMapping("/getweights/{id}")
    public List<Weight> getWeights(@PathVariable Long id){
        return service.getWeights(id);
    }
    @DeleteMapping("/deletesubscription/{id}")
    public User deleteSubscription(@PathVariable Long id){
        return service.deleteSubscription(id);
    }

    @PostMapping("/report/{id}")
    public Report addReport(@PathVariable Long id, @RequestParam("coach_id") Long coach_id, @RequestBody String message){
        return service.addReport(id,coach_id,message,"user");
    }
    @DeleteMapping("/deleteuser/{id}")
    public ApiResponse deleteUser(@PathVariable Long id){
          service.deleteUser(id);
          return new ApiResponse(true,"user deleted");
    }

}

