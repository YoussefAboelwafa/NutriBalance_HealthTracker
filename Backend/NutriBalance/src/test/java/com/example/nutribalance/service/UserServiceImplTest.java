package com.example.nutribalance.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.nutribalance.Entities.Coach;
import com.example.nutribalance.Entities.Plan;
import com.example.nutribalance.Entities.User;
import com.example.nutribalance.Repositries.CoachRepositry;
import com.example.nutribalance.Repositries.UserRepositry;
import com.example.nutribalance.dto.SignUpRequest;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private CoachRepositry coachRepositry;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepositry userRepositry;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#registerNewUser(SignUpRequest)}
     */
    @Test
    void testRegisterNewUser() throws Exception {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes("UTF-8"));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes("UTF-8"));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());

        Coach coach2 = new Coach();
        coach2.setAddress("42 Main St");
        coach2.setCoach_id(1L);
        coach2.setContact_number("42");
        coach2.setCv("AXAXAXAX".getBytes("UTF-8"));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes("UTF-8"));
        coach2.setIsapproved(1);
        coach2.setNo_users_subscribed(1);
        coach2.setPassword("iloveyou");
        coach2.setPlans(new ArrayList<>());
        coach2.setPrice("Price");
        coach2.setRating(1);
        coach2.setUsername("janedoe");
        coach2.setUsers(new ArrayList<>());
        coach2.setUsers_reports(new ArrayList<>());

        Plan plan = new Plan();
        plan.setCoach(coach2);
        plan.setDescription("The characteristics of someone or something");
        plan.setGoal("Goal");
        plan.setPlanName("Plan Name");
        plan.setUsers(new ArrayList<>());

        User user = new User();
        user.setCoach(coach);
        user.setCoaches_reports(new ArrayList<>());
        user.setComment("Comment");
        user.setContact_number("42");
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setImage("AXAXAXAX".getBytes("UTF-8"));
        user.setPassword("iloveyou");
        user.setPlan(plan);
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());
        when(userRepositry.save(Mockito.<User>any())).thenReturn(user);
        doNothing().when(userRepositry).flush();
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
        User actualRegisterNewUserResult = userServiceImpl.registerNewUser(new SignUpRequest("Name", "jane.doe@example.org",
                "iloveyou", "6625550144", "https://example.org/example", "Provider User ID", "Role"));
        verify(userRepositry).flush();
        verify(userRepositry).save(Mockito.<User>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
        assertSame(user, actualRegisterNewUserResult);
    }

    /**
     * Method under test:  {@link UserServiceImpl#registerNewUser(SignUpRequest)}
     */
    @Test
    void testRegisterNewUser2() throws Exception {
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenThrow(new IllegalStateException("USER"));
        assertThrows(IllegalStateException.class, () -> userServiceImpl.registerNewUser(new SignUpRequest("Name",
                "jane.doe@example.org", "iloveyou", "6625550144", "https://example.org/example", "Provider User ID", "Role")));
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test: {@link UserServiceImpl#findUserByEmail(String)}
     */
    @Test
    void testFindUserByEmail() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes("UTF-8"));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes("UTF-8"));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());

        Coach coach2 = new Coach();
        coach2.setAddress("42 Main St");
        coach2.setCoach_id(1L);
        coach2.setContact_number("42");
        coach2.setCv("AXAXAXAX".getBytes("UTF-8"));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes("UTF-8"));
        coach2.setIsapproved(1);
        coach2.setNo_users_subscribed(1);
        coach2.setPassword("iloveyou");
        coach2.setPlans(new ArrayList<>());
        coach2.setPrice("Price");
        coach2.setRating(1);
        coach2.setUsername("janedoe");
        coach2.setUsers(new ArrayList<>());
        coach2.setUsers_reports(new ArrayList<>());

        Plan plan = new Plan();
        plan.setCoach(coach2);
        plan.setDescription("The characteristics of someone or something");
        plan.setGoal("Goal");
        plan.setPlanName("Plan Name");
        plan.setUsers(new ArrayList<>());

        User user = new User();
        user.setCoach(coach);
        user.setCoaches_reports(new ArrayList<>());
        user.setComment("Comment");
        user.setContact_number("42");
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setImage("AXAXAXAX".getBytes("UTF-8"));
        user.setPassword("iloveyou");
        user.setPlan(plan);
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepositry.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        User actualFindUserByEmailResult = userServiceImpl.findUserByEmail("jane.doe@example.org");
        verify(userRepositry).findByEmail(Mockito.<String>any());
        assertSame(user, actualFindUserByEmailResult);
    }


    /**
     * Method under test: {@link UserServiceImpl#findUserById(Long)}
     */
    @Test
    void testFindUserById() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes("UTF-8"));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes("UTF-8"));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());

        Coach coach2 = new Coach();
        coach2.setAddress("42 Main St");
        coach2.setCoach_id(1L);
        coach2.setContact_number("42");
        coach2.setCv("AXAXAXAX".getBytes("UTF-8"));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes("UTF-8"));
        coach2.setIsapproved(1);
        coach2.setNo_users_subscribed(1);
        coach2.setPassword("iloveyou");
        coach2.setPlans(new ArrayList<>());
        coach2.setPrice("Price");
        coach2.setRating(1);
        coach2.setUsername("janedoe");
        coach2.setUsers(new ArrayList<>());
        coach2.setUsers_reports(new ArrayList<>());

        Plan plan = new Plan();
        plan.setCoach(coach2);
        plan.setDescription("The characteristics of someone or something");
        plan.setGoal("Goal");
        plan.setPlanName("Plan Name");
        plan.setUsers(new ArrayList<>());

        User user = new User();
        user.setCoach(coach);
        user.setCoaches_reports(new ArrayList<>());
        user.setComment("Comment");
        user.setContact_number("42");
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setImage("AXAXAXAX".getBytes("UTF-8"));
        user.setPassword("iloveyou");
        user.setPlan(plan);
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepositry.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Optional<User> actualFindUserByIdResult = userServiceImpl.findUserById(1L);
        verify(userRepositry).findById(Mockito.<Long>any());
        assertTrue(actualFindUserByIdResult.isPresent());
        assertSame(ofResult, actualFindUserByIdResult);
    }
}
