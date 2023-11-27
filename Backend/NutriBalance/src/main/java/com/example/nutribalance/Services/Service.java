package com.example.nutribalance.Services;

import com.example.nutribalance.Entities.Coach;
import com.example.nutribalance.Repositries.CoachRepositry;
import com.example.nutribalance.Repositries.SubscriptionRepositry;
import com.example.nutribalance.Repositries.UserRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
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

    @Override
    public Coach savecoach(Coach coach) {
        Optional<Coach> old_coach_1= coachRepo.findByEmail(coach.getEmail());
        Optional<Coach> old_coach_2= coachRepo.findByUsername(coach.getUsername());
        if(old_coach_1.isPresent() || old_coach_2.isPresent()){
            return null;
        }
        return coachRepo.save(coach);
    }

    @Override
    public List<Coach> get_waiting_coaches() {
        return coachRepo.findByisapproved(0);
    }
}

