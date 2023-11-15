package com.example.nutribalance.Controllers;

import com.example.nutribalance.Entities.Coach;
import com.example.nutribalance.Services.Iservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coach")
public class CoachController {
    @Autowired
    private  Iservice iservice;
    @PostMapping("/save{sort}")
    public Coach saveCoachWithUsers(@RequestBody Coach coach, @PathVariable String sort){
        return iservice.saveCoachWithUsers();
    }

}
