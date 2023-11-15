package com.example.nutribalance.Services;

import com.example.nutribalance.Entities.Coach;
import com.example.nutribalance.Entities.User;
import com.example.nutribalance.Repositries.CoachRepositry;
import com.example.nutribalance.Repositries.UserRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class Service implements Iservice{
    @Autowired
    private UserRepositry userRepo;
    @Autowired
    private CoachRepositry coachRepo;

    @Override
    public Coach saveCoachWithUsers() {
        Coach coach = new Coach();
        coach.setUser_name("coach1");
        coach.setPassword("coach1");
        coach.setEmail("coach");
        coach.setContact_number("123456789");
        coach.setDescription("fady 3adalat");

        Coach coach2 = new Coach();
        coach.setUser_name("coach2");
        coach.setPassword("coach2");
        coach.setEmail("coach2@gmail.com");
        coach.setContact_number("0123456789");
        coach.setDescription("fady 3adalat");
        User user1 = new User();
        user1.setUser_name("user1");
        user1.setPassword("user133444");
        user1.setEmail("user1@gmail.com");
        user1.setContact_number("123456789");
User user2 = new User();
        user2.setUser_name("user2");
        user2.setPassword("user133444");
        user2.setEmail("user2@gmail.com");
        user2.setContact_number("123456789");
        coach.setUsers(Arrays.asList(user1,user2));
        coach2.setUsers(Arrays.asList(user1,user2));

        coachRepo.save(coach2);
        return coach;

    }
}
