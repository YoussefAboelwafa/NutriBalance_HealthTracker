package com.example.nutribalance;
import com.example.nutribalance.Entities.Coach;
import com.example.nutribalance.Repositries.CoachRepositry;
import com.example.nutribalance.Services.Iservice;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class NutriBalanceApplicationTests {
    @Autowired
    private Iservice service;
    @MockBean
    private CoachRepositry coachRepositry;
    @Test
    public void testSaveCoach() {
        Coach coach = new Coach();
        coach.setUsername("coach1");
        coach.setEmail("coach1@gmail.com");
        coach.setPassword("123456");
        coach.setDescription("coach1 description");
        coach.setContact_number("01029979868");
        coach.set_approved(false);
        //case1: neither email nor username is found in the database
        when(coachRepositry.findByEmail(coach.getEmail())).thenReturn(Optional.empty());
        when(coachRepositry.findByUsername(coach.getUsername())).thenReturn(Optional.empty());
        when(coachRepositry.save(coach)).thenReturn(coach);
        assertEquals(coach,service.savecoach(coach));
        //case2: email is found in the database
        when(coachRepositry.findByEmail(coach.getEmail())).thenReturn(Optional.of(coach));
        assertNull(service.savecoach(coach));
        //case3: username is found in the database
        when(coachRepositry.findByEmail(coach.getEmail())).thenReturn(Optional.empty());
        when(coachRepositry.findByUsername(coach.getUsername())).thenReturn(Optional.of(coach));
        assertNull(service.savecoach(coach));
        //case4: both email and username are found in the database
        when(coachRepositry.findByEmail(coach.getEmail())).thenReturn(Optional.of(coach));
        when(coachRepositry.findByUsername(coach.getUsername())).thenReturn(Optional.of(coach));
        assertNull(service.savecoach(coach));

    }



}
