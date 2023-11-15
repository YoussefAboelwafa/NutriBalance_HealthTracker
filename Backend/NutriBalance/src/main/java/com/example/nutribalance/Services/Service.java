package com.example.nutribalance.Services;

import com.example.nutribalance.Entities.Coach;
import com.example.nutribalance.Entities.User;
import com.example.nutribalance.Repositries.CoachRepositry;
import com.example.nutribalance.Repositries.SubscriptionRepositry;
import com.example.nutribalance.Repositries.UserRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class Service implements Iservice{
    @Autowired
    private UserRepositry userRepo;
    @Autowired
    private CoachRepositry coachRepo;
    @Autowired
    private SubscriptionRepositry subscriptionRepo;

}
