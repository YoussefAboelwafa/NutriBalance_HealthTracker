package com.example.nutribalance.Controllers;

import com.example.nutribalance.Entities.Coach;
import com.example.nutribalance.Entities.User;
import com.example.nutribalance.Repositries.CoachRepositry;
import com.example.nutribalance.Repositries.UserRepositry;
import com.example.nutribalance.Services.Iservice;
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
    private  Iservice iservice;
@PostMapping("/save")
    public ResponseEntity<?> savecoach(
                           @RequestParam("file") MultipartFile file,
                           @RequestParam("coach") String coachJson) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Coach coach = objectMapper.readValue(coachJson, Coach.class);
    coach.setCv(file.getBytes());
    return  ResponseEntity.ok().body(iservice.savecoach(coach));
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
}
