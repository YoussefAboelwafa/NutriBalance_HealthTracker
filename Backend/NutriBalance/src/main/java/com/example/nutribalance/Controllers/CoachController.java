package com.example.nutribalance.Controllers;

import com.example.nutribalance.Entities.Coach;
import com.example.nutribalance.Entities.User;
import com.example.nutribalance.Repositries.CoachRepositry;
import com.example.nutribalance.Repositries.UserRepositry;
import com.example.nutribalance.Services.Iservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coach")
public class CoachController {
    @Autowired
    private  Iservice iservice;
@PostMapping("/save")
    public Coach savecoach(@RequestBody Coach coach) {
    return iservice.savecoach(coach);
}
}
