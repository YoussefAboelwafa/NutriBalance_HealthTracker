package com.example.nutribalance.Controllers;

import com.example.nutribalance.Entities.Coach;
import com.example.nutribalance.Entities.User;
import com.example.nutribalance.Mails.EmailDetails;
import com.example.nutribalance.Mails.EmailService;
import com.example.nutribalance.Repositries.CoachRepositry;
import com.example.nutribalance.Repositries.UserRepositry;
import com.example.nutribalance.Services.Iservice;
import com.example.nutribalance.dto.CoachDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        EmailDetails details = new EmailDetails();
        details.setRecipient(coach.getEmail());
        details.setSubject("Waiting for approval Email");
        details.setMsgBody("Dear " + coach.getUsername() + ",\n" +
                "Thank you for your interest in joining our team. We have received your application for the position of Nutrition Coach. We are currently reviewing all applications and will be in touch with those who we feel are best suited for the position.\n" +
                "Thank you again for your interest in working with us. We wish you the best of luck with your job search.\n" +
                "Sincerely,\n" +
                "NutriBalance Team");
        emailService.sendSimpleMail(details);
        coach.setCv(file.getBytes());
        return ResponseEntity.ok().body(iservice.savecoach(coach));
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
    public CoachDto coachsignin(@PathVariable String email, @PathVariable String password) {
        Coach coach = iservice.coachsignin(email, password);
        if (coach != null) {
            return CoachDto.builder()
                    .coach_id(coach.getCoach_id())
                    .username(coach.getUsername())
                    .email(coach.getEmail())
                    .isapproved(coach.getIsapproved())
                    .cv(coach.getCv())
                    .price(coach.getPrice())
                    .rating(coach.getRating())
                    .address(coach.getAddress())
                    .description(coach.getDescription())
                    .no_users_subscribed(coach.getNo_users_subscribed())
                    .contact_number(coach.getContact_number())
                    .image(coach.getImage())
                    .build();
        }
        return null;
    }

    @PostMapping("/addImageToCoach/{Email}")
    public CoachDto addImageToCoach(@PathVariable String Email, @RequestParam("file") MultipartFile image) {
        Coach coach = iservice.addImageToCoach(Email, image);
        return CoachDto.builder()
                .coach_id(coach.getCoach_id())
                .username(coach.getUsername())
                .email(coach.getEmail())
                .isapproved(coach.getIsapproved())
                .cv(coach.getCv())
                .price(coach.getPrice())
                .rating(coach.getRating())
                .address(coach.getAddress())
                .description(coach.getDescription())
                .no_users_subscribed(coach.getNo_users_subscribed())
                .contact_number(coach.getContact_number())
                .image(coach.getImage())
                .build();
    }

    @PutMapping("/updateCoach")
    public CoachDto updateCaoch(@RequestBody Coach coach) {
        Coach coach1 = iservice.updateCoach(coach);
        return CoachDto.builder()
                .coach_id(coach1.getCoach_id())
                .username(coach1.getUsername())
                .email(coach1.getEmail())
                .isapproved(coach1.getIsapproved())
                .cv(coach1.getCv())
                .price(coach1.getPrice())
                .rating(coach1.getRating())
                .address(coach1.getAddress())
                .description(coach1.getDescription())
                .no_users_subscribed(coach1.getNo_users_subscribed())
                .contact_number(coach1.getContact_number())
                .image(coach1.getImage())
                .build();
    }
    @PostMapping("/updateCV/{email}")
    public CoachDto updateCV(@PathVariable("email") String email,@RequestParam("file") MultipartFile file) throws IOException {
        Coach coach1 = iservice.updateCoachCV(email,file.getBytes());
        return CoachDto.builder()
                .coach_id(coach1.getCoach_id())
                .username(coach1.getUsername())
                .email(coach1.getEmail())
                .isapproved(coach1.getIsapproved())
                .cv(coach1.getCv())
                .price(coach1.getPrice())
                .rating(coach1.getRating())
                .address(coach1.getAddress())
                .description(coach1.getDescription())
                .no_users_subscribed(coach1.getNo_users_subscribed())
                .contact_number(coach1.getContact_number())
                .image(coach1.getImage())
                .build();
    }

}

