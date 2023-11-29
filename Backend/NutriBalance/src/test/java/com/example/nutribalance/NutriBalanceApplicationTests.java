package com.example.nutribalance;

import com.example.nutribalance.Controllers.UserController;
import com.example.nutribalance.Entities.Coach;
import com.example.nutribalance.Entities.Plan;
import com.example.nutribalance.Entities.User;
import com.example.nutribalance.Repositries.CoachRepositry;
import com.example.nutribalance.Repositries.UserRepositry;
import com.example.nutribalance.Services.Iservice;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class NutriBalanceApplicationTests {
    @Autowired
    private Iservice service;
    @MockBean
    private CoachRepositry coachRepositry;
    @MockBean
    private UserRepositry userRepositry;
    @MockBean
    private UserController userController;

    @Test
    public void testSaveCoach() {
        Coach coach = new Coach();
        coach.setUsername("coach1");
        coach.setEmail("coach1@gmail.com");
        coach.setPassword("123456");
        coach.setDescription("coach1 description");
        coach.setContact_number("01029979868");
        coach.setIsapproved(0);
        //case1: neither email nor username is found in the database
        when(coachRepositry.findByEmail(coach.getEmail())).thenReturn(Optional.empty());
        when(coachRepositry.findByUsername(coach.getUsername())).thenReturn(Optional.empty());
        when(coachRepositry.save(coach)).thenReturn(coach);
        assertEquals(coach, service.savecoach(coach));
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

    @Test

    public void testApproveCoach() {
        Coach coach = new Coach();
        coach.setUsername("coach1");
        coach.setCoach_id(1L);
        coach.setIsapproved(0);
        //case1: coach is found in the database
        when(coachRepositry.findById(coach.getCoach_id())).thenReturn(Optional.of(coach));
        when(coachRepositry.save(coach)).thenReturn(coach);
        assertEquals(1, service.approvecoach(coach.getCoach_id()).getIsapproved());
        assertEquals(coach, service.approvecoach(coach.getCoach_id()));
        //case2: coach is not found in the database
        when(coachRepositry.findById(coach.getCoach_id())).thenReturn(Optional.empty());
        assertNull(service.approvecoach(coach.getCoach_id()));
    }

    @Test

    public void testSaveUser() {
        User user = new User();
        user.setUsername("user1");
        user.setEmail("user1@gmail.com");
        user.setPassword("12345678");
        user.setContact_number("01029979868");
        //case1: neither email nor username is found in the database
        when(userRepositry.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userRepositry.findByUsername(user.getUsername())).thenReturn(Optional.empty());
        when(userRepositry.save(user)).thenReturn(user);
        assertEquals(user, service.saveuser(user));
        //case2: email is found in the database
        when(userRepositry.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        assertNull(service.saveuser(user));
        //case3: username is found in the database
        when(userRepositry.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userRepositry.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        assertNull(service.saveuser(user));
        //case4: both email and username are found in the database
        when(userRepositry.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(userRepositry.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        assertNull(service.saveuser(user));

    }

    @Test
    public void testUserSignin() {

        User user = new User();
        user.setUsername("user1");
        user.setUser_id(1);
        user.setEmail("user1@gmail.com");
        user.setPassword("123456");
        user.setContact_number("01234567891");
        user.setPlan(new Plan());
        user.setUser_id(1);

        service.saveuser(user);

        when(userRepositry.findByEmail("tvscx@gmail.com")).thenReturn(Optional.empty());
        assertNull(service.usersignin("tvscx@gmail.com", "123456"));

        when(userRepositry.findByEmail("user1@gmail.com")).thenReturn(Optional.of(user));
        assertEquals(user, service.usersignin("user1@gmail.com", "123456"));

        when(userRepositry.findByEmail("user1@gmail.com")).thenReturn(Optional.of(user));
        assertNull(service.usersignin("user1@gmail.com", "111111"));

    }
    @Test
    public void testUserSaveController() {
        User user = new User();
        user.setUsername("user1");
        user.setUser_id(1);
        user.setEmail("user1@gmail.com");
        user.setPassword("123456");
        user.setContact_number("01234567891");

        when(userController.saveUser(user)).thenReturn(user);
        assertEquals(user, userController.saveUser(user));
    }
    @Test
    public void testUserExistingEmailController() {
        User user = new User();
        user.setUsername("user1");
        user.setUser_id(1);
        user.setEmail("user1@gmail.com");
        user.setPassword("123456");
        user.setContact_number("01234567891");

        // 2 users with the same email
        User user2 = new User();
        user2.setUsername("user2");
        user2.setUser_id(2);
        user2.setEmail("user1@gmail.com");
        user2.setPassword("123456");
        user2.setContact_number("01234567891");

        when(userController.saveUser(user2)).thenReturn(null);
        assertNull(userController.saveUser(user2));
    }

    @Test
    public void testCoachSignin(){
        Coach coach = new Coach();
        coach.setUsername("coach1");
        coach.setEmail("coach1@gmail.com");
        coach.setPassword("123456");
        coach.setDescription("coach1 description");
        coach.setContact_number("01029979868");
        coach.setIsapproved(1);

        when(coachRepositry.findByEmail("tvscx@gmail.com")).thenReturn(Optional.empty());
        assertNull(service.coachsignin("tvscx@gmail.com","123456"));

        when(coachRepositry.findByEmail("coach1@gmail.com")).thenReturn(Optional.of(coach));
        assertEquals(coach,service.coachsignin("coach1@gmail.com","123456"));

        when(coachRepositry.findByEmail("coach1@gmail.com")).thenReturn(Optional.of(coach));
        assertNull(service.coachsignin("coach1@gmail.com","111111"));

    }

}
