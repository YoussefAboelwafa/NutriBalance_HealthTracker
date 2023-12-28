package com.example.nutribalance.controllers;

import com.example.nutribalance.dto.ApiResponse;
import com.example.nutribalance.dto.PlanDto;
import com.example.nutribalance.entities.Coach;
import com.example.nutribalance.entities.Plan;
import com.example.nutribalance.entities.User;
import com.example.nutribalance.services.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/coach")
public class CoachController {
    @Autowired
    private IService iservice;

    @PostMapping("/save")
    public ResponseEntity<?> saveCoach(@RequestParam("file") MultipartFile file, @RequestParam("coach") String coachJson) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Coach coach = objectMapper.readValue(coachJson, Coach.class);
        coach.setCv(file.getBytes());
        coach.setIsapproved(0);
        coach.setEnabled(false);
        return ResponseEntity.ok().body(iservice.registerCoach(coach));
    }

    @GetMapping("/get_waiting_coaches")
    public List<Coach> getWaitingCoaches() {
        return iservice.getWaitingCoaches();
    }

    @DeleteMapping("/delete/{id}")
    public String deletecoach(@PathVariable Long id) {
        return iservice.deletecoach(id);
    }

    @GetMapping("/approve/{id}")
    public Coach approvecoach(@PathVariable Long id) {
        return iservice.approvecoach(id);
    }

    @GetMapping("checksignin/{email}/{password}")
    public Coach coachSignIn(@PathVariable String email, @PathVariable String password) {
        return iservice.coachSignIn(email, password);
    }

    @GetMapping("/get_subscribed_users/{coach_id}")
    public List<User> getSubscribedUsers(@PathVariable Long coach_id) {
        return iservice.getSubscribedUsers(coach_id);
    }

    @GetMapping("/update_comment/{comment}/{user_id}")
    public User updateComment(@PathVariable String comment, @PathVariable Long user_id) {
        return iservice.updateComment(comment, user_id);
    }

    @PostMapping("/addImageToCoach/{Email}")
    public Coach addImageToCoach(@PathVariable String Email, @RequestParam("file") MultipartFile image) {
        return iservice.addImageToCoach(Email, image);
    }

    @PutMapping("/updateCoach")
    public Coach updateCoach(@RequestBody Coach coach) {
        return iservice.updateCoach(coach);
    }

    @PostMapping("/updateCV/{email}")
    public Coach updateCV(@PathVariable("email") String email, @RequestParam("file") MultipartFile file) throws IOException {
        return iservice.updateCoachCV(email, file.getBytes());
    }

    @GetMapping("/getPlans")
    public List<PlanDto> getPlans(@RequestParam("id") Long coach_id) {
        List<Plan> plans = iservice.getPlans(coach_id);
        ArrayList<PlanDto> planDtos = new ArrayList<>();
        for (Plan plan : plans) {
            planDtos.add(
                    PlanDto.builder()
                            .planName(plan.getPlanName())
                            .description(plan.getDescription())
                            .Goal(plan.getGoal()).build());
        }
        return planDtos;

    }
    @PutMapping("/updatePlan")
    public PlanDto updatePlan(@RequestBody Plan plan){
        Plan p=iservice.updatePlan(plan);
        return PlanDto.builder()
                .planName(p.getPlanName())
                .description(p.getDescription())
                .Goal(p.getGoal()).build();

    }
    @DeleteMapping("/deletePlan/{planName}")
    public ApiResponse deletePlan(@PathVariable String planName){
        String s=iservice.deletePlan(planName);
        boolean b= s.equals("Plan deleted");
        return new ApiResponse(b,s);
    }
    @GetMapping("/getcoaches")
    public  List<Coach> getCoaches(){
        return iservice.getCoaches();
    }

    @DeleteMapping("/deletecoach/{id}")
    public ApiResponse deleteCoach(@PathVariable Long id){
        iservice.deleteCoach(id);
        return new ApiResponse(true,"coach deleted");
    }
}

