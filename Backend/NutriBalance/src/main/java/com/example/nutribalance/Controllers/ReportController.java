package com.example.nutribalance.Controllers;

import com.example.nutribalance.Entities.Plan;
import com.example.nutribalance.Entities.Report;
import com.example.nutribalance.Services.Iservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
