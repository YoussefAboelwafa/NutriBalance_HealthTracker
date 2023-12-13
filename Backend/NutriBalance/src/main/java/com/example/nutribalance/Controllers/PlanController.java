package com.example.nutribalance.Controllers;

import com.example.nutribalance.Entities.Plan;
import com.example.nutribalance.Services.Iservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/plan")
public class PlanController {
    @Autowired
    private Iservice iservice;

    @PostMapping("/save")
    public Plan saveplan(@RequestBody Plan plan) {
        return iservice.saveplan(plan);
    }
    @GetMapping("/getall")
    public List<Plan> getallplans(){
        return iservice.getallplans();
    }
}
