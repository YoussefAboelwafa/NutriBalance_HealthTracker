package com.example.nutribalance.controllers;


import com.example.nutribalance.services.IService;
import com.example.nutribalance.dto.ApiResponse;
import com.example.nutribalance.dto.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private IService iservice;

    @GetMapping("/getNotifications")
    public List<NotificationDto> getNotifications(@RequestParam("id") Long id, @RequestParam("role") String role){
        return iservice.getNotifications(id, role);
    }

    @DeleteMapping("/deleteNotification")
    public ApiResponse deleteNotification(@RequestParam("notification_id") Long notification_id){
        return new ApiResponse(true, iservice.deleteNotification(notification_id));
    }
}
