package com.example.nutribalance.controllers;

import com.example.nutribalance.dto.ApiResponse;
import com.example.nutribalance.entities.Report;
import com.example.nutribalance.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private IService iservice;

   @GetMapping("/get_reports")
    public List<Report> getReports(){
       return iservice.getReports();
   }
   @DeleteMapping("/deletereport")
    public ApiResponse deleteReport(@RequestParam("user_id") Long user_id,@RequestParam("coach_id") Long coach_id){
         iservice.deleteReport(user_id,coach_id);
         return new ApiResponse(true,"report deleted");
   }
}
