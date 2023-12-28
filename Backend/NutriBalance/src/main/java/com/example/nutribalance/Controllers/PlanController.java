package com.example.nutribalance.controllers;

import com.example.nutribalance.entities.Plan;
import com.example.nutribalance.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/plan")
public class PlanController {
    @Autowired
    private IService iservice;
    @PostMapping("/save")
    public Plan savePlan(@RequestBody Plan plan) {
        return iservice.saveplan(plan);
    }
    @GetMapping("/getall")
    public List<Plan> getAllPlans(){
        return iservice.getAllPlans();
    }
}
