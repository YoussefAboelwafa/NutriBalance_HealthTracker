package com.example.nutribalance.Controllers;

import com.example.nutribalance.Entities.Plan;
import com.example.nutribalance.Entities.Report;
import com.example.nutribalance.Entities.ReportId;
import com.example.nutribalance.Entities.User;
import com.example.nutribalance.Services.Iservice;
import com.example.nutribalance.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private Iservice iservice;

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
