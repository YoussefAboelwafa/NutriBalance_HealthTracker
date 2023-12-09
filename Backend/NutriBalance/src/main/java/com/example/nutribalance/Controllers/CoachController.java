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
    public Coach coachsignin(@PathVariable String email, @PathVariable String password) {
        return iservice.coachsignin(email, password);
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
    public Coach updateCV(@PathVariable("email") String email,@RequestParam("file") MultipartFile file) throws IOException {
        return iservice.updateCoachCV(email,file.getBytes());
    }

}

