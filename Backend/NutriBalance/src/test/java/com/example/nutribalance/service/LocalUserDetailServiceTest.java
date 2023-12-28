package com.example.nutribalance.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.nutribalance.Entities.Coach;
import com.example.nutribalance.Entities.Plan;
import com.example.nutribalance.Entities.User;
import com.example.nutribalance.dto.LocalUser;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LocalUserDetailService.class})
@ExtendWith(SpringExtension.class)
class LocalUserDetailServiceTest {
    @Autowired
    private LocalUserDetailService localUserDetailService;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link LocalUserDetailService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws UnsupportedEncodingException, UsernameNotFoundException {
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
        when(userService.findUserByEmail(Mockito.<String>any())).thenReturn(user);
        LocalUser actualLoadUserByUsernameResult = localUserDetailService.loadUserByUsername("jane.doe@example.org");
        verify(userService).findUserByEmail(Mockito.<String>any());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        assertEquals("jane.doe@example.org", actualLoadUserByUsernameResult.getUsername());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getName());
        assertNull(actualLoadUserByUsernameResult.getIdToken());
        assertNull(actualLoadUserByUsernameResult.getUserInfo());
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
    }

    /**
     * Method under test: {@link LocalUserDetailService#loadUserById(Long)}
     */
    @Test
    void testLoadUserById() throws UnsupportedEncodingException {
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
        when(userService.findUserById(Mockito.<Long>any())).thenReturn(ofResult);
        LocalUser actualLoadUserByIdResult = localUserDetailService.loadUserById(1L);
        verify(userService).findUserById(Mockito.<Long>any());
        assertEquals("iloveyou", actualLoadUserByIdResult.getPassword());
        assertEquals("jane.doe@example.org", actualLoadUserByIdResult.getUsername());
        assertEquals("janedoe", actualLoadUserByIdResult.getName());
        assertNull(actualLoadUserByIdResult.getIdToken());
        assertNull(actualLoadUserByIdResult.getUserInfo());
        assertEquals(1, actualLoadUserByIdResult.getAuthorities().size());
        assertTrue(actualLoadUserByIdResult.isAccountNonExpired());
        assertTrue(actualLoadUserByIdResult.isAccountNonLocked());
        assertTrue(actualLoadUserByIdResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByIdResult.isEnabled());
    }
}
