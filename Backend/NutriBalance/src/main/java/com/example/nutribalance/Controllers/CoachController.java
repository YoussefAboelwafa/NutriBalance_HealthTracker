package com.example.nutribalance.Controllers;

import com.example.nutribalance.Entities.Coach;
import com.example.nutribalance.Entities.Notification;
import com.example.nutribalance.Entities.Plan;
import com.example.nutribalance.Entities.User;
import com.example.nutribalance.Mails.EmailDetails;
import com.example.nutribalance.Mails.EmailService;
import com.example.nutribalance.Services.Iservice;
import com.example.nutribalance.dto.ApiResponse;
import com.example.nutribalance.dto.CoachDto;
import com.example.nutribalance.dto.PlanDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/coach")
public class CoachController {
    @Autowired
    private Iservice iservice;
    @Autowired
    private EmailService emailService;

    @PostMapping("/save")
    public ResponseEntity<?> savecoach(@RequestParam("file") MultipartFile file, @RequestParam("coach") String coachJson) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Coach coach = objectMapper.readValue(coachJson, Coach.class);
        coach.setCv(file.getBytes());
        coach.setIsapproved(0);
        coach.setEnabled(false);


        return ResponseEntity.ok().body(iservice.registerCoach(coach));
    }

    @GetMapping("/get_waiting_coaches")
    public List<Coach> get_waiting_coaches() {
        return iservice.get_waiting_coaches();
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
    public Coach coachsignin(@PathVariable String email, @PathVariable String password) {
        return iservice.coachsignin(email, password);
    }

    @GetMapping("/get_subscribed_users/{coach_id}")
    public List<User> get_subscribed_users(@PathVariable Long coach_id) {
        return iservice.get_subscribed_users(coach_id);
    }

    @GetMapping("/update_comment/{comment}/{user_id}")
    public User update_comment(@PathVariable String comment, @PathVariable Long user_id) {
        return iservice.update_comment(comment, user_id);
    }

    @PostMapping("/addImageToCoach/{Email}")
    public Coach addImageToCoach(@PathVariable String Email, @RequestParam("file") MultipartFile image) {
        return iservice.addImageToCoach(Email, image);
    }

    @PutMapping("/updateCoach")
    public Coach updateCaoch(@RequestBody Coach coach) {
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

