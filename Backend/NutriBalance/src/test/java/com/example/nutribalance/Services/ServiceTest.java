package com.example.nutribalance.Services;

import com.example.nutribalance.Entities.*;
import com.example.nutribalance.Mails.EmailService;
import com.example.nutribalance.Repositries.*;

import com.example.nutribalance.dto.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {Service.class})
@ExtendWith(SpringExtension.class)
class ServiceTest {
    @MockBean
    private CoachRepositry coachRepositry;
    @MockBean
    private EmailService emailService;
    @MockBean
    private FoodCalorieRepositry foodCalorieRepositry;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private PlanRepositry planRepositry;
    @MockBean
    private ResetPasswordRepository resetPasswordRepository;
    @Autowired
    private Service service;
    @MockBean
    private UserRepositry userRepositry;
    @MockBean
    private ChatRepository chatRepository;
    @MockBean
    private WeightRepositry weightRepository;
    @MockBean
    private NotificationRepository notificationRepository;
   @MockBean
private  ReportRepositry reportRepositry;


    /**
     * Method under test: {@link Service#savecoach(Coach)}
     */
    @Test
    void testSavecoach() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        when(coachRepositry.save(Mockito.any())).thenReturn(coach);

        Coach coach2 = new Coach();
        coach2.setAddress("42 Main St");
        coach2.setCoach_id(1L);
        coach2.setContact_number("42");
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setIsapproved(1);
        coach2.setNo_users_subscribed(1);
        coach2.setPassword("iloveyou");
        coach2.setPlans(new ArrayList<>());
        coach2.setPrice("Price");
        coach2.setRating(1);
        coach2.setUsername("janedoe");
        coach2.setUsers(new ArrayList<>());
        coach2.setUsers_reports(new ArrayList<>());
        Coach actualSavecoachResult = service.savecoach(coach2);
        verify(coachRepositry).save(Mockito.any());
        assertSame(coach, actualSavecoachResult);
    }

    /**
     * Method under test: {@link Service#updateCoach(Coach)}
     */
    @Test
    void testUpdateCoach() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        Optional<Coach> ofResult = Optional.of(coach);

        Coach coach2 = new Coach();
        coach2.setAddress("42 Main St");
        coach2.setCoach_id(1L);
        coach2.setContact_number("42");
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setIsapproved(1);
        coach2.setNo_users_subscribed(1);
        coach2.setPassword("iloveyou");
        coach2.setPlans(new ArrayList<>());
        coach2.setPrice("Price");
        coach2.setRating(1);
        coach2.setUsername("janedoe");
        coach2.setUsers(new ArrayList<>());
        coach2.setUsers_reports(new ArrayList<>());
        when(coachRepositry.save(Mockito.any())).thenReturn(coach2);
        when(coachRepositry.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Coach coach3 = new Coach();
        coach3.setAddress("42 Main St");
        coach3.setCoach_id(1L);
        coach3.setContact_number("42");
        coach3.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach3.setDescription("The characteristics of someone or something");
        coach3.setEmail("jane.doe@example.org");
        coach3.setEnabled(true);
        coach3.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach3.setIsapproved(1);
        coach3.setNo_users_subscribed(1);
        coach3.setPassword("iloveyou");
        coach3.setPlans(new ArrayList<>());
        coach3.setPrice("Price");
        coach3.setRating(1);
        coach3.setUsername("janedoe");
        coach3.setUsers(new ArrayList<>());
        coach3.setUsers_reports(new ArrayList<>());
        Coach actualUpdateCoachResult = service.updateCoach(coach3);
        verify(coachRepositry).findById(Mockito.<Long>any());
        verify(coachRepositry).save(Mockito.any());
        assertSame(coach2, actualUpdateCoachResult);
    }

    /**
     * Method under test: {@link Service#updateCoach(Coach)}
     */
    @Test
    void testUpdateCoach2() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        Optional<Coach> ofResult = Optional.of(coach);
        when(coachRepositry.save(Mockito.any())).thenThrow(new RuntimeException("foo"));
        when(coachRepositry.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Coach coach2 = new Coach();
        coach2.setAddress("42 Main St");
        coach2.setCoach_id(1L);
        coach2.setContact_number("42");
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setIsapproved(1);
        coach2.setNo_users_subscribed(1);
        coach2.setPassword("iloveyou");
        coach2.setPlans(new ArrayList<>());
        coach2.setPrice("Price");
        coach2.setRating(1);
        coach2.setUsername("janedoe");
        coach2.setUsers(new ArrayList<>());
        coach2.setUsers_reports(new ArrayList<>());
        assertThrows(RuntimeException.class, () -> service.updateCoach(coach2));
        verify(coachRepositry).findById(Mockito.<Long>any());
        verify(coachRepositry).save(Mockito.any());
    }

    /**
     * Method under test: {@link Service#updateCoachCV(String, byte[])}
     */
    @Test
    void testUpdateCoachCV() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        Optional<Coach> ofResult = Optional.of(coach);

        Coach coach2 = new Coach();
        coach2.setAddress("42 Main St");
        coach2.setCoach_id(1L);
        coach2.setContact_number("42");
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setIsapproved(1);
        coach2.setNo_users_subscribed(1);
        coach2.setPassword("iloveyou");
        coach2.setPlans(new ArrayList<>());
        coach2.setPrice("Price");
        coach2.setRating(1);
        coach2.setUsername("janedoe");
        coach2.setUsers(new ArrayList<>());
        coach2.setUsers_reports(new ArrayList<>());
        when(coachRepositry.save(Mockito.any())).thenReturn(coach2);
        when(coachRepositry.findByEmail(Mockito.any())).thenReturn(ofResult);
        Coach actualUpdateCoachCVResult = service.updateCoachCV("jane.doe@example.org", "AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        verify(coachRepositry).findByEmail(Mockito.any());
        verify(coachRepositry).save(Mockito.any());
        assertSame(coach2, actualUpdateCoachCVResult);
    }

    /**
     * Method under test: {@link Service#updateCoachCV(String, byte[])}
     */
    @Test
    void testUpdateCoachCV2() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        Optional<Coach> ofResult = Optional.of(coach);
        when(coachRepositry.save(Mockito.any())).thenThrow(new RuntimeException("foo"));
        when(coachRepositry.findByEmail(Mockito.any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class,
                () -> service.updateCoachCV("jane.doe@example.org", "AXAXAXAX".getBytes(StandardCharsets.UTF_8)));
        verify(coachRepositry).findByEmail(Mockito.any());
        verify(coachRepositry).save(Mockito.any());
    }

    /**
     * Method under test: {@link Service#getallplans()}
     */
    @Test
    void testGetallplans() {
        ArrayList<Plan> planList = new ArrayList<>();
        when(planRepositry.findAll()).thenReturn(planList);
        List<Plan> actualGetallplansResult = service.getallplans();
        verify(planRepositry).findAll();
        assertTrue(actualGetallplansResult.isEmpty());
        assertSame(planList, actualGetallplansResult);
    }

    /**
     * Method under test:  {@link Service#getallplans()}
     */
    @Test
    void testGetallplans2() {
        when(planRepositry.findAll()).thenThrow(new RuntimeException("foo"));
        assertThrows(RuntimeException.class, () -> service.getallplans());
        verify(planRepositry).findAll();
    }

    /**
     * Method under test: {@link Service#getPlans(Long)}
     */
    @Test
    void testGetPlans() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        ArrayList<Plan> plans = new ArrayList<>();
        coach.setPlans(plans);
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        Optional<Coach> ofResult = Optional.of(coach);
        when(coachRepositry.findById(Mockito.<Long>any())).thenReturn(ofResult);
        List<Plan> actualPlans = service.getPlans(1L);
        verify(coachRepositry).findById(Mockito.<Long>any());
        assertTrue(actualPlans.isEmpty());
        assertSame(plans, actualPlans);
    }

    /**
     * Method under test: {@link Service#getPlans(Long)}
     */
    @Test
    void testGetPlans2() {
        Optional<Coach> emptyResult = Optional.empty();
        when(coachRepositry.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        List<Plan> actualPlans = service.getPlans(1L);
        verify(coachRepositry).findById(Mockito.<Long>any());
        assertNull(actualPlans);
    }

    /**
     * Method under test:  {@link Service#getPlans(Long)}
     */
    @Test
    void testGetPlans3() {
        when(coachRepositry.findById(Mockito.<Long>any())).thenThrow(new RuntimeException("foo"));
        assertThrows(RuntimeException.class, () -> service.getPlans(1L));
        verify(coachRepositry).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link Service#updatePlan(Plan)}
     */
    @Test
    void testUpdatePlan() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());

        Plan plan = new Plan();
        plan.setCoach(coach);
        plan.setDescription("The characteristics of someone or something");
        plan.setGoal("Goal");
        plan.setPlanName("Plan Name");
        plan.setUsers(new ArrayList<>());
        Optional<Plan> ofResult = Optional.of(plan);

        Coach coach2 = new Coach();
        coach2.setAddress("42 Main St");
        coach2.setCoach_id(1L);
        coach2.setContact_number("42");
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setIsapproved(1);
        coach2.setNo_users_subscribed(1);
        coach2.setPassword("iloveyou");
        coach2.setPlans(new ArrayList<>());
        coach2.setPrice("Price");
        coach2.setRating(1);
        coach2.setUsername("janedoe");
        coach2.setUsers(new ArrayList<>());
        coach2.setUsers_reports(new ArrayList<>());

        Plan plan2 = new Plan();
        plan2.setCoach(coach2);
        plan2.setDescription("The characteristics of someone or something");
        plan2.setGoal("Goal");
        plan2.setPlanName("Plan Name");
        plan2.setUsers(new ArrayList<>());
        when(planRepositry.save(Mockito.any())).thenReturn(plan2);
        when(planRepositry.findById(Mockito.any())).thenReturn(ofResult);

        Coach coach3 = new Coach();
        coach3.setAddress("42 Main St");
        coach3.setCoach_id(1L);
        coach3.setContact_number("42");
        coach3.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach3.setDescription("The characteristics of someone or something");
        coach3.setEmail("jane.doe@example.org");
        coach3.setEnabled(true);
        coach3.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach3.setIsapproved(1);
        coach3.setNo_users_subscribed(1);
        coach3.setPassword("iloveyou");
        coach3.setPlans(new ArrayList<>());
        coach3.setPrice("Price");
        coach3.setRating(1);
        coach3.setUsername("janedoe");
        coach3.setUsers(new ArrayList<>());
        coach3.setUsers_reports(new ArrayList<>());

        Plan plan3 = new Plan();
        plan3.setCoach(coach3);
        plan3.setDescription("The characteristics of someone or something");
        plan3.setGoal("Goal");
        plan3.setPlanName("Plan Name");
        plan3.setUsers(new ArrayList<>());
        Plan actualUpdatePlanResult = service.updatePlan(plan3);
        verify(planRepositry).findById(Mockito.any());
        verify(planRepositry).save(Mockito.any());
        assertSame(plan2, actualUpdatePlanResult);
    }

    /**
     * Method under test: {@link Service#updatePlan(Plan)}
     */
    @Test
    void testUpdatePlan2() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());

        Plan plan = new Plan();
        plan.setCoach(coach);
        plan.setDescription("The characteristics of someone or something");
        plan.setGoal("Goal");
        plan.setPlanName("Plan Name");
        plan.setUsers(new ArrayList<>());
        Optional<Plan> ofResult = Optional.of(plan);
        when(planRepositry.save(Mockito.any())).thenThrow(new RuntimeException("foo"));
        when(planRepositry.findById(Mockito.any())).thenReturn(ofResult);

        Coach coach2 = new Coach();
        coach2.setAddress("42 Main St");
        coach2.setCoach_id(1L);
        coach2.setContact_number("42");
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setIsapproved(1);
        coach2.setNo_users_subscribed(1);
        coach2.setPassword("iloveyou");
        coach2.setPlans(new ArrayList<>());
        coach2.setPrice("Price");
        coach2.setRating(1);
        coach2.setUsername("janedoe");
        coach2.setUsers(new ArrayList<>());
        coach2.setUsers_reports(new ArrayList<>());

        Plan plan2 = new Plan();
        plan2.setCoach(coach2);
        plan2.setDescription("The characteristics of someone or something");
        plan2.setGoal("Goal");
        plan2.setPlanName("Plan Name");
        plan2.setUsers(new ArrayList<>());
        assertThrows(RuntimeException.class, () -> service.updatePlan(plan2));
        verify(planRepositry).findById(Mockito.any());
        verify(planRepositry).save(Mockito.any());
    }

    /**
     * Method under test: {@link Service#deletePlan(String)}
     */
    @Test
    void testDeletePlan() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());

        Plan plan = new Plan();
        plan.setCoach(coach);
        plan.setDescription("The characteristics of someone or something");
        plan.setGoal("Goal");
        plan.setPlanName("Plan Name");
        plan.setUsers(new ArrayList<>());
        Optional<Plan> ofResult = Optional.of(plan);
        doNothing().when(planRepositry).deleteById(Mockito.any());
        when(planRepositry.findById(Mockito.any())).thenReturn(ofResult);
        String actualDeletePlanResult = service.deletePlan("Plan Name");
        verify(planRepositry).deleteById(Mockito.any());
        verify(planRepositry).findById(Mockito.any());
        assertEquals("Plan deleted", actualDeletePlanResult);
    }

    /**
     * Method under test: {@link Service#deletePlan(String)}
     */
    @Test
    void testDeletePlan2() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());

        Plan plan = new Plan();
        plan.setCoach(coach);
        plan.setDescription("The characteristics of someone or something");
        plan.setGoal("Goal");
        plan.setPlanName("Plan Name");
        plan.setUsers(new ArrayList<>());
        Optional<Plan> ofResult = Optional.of(plan);
        doThrow(new RuntimeException("Plan deleted")).when(planRepositry).deleteById(Mockito.any());
        when(planRepositry.findById(Mockito.any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class, () -> service.deletePlan("Plan Name"));
        verify(planRepositry).deleteById(Mockito.any());
        verify(planRepositry).findById(Mockito.any());
    }

    /**
     * Method under test: {@link Service#deletePlan(String)}
     */
    @Test
    void testDeletePlan3() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        coach2.setCv(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
        coach2.setIsapproved(1);
        coach2.setNo_users_subscribed(1);
        coach2.setPassword("iloveyou");
        coach2.setPlans(new ArrayList<>());
        coach2.setPrice("Plan deleted");
        coach2.setRating(1);
        coach2.setUsername("janedoe");
        coach2.setUsers(new ArrayList<>());
        coach2.setUsers_reports(new ArrayList<>());

        Coach coach3 = new Coach();
        coach3.setAddress("42 Main St");
        coach3.setCoach_id(1L);
        coach3.setContact_number("42");
        coach3.setCv(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
        coach3.setDescription("The characteristics of someone or something");
        coach3.setEmail("jane.doe@example.org");
        coach3.setEnabled(true);
        coach3.setImage(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
        coach3.setIsapproved(1);
        coach3.setNo_users_subscribed(1);
        coach3.setPassword("iloveyou");
        coach3.setPlans(new ArrayList<>());
        coach3.setPrice("Plan deleted");
        coach3.setRating(1);
        coach3.setUsername("janedoe");
        coach3.setUsers(new ArrayList<>());
        coach3.setUsers_reports(new ArrayList<>());

        Plan plan = new Plan();
        plan.setCoach(coach3);
        plan.setDescription("The characteristics of someone or something");
        plan.setGoal("Plan deleted");
        plan.setPlanName("Plan deleted");
        plan.setUsers(new ArrayList<>());

        User user = new User();
        user.setCoach(coach2);
        user.setCoaches_reports(new ArrayList<>());
        user.setComment("Plan deleted");
        user.setContact_number("42");
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setImage(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
        user.setPassword("iloveyou");
        user.setPlan(plan);
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());

        ArrayList<User> users = new ArrayList<>();
        users.add(user);

        Plan plan2 = new Plan();
        plan2.setCoach(coach);
        plan2.setDescription("The characteristics of someone or something");
        plan2.setGoal("Goal");
        plan2.setPlanName("Plan Name");
        plan2.setUsers(users);
        Optional<Plan> ofResult = Optional.of(plan2);
        when(planRepositry.findById(Mockito.any())).thenReturn(ofResult);
        String actualDeletePlanResult = service.deletePlan("Plan Name");
        verify(planRepositry).findById(Mockito.any());
        assertEquals("There are users subscribed to this plan", actualDeletePlanResult);
    }

    /**
     * Method under test: {@link Service#get_waiting_coaches()}
     */
    @Test
    void testGet_waiting_coaches() {
        ArrayList<Coach> coachList = new ArrayList<>();
        when(coachRepositry.findByisapproved(anyInt())).thenReturn(coachList);
        List<Coach> actualGet_waiting_coachesResult = service.get_waiting_coaches();
        verify(coachRepositry).findByisapproved(anyInt());
        assertTrue(actualGet_waiting_coachesResult.isEmpty());
        assertSame(coachList, actualGet_waiting_coachesResult);
    }

    /**
     * Method under test:  {@link Service#get_waiting_coaches()}
     */
    @Test
    void testGet_waiting_coaches2() {
        when(coachRepositry.findByisapproved(anyInt())).thenThrow(new RuntimeException("foo"));
        assertThrows(RuntimeException.class, () -> service.get_waiting_coaches());
        verify(coachRepositry).findByisapproved(anyInt());
    }

    /**
     * Method under test: {@link Service#deletecoach(Long)}
     */
    @Test
    void testDeletecoach() {
        doNothing().when(coachRepositry).deleteById(Mockito.<Long>any());
        String actualDeletecoachResult = service.deletecoach(1L);
        verify(coachRepositry).deleteById(Mockito.<Long>any());
        assertEquals("coach deleted", actualDeletecoachResult);
    }

    /**
     * Method under test: {@link Service#deletecoach(Long)}
     */
    @Test
    void testDeletecoach2() {
        doThrow(new RuntimeException("coach deleted")).when(coachRepositry).deleteById(Mockito.<Long>any());
        assertThrows(RuntimeException.class, () -> service.deletecoach(1L));
        verify(coachRepositry).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link Service#approvecoach(Long)}
     */
    @Test
    void testApprovecoach() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        Optional<Coach> ofResult = Optional.of(coach);

        Coach coach2 = new Coach();
        coach2.setAddress("42 Main St");
        coach2.setCoach_id(1L);
        coach2.setContact_number("42");
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setIsapproved(1);
        coach2.setNo_users_subscribed(1);
        coach2.setPassword("iloveyou");
        coach2.setPlans(new ArrayList<>());
        coach2.setPrice("Price");
        coach2.setRating(1);
        coach2.setUsername("janedoe");
        coach2.setUsers(new ArrayList<>());
        coach2.setUsers_reports(new ArrayList<>());
        when(coachRepositry.save(Mockito.any())).thenReturn(coach2);
        when(coachRepositry.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(emailService.sendMemeMail(Mockito.any())).thenReturn("Send Meme Mail");
        Coach actualApprovecoachResult = service.approvecoach(1L);
        verify(emailService).sendMemeMail(Mockito.any());
        verify(coachRepositry).findById(Mockito.<Long>any());
        verify(coachRepositry).save(Mockito.any());
        assertSame(coach2, actualApprovecoachResult);
    }

    /**
     * Method under test: {@link Service#approvecoach(Long)}
     */
    @Test
    void testApprovecoach2() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        Optional<Coach> ofResult = Optional.of(coach);
        when(coachRepositry.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(emailService.sendMemeMail(Mockito.any()))
                .thenThrow(new RuntimeException("Approved Coach Email"));
        assertThrows(RuntimeException.class, () -> service.approvecoach(1L));
        verify(emailService).sendMemeMail(Mockito.any());
        verify(coachRepositry).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link Service#saveuser(User)}
     */
    @Test
    void testSaveuser() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        user.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user.setPassword("iloveyou");
        user.setPlan(plan);
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepositry.findByEmail(Mockito.any())).thenReturn(ofResult);

        Coach coach3 = new Coach();
        coach3.setAddress("42 Main St");
        coach3.setCoach_id(1L);
        coach3.setContact_number("42");
        coach3.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach3.setDescription("The characteristics of someone or something");
        coach3.setEmail("jane.doe@example.org");
        coach3.setEnabled(true);
        coach3.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach3.setIsapproved(1);
        coach3.setNo_users_subscribed(1);
        coach3.setPassword("iloveyou");
        coach3.setPlans(new ArrayList<>());
        coach3.setPrice("Price");
        coach3.setRating(1);
        coach3.setUsername("janedoe");
        coach3.setUsers(new ArrayList<>());
        coach3.setUsers_reports(new ArrayList<>());

        Coach coach4 = new Coach();
        coach4.setAddress("42 Main St");
        coach4.setCoach_id(1L);
        coach4.setContact_number("42");
        coach4.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach4.setDescription("The characteristics of someone or something");
        coach4.setEmail("jane.doe@example.org");
        coach4.setEnabled(true);
        coach4.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach4.setIsapproved(1);
        coach4.setNo_users_subscribed(1);
        coach4.setPassword("iloveyou");
        coach4.setPlans(new ArrayList<>());
        coach4.setPrice("Price");
        coach4.setRating(1);
        coach4.setUsername("janedoe");
        coach4.setUsers(new ArrayList<>());
        coach4.setUsers_reports(new ArrayList<>());

        Plan plan2 = new Plan();
        plan2.setCoach(coach4);
        plan2.setDescription("The characteristics of someone or something");
        plan2.setGoal("Goal");
        plan2.setPlanName("Plan Name");
        plan2.setUsers(new ArrayList<>());

        User user2 = new User();
        user2.setCoach(coach3);
        user2.setCoaches_reports(new ArrayList<>());
        user2.setComment("Comment");
        user2.setContact_number("42");
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user2.setPassword("iloveyou");
        user2.setPlan(plan2);
        user2.setUser_id(1L);
        user2.setUsername("janedoe");
        user2.setWeights(new ArrayList<>());
        User actualSaveuserResult = service.saveuser(user2);
        verify(userRepositry).findByEmail(Mockito.any());
        assertNull(actualSaveuserResult);
    }

    /**
     * Method under test:  {@link Service#saveuser(User)}
     */
    @Test
    void testSaveuser2() throws UnsupportedEncodingException {
        when(userRepositry.findByEmail(Mockito.any())).thenThrow(new RuntimeException("user"));

        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        user.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user.setPassword("iloveyou");
        user.setPlan(plan);
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());
        assertThrows(RuntimeException.class, () -> service.saveuser(user));
        verify(userRepositry).findByEmail(Mockito.any());
    }

    /**
     * Method under test: {@link Service#updateUser(User)}
     */
    @Test
    void testUpdateUser() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        user.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user.setPassword("iloveyou");
        user.setPlan(plan);
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);

        Coach coach3 = new Coach();
        coach3.setAddress("42 Main St");
        coach3.setCoach_id(1L);
        coach3.setContact_number("42");
        coach3.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach3.setDescription("The characteristics of someone or something");
        coach3.setEmail("jane.doe@example.org");
        coach3.setEnabled(true);
        coach3.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach3.setIsapproved(1);
        coach3.setNo_users_subscribed(1);
        coach3.setPassword("iloveyou");
        coach3.setPlans(new ArrayList<>());
        coach3.setPrice("Price");
        coach3.setRating(1);
        coach3.setUsername("janedoe");
        coach3.setUsers(new ArrayList<>());
        coach3.setUsers_reports(new ArrayList<>());

        Coach coach4 = new Coach();
        coach4.setAddress("42 Main St");
        coach4.setCoach_id(1L);
        coach4.setContact_number("42");
        coach4.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach4.setDescription("The characteristics of someone or something");
        coach4.setEmail("jane.doe@example.org");
        coach4.setEnabled(true);
        coach4.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach4.setIsapproved(1);
        coach4.setNo_users_subscribed(1);
        coach4.setPassword("iloveyou");
        coach4.setPlans(new ArrayList<>());
        coach4.setPrice("Price");
        coach4.setRating(1);
        coach4.setUsername("janedoe");
        coach4.setUsers(new ArrayList<>());
        coach4.setUsers_reports(new ArrayList<>());

        Plan plan2 = new Plan();
        plan2.setCoach(coach4);
        plan2.setDescription("The characteristics of someone or something");
        plan2.setGoal("Goal");
        plan2.setPlanName("Plan Name");
        plan2.setUsers(new ArrayList<>());

        User user2 = new User();
        user2.setCoach(coach3);
        user2.setCoaches_reports(new ArrayList<>());
        user2.setComment("Comment");
        user2.setContact_number("42");
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user2.setPassword("iloveyou");
        user2.setPlan(plan2);
        user2.setUser_id(1L);
        user2.setUsername("janedoe");
        user2.setWeights(new ArrayList<>());
        when(userRepositry.save(Mockito.any())).thenReturn(user2);
        when(userRepositry.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Coach coach5 = new Coach();
        coach5.setAddress("42 Main St");
        coach5.setCoach_id(1L);
        coach5.setContact_number("42");
        coach5.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach5.setDescription("The characteristics of someone or something");
        coach5.setEmail("jane.doe@example.org");
        coach5.setEnabled(true);
        coach5.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach5.setIsapproved(1);
        coach5.setNo_users_subscribed(1);
        coach5.setPassword("iloveyou");
        coach5.setPlans(new ArrayList<>());
        coach5.setPrice("Price");
        coach5.setRating(1);
        coach5.setUsername("janedoe");
        coach5.setUsers(new ArrayList<>());
        coach5.setUsers_reports(new ArrayList<>());

        Coach coach6 = new Coach();
        coach6.setAddress("42 Main St");
        coach6.setCoach_id(1L);
        coach6.setContact_number("42");
        coach6.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach6.setDescription("The characteristics of someone or something");
        coach6.setEmail("jane.doe@example.org");
        coach6.setEnabled(true);
        coach6.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach6.setIsapproved(1);
        coach6.setNo_users_subscribed(1);
        coach6.setPassword("iloveyou");
        coach6.setPlans(new ArrayList<>());
        coach6.setPrice("Price");
        coach6.setRating(1);
        coach6.setUsername("janedoe");
        coach6.setUsers(new ArrayList<>());
        coach6.setUsers_reports(new ArrayList<>());

        Plan plan3 = new Plan();
        plan3.setCoach(coach6);
        plan3.setDescription("The characteristics of someone or something");
        plan3.setGoal("Goal");
        plan3.setPlanName("Plan Name");
        plan3.setUsers(new ArrayList<>());

        User user3 = new User();
        user3.setCoach(coach5);
        user3.setCoaches_reports(new ArrayList<>());
        user3.setComment("Comment");
        user3.setContact_number("42");
        user3.setEmail("jane.doe@example.org");
        user3.setEnabled(true);
        user3.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user3.setPassword("iloveyou");
        user3.setPlan(plan3);
        user3.setUser_id(1L);
        user3.setUsername("janedoe");
        user3.setWeights(new ArrayList<>());
        User actualUpdateUserResult = service.updateUser(user3);
        verify(userRepositry).findById(Mockito.<Long>any());
        verify(userRepositry).save(Mockito.any());
        assertSame(user2, actualUpdateUserResult);
    }

    /**
     * Method under test: {@link Service#updateUser(User)}
     */
    @Test
    void testUpdateUser2() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        user.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user.setPassword("iloveyou");
        user.setPlan(plan);
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepositry.save(Mockito.any())).thenThrow(new RuntimeException("foo"));
        when(userRepositry.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Coach coach3 = new Coach();
        coach3.setAddress("42 Main St");
        coach3.setCoach_id(1L);
        coach3.setContact_number("42");
        coach3.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach3.setDescription("The characteristics of someone or something");
        coach3.setEmail("jane.doe@example.org");
        coach3.setEnabled(true);
        coach3.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach3.setIsapproved(1);
        coach3.setNo_users_subscribed(1);
        coach3.setPassword("iloveyou");
        coach3.setPlans(new ArrayList<>());
        coach3.setPrice("Price");
        coach3.setRating(1);
        coach3.setUsername("janedoe");
        coach3.setUsers(new ArrayList<>());
        coach3.setUsers_reports(new ArrayList<>());

        Coach coach4 = new Coach();
        coach4.setAddress("42 Main St");
        coach4.setCoach_id(1L);
        coach4.setContact_number("42");
        coach4.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach4.setDescription("The characteristics of someone or something");
        coach4.setEmail("jane.doe@example.org");
        coach4.setEnabled(true);
        coach4.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach4.setIsapproved(1);
        coach4.setNo_users_subscribed(1);
        coach4.setPassword("iloveyou");
        coach4.setPlans(new ArrayList<>());
        coach4.setPrice("Price");
        coach4.setRating(1);
        coach4.setUsername("janedoe");
        coach4.setUsers(new ArrayList<>());
        coach4.setUsers_reports(new ArrayList<>());

        Plan plan2 = new Plan();
        plan2.setCoach(coach4);
        plan2.setDescription("The characteristics of someone or something");
        plan2.setGoal("Goal");
        plan2.setPlanName("Plan Name");
        plan2.setUsers(new ArrayList<>());

        User user2 = new User();
        user2.setCoach(coach3);
        user2.setCoaches_reports(new ArrayList<>());
        user2.setComment("Comment");
        user2.setContact_number("42");
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user2.setPassword("iloveyou");
        user2.setPlan(plan2);
        user2.setUser_id(1L);
        user2.setUsername("janedoe");
        user2.setWeights(new ArrayList<>());
        assertThrows(RuntimeException.class, () -> service.updateUser(user2));
        verify(userRepositry).findById(Mockito.<Long>any());
        verify(userRepositry).save(Mockito.any());
    }

    /**
     * Method under test: {@link Service#addImageToUser(String, MultipartFile)}
     */
    @Test
    void testAddImageToUser() throws IOException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        user.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user.setPassword("iloveyou");
        user.setPlan(plan);
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);

        Coach coach3 = new Coach();
        coach3.setAddress("42 Main St");
        coach3.setCoach_id(1L);
        coach3.setContact_number("42");
        coach3.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach3.setDescription("The characteristics of someone or something");
        coach3.setEmail("jane.doe@example.org");
        coach3.setEnabled(true);
        coach3.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach3.setIsapproved(1);
        coach3.setNo_users_subscribed(1);
        coach3.setPassword("iloveyou");
        coach3.setPlans(new ArrayList<>());
        coach3.setPrice("Price");
        coach3.setRating(1);
        coach3.setUsername("janedoe");
        coach3.setUsers(new ArrayList<>());
        coach3.setUsers_reports(new ArrayList<>());

        Coach coach4 = new Coach();
        coach4.setAddress("42 Main St");
        coach4.setCoach_id(1L);
        coach4.setContact_number("42");
        coach4.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach4.setDescription("The characteristics of someone or something");
        coach4.setEmail("jane.doe@example.org");
        coach4.setEnabled(true);
        coach4.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach4.setIsapproved(1);
        coach4.setNo_users_subscribed(1);
        coach4.setPassword("iloveyou");
        coach4.setPlans(new ArrayList<>());
        coach4.setPrice("Price");
        coach4.setRating(1);
        coach4.setUsername("janedoe");
        coach4.setUsers(new ArrayList<>());
        coach4.setUsers_reports(new ArrayList<>());

        Plan plan2 = new Plan();
        plan2.setCoach(coach4);
        plan2.setDescription("The characteristics of someone or something");
        plan2.setGoal("Goal");
        plan2.setPlanName("Plan Name");
        plan2.setUsers(new ArrayList<>());

        User user2 = new User();
        user2.setCoach(coach3);
        user2.setCoaches_reports(new ArrayList<>());
        user2.setComment("Comment");
        user2.setContact_number("42");
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user2.setPassword("iloveyou");
        user2.setPlan(plan2);
        user2.setUser_id(1L);
        user2.setUsername("janedoe");
        user2.setWeights(new ArrayList<>());
        when(userRepositry.save(Mockito.any())).thenReturn(user2);
        when(userRepositry.findByEmail(Mockito.any())).thenReturn(ofResult);
        User actualAddImageToUserResult = service.addImageToUser("jane.doe@example.org",
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes(StandardCharsets.UTF_8))));
        verify(userRepositry).findByEmail(Mockito.any());
        verify(userRepositry).save(Mockito.any());
        assertSame(user, actualAddImageToUserResult);
        byte[] expectedImage = "AXAXAXAX".getBytes(StandardCharsets.UTF_8);
        assertArrayEquals(expectedImage, actualAddImageToUserResult.getImage());
    }

    /**
     * Method under test: {@link Service#addImageToUser(String, MultipartFile)}
     */
    @Test
    void testAddImageToUser2() throws IOException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        user.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user.setPassword("iloveyou");
        user.setPlan(plan);
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepositry.save(Mockito.any())).thenThrow(new RuntimeException("foo"));
        when(userRepositry.findByEmail(Mockito.any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class, () -> service.addImageToUser("jane.doe@example.org",
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes(StandardCharsets.UTF_8)))));
        verify(userRepositry).findByEmail(Mockito.any());
        verify(userRepositry).save(Mockito.any());
    }

    /**
     * Method under test: {@link Service#verify(String)}
     */
    @Test
    void testVerify() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        when(coachRepositry.save(Mockito.any())).thenReturn(coach);

        ResetPassword resetPassword = new ResetPassword();
        resetPassword.setEmail("jane.doe@example.org");
        resetPassword.setId(1L);
        resetPassword.setToken("ABC123");
        resetPassword.setUsername("janedoe");
        doNothing().when(resetPasswordRepository).deleteById(Mockito.<Long>any());
        when(resetPasswordRepository.findByToken(Mockito.any())).thenReturn(resetPassword);

        Coach coach2 = new Coach();
        coach2.setAddress("42 Main St");
        coach2.setCoach_id(1L);
        coach2.setContact_number("42");
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setIsapproved(1);
        coach2.setNo_users_subscribed(1);
        coach2.setPassword("iloveyou");
        coach2.setPlans(new ArrayList<>());
        coach2.setPrice("Price");
        coach2.setRating(1);
        coach2.setUsername("janedoe");
        coach2.setUsers(new ArrayList<>());
        coach2.setUsers_reports(new ArrayList<>());

        Coach coach3 = new Coach();
        coach3.setAddress("42 Main St");
        coach3.setCoach_id(1L);
        coach3.setContact_number("42");
        coach3.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach3.setDescription("The characteristics of someone or something");
        coach3.setEmail("jane.doe@example.org");
        coach3.setEnabled(true);
        coach3.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach3.setIsapproved(1);
        coach3.setNo_users_subscribed(1);
        coach3.setPassword("iloveyou");
        coach3.setPlans(new ArrayList<>());
        coach3.setPrice("Price");
        coach3.setRating(1);
        coach3.setUsername("janedoe");
        coach3.setUsers(new ArrayList<>());
        coach3.setUsers_reports(new ArrayList<>());

        Plan plan = new Plan();
        plan.setCoach(coach3);
        plan.setDescription("The characteristics of someone or something");
        plan.setGoal("Goal");
        plan.setPlanName("Plan Name");
        plan.setUsers(new ArrayList<>());

        User user = new User();
        user.setCoach(coach2);
        user.setCoaches_reports(new ArrayList<>());
        user.setComment("Comment");
        user.setContact_number("42");
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user.setPassword("iloveyou");
        user.setPlan(plan);
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);

        Coach coach4 = new Coach();
        coach4.setAddress("42 Main St");
        coach4.setCoach_id(1L);
        coach4.setContact_number("42");
        coach4.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach4.setDescription("The characteristics of someone or something");
        coach4.setEmail("jane.doe@example.org");
        coach4.setEnabled(true);
        coach4.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach4.setIsapproved(1);
        coach4.setNo_users_subscribed(1);
        coach4.setPassword("iloveyou");
        coach4.setPlans(new ArrayList<>());
        coach4.setPrice("Price");
        coach4.setRating(1);
        coach4.setUsername("janedoe");
        coach4.setUsers(new ArrayList<>());
        coach4.setUsers_reports(new ArrayList<>());

        Coach coach5 = new Coach();
        coach5.setAddress("42 Main St");
        coach5.setCoach_id(1L);
        coach5.setContact_number("42");
        coach5.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach5.setDescription("The characteristics of someone or something");
        coach5.setEmail("jane.doe@example.org");
        coach5.setEnabled(true);
        coach5.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach5.setIsapproved(1);
        coach5.setNo_users_subscribed(1);
        coach5.setPassword("iloveyou");
        coach5.setPlans(new ArrayList<>());
        coach5.setPrice("Price");
        coach5.setRating(1);
        coach5.setUsername("janedoe");
        coach5.setUsers(new ArrayList<>());
        coach5.setUsers_reports(new ArrayList<>());

        Plan plan2 = new Plan();
        plan2.setCoach(coach5);
        plan2.setDescription("The characteristics of someone or something");
        plan2.setGoal("Goal");
        plan2.setPlanName("Plan Name");
        plan2.setUsers(new ArrayList<>());

        User user2 = new User();
        user2.setCoach(coach4);
        user2.setCoaches_reports(new ArrayList<>());
        user2.setComment("Comment");
        user2.setContact_number("42");
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user2.setPassword("iloveyou");
        user2.setPlan(plan2);
        user2.setUser_id(1L);
        user2.setUsername("janedoe");
        user2.setWeights(new ArrayList<>());
        when(userRepositry.save(Mockito.any())).thenReturn(user2);
        when(userRepositry.findByEmail(Mockito.any())).thenReturn(ofResult);
        boolean actualVerifyResult = service.verify("Verification Code");
        verify(resetPasswordRepository).findByToken(Mockito.any());
        verify(userRepositry).findByEmail(Mockito.any());
        verify(resetPasswordRepository).deleteById(Mockito.<Long>any());
        verify(userRepositry).save(Mockito.any());
        assertTrue(actualVerifyResult);
    }

    /**
     * Method under test: {@link Service#verify(String)}
     */
    @Test
    void testVerify2() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        when(coachRepositry.save(Mockito.any())).thenReturn(coach);

        ResetPassword resetPassword = new ResetPassword();
        resetPassword.setEmail("jane.doe@example.org");
        resetPassword.setId(1L);
        resetPassword.setToken("ABC123");
        resetPassword.setUsername("janedoe");
        when(resetPasswordRepository.findByToken(Mockito.any())).thenReturn(resetPassword);

        Coach coach2 = new Coach();
        coach2.setAddress("42 Main St");
        coach2.setCoach_id(1L);
        coach2.setContact_number("42");
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setIsapproved(1);
        coach2.setNo_users_subscribed(1);
        coach2.setPassword("iloveyou");
        coach2.setPlans(new ArrayList<>());
        coach2.setPrice("Price");
        coach2.setRating(1);
        coach2.setUsername("janedoe");
        coach2.setUsers(new ArrayList<>());
        coach2.setUsers_reports(new ArrayList<>());

        Coach coach3 = new Coach();
        coach3.setAddress("42 Main St");
        coach3.setCoach_id(1L);
        coach3.setContact_number("42");
        coach3.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach3.setDescription("The characteristics of someone or something");
        coach3.setEmail("jane.doe@example.org");
        coach3.setEnabled(true);
        coach3.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach3.setIsapproved(1);
        coach3.setNo_users_subscribed(1);
        coach3.setPassword("iloveyou");
        coach3.setPlans(new ArrayList<>());
        coach3.setPrice("Price");
        coach3.setRating(1);
        coach3.setUsername("janedoe");
        coach3.setUsers(new ArrayList<>());
        coach3.setUsers_reports(new ArrayList<>());

        Plan plan = new Plan();
        plan.setCoach(coach3);
        plan.setDescription("The characteristics of someone or something");
        plan.setGoal("Goal");
        plan.setPlanName("Plan Name");
        plan.setUsers(new ArrayList<>());

        User user = new User();
        user.setCoach(coach2);
        user.setCoaches_reports(new ArrayList<>());
        user.setComment("Comment");
        user.setContact_number("42");
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user.setPassword("iloveyou");
        user.setPlan(plan);
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepositry.save(Mockito.any())).thenThrow(new RuntimeException("user"));
        when(userRepositry.findByEmail(Mockito.any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class, () -> service.verify("Verification Code"));
        verify(resetPasswordRepository).findByToken(Mockito.any());
        verify(userRepositry).findByEmail(Mockito.any());
        verify(userRepositry).save(Mockito.any());
    }

    /**
     * Method under test: {@link Service#registerCoach(Coach)}
     */
    @Test
    void testRegisterCoach() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        Optional<Coach> ofResult = Optional.of(coach);
        when(coachRepositry.findByEmail(Mockito.any())).thenReturn(ofResult);

        Coach coach2 = new Coach();
        coach2.setAddress("42 Main St");
        coach2.setCoach_id(1L);
        coach2.setContact_number("42");
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setIsapproved(1);
        coach2.setNo_users_subscribed(1);
        coach2.setPassword("iloveyou");
        coach2.setPlans(new ArrayList<>());
        coach2.setPrice("Price");
        coach2.setRating(1);
        coach2.setUsername("janedoe");
        coach2.setUsers(new ArrayList<>());
        coach2.setUsers_reports(new ArrayList<>());
        Coach actualRegisterCoachResult = service.registerCoach(coach2);
        verify(coachRepositry).findByEmail(Mockito.any());
        assertNull(actualRegisterCoachResult);
    }

    /**
     * Method under test: {@link Service#registerCoach(Coach)}
     */
    @Test
    void testRegisterCoach2() throws UnsupportedEncodingException {
        Optional<Coach> emptyResult = Optional.empty();
        when(coachRepositry.findByEmail(Mockito.any())).thenReturn(emptyResult);
        when(emailService.sendMemeMail(Mockito.any())).thenReturn("Send Meme Mail");

        ResetPassword resetPassword = new ResetPassword();
        resetPassword.setEmail("jane.doe@example.org");
        resetPassword.setId(1L);
        resetPassword.setToken("ABC123");
        resetPassword.setUsername("janedoe");

        ResetPassword resetPassword2 = new ResetPassword();
        resetPassword2.setEmail("jane.doe@example.org");
        resetPassword2.setId(1L);
        resetPassword2.setToken("ABC123");
        resetPassword2.setUsername("janedoe");
        doNothing().when(resetPasswordRepository).deleteById(Mockito.<Long>any());
        when(resetPasswordRepository.findByEmail(Mockito.any())).thenReturn(resetPassword);
        when(resetPasswordRepository.save(Mockito.any())).thenReturn(resetPassword2);
        when(passwordEncoder.encode(Mockito.any())).thenReturn("secret");

        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        Coach actualRegisterCoachResult = service.registerCoach(coach);
        verify(emailService).sendMemeMail(Mockito.any());
        verify(coachRepositry).findByEmail(Mockito.any());
        verify(resetPasswordRepository).findByEmail(Mockito.any());
        verify(resetPasswordRepository).deleteById(Mockito.<Long>any());
        verify(resetPasswordRepository).save(Mockito.any());
        verify(passwordEncoder).encode(Mockito.any());
        assertEquals("secret", coach.getPassword());
        assertNull(actualRegisterCoachResult);
        assertFalse(coach.isEnabled());
    }

    /**
     * Method under test: {@link Service#registerCoach(Coach)}
     */
    @Test
    void testRegisterCoach3() throws UnsupportedEncodingException {
        Optional<Coach> emptyResult = Optional.empty();
        when(coachRepositry.findByEmail(Mockito.any())).thenReturn(emptyResult);
        when(passwordEncoder.encode(Mockito.any())).thenThrow(new RuntimeException("coach"));

        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        assertThrows(RuntimeException.class, () -> service.registerCoach(coach));
        verify(coachRepositry).findByEmail(Mockito.any());
        verify(passwordEncoder).encode(Mockito.any());
    }

    /**
     * Method under test: {@link Service#registerCoach(Coach)}
     */
    @Test
    void testRegisterCoach4() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        when(coachRepositry.save(Mockito.any())).thenReturn(coach);
        Optional<Coach> emptyResult = Optional.empty();
        when(coachRepositry.findByEmail(Mockito.any())).thenReturn(emptyResult);
        when(emailService.sendMemeMail(Mockito.any())).thenReturn("done");

        ResetPassword resetPassword = new ResetPassword();
        resetPassword.setEmail("jane.doe@example.org");
        resetPassword.setId(1L);
        resetPassword.setToken("ABC123");
        resetPassword.setUsername("janedoe");

        ResetPassword resetPassword2 = new ResetPassword();
        resetPassword2.setEmail("jane.doe@example.org");
        resetPassword2.setId(1L);
        resetPassword2.setToken("ABC123");
        resetPassword2.setUsername("janedoe");
        doNothing().when(resetPasswordRepository).deleteById(Mockito.<Long>any());
        when(resetPasswordRepository.findByEmail(Mockito.any())).thenReturn(resetPassword);
        when(resetPasswordRepository.save(Mockito.any())).thenReturn(resetPassword2);
        when(passwordEncoder.encode(Mockito.any())).thenReturn("secret");

        Coach coach2 = new Coach();
        coach2.setAddress("42 Main St");
        coach2.setCoach_id(1L);
        coach2.setContact_number("42");
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setIsapproved(1);
        coach2.setNo_users_subscribed(1);
        coach2.setPassword("iloveyou");
        coach2.setPlans(new ArrayList<>());
        coach2.setPrice("Price");
        coach2.setRating(1);
        coach2.setUsername("janedoe");
        coach2.setUsers(new ArrayList<>());
        coach2.setUsers_reports(new ArrayList<>());
        Coach actualRegisterCoachResult = service.registerCoach(coach2);
        verify(emailService).sendMemeMail(Mockito.any());
        verify(coachRepositry).findByEmail(Mockito.any());
        verify(resetPasswordRepository).findByEmail(Mockito.any());
        verify(resetPasswordRepository).deleteById(Mockito.<Long>any());
        verify(coachRepositry).save(Mockito.any());
        verify(resetPasswordRepository).save(Mockito.any());
        verify(passwordEncoder).encode(Mockito.any());
        assertEquals("secret", coach2.getPassword());
        assertFalse(coach2.isEnabled());
        assertSame(coach, actualRegisterCoachResult);
    }

    /**
     * Method under test: {@link Service#usersignin(String, String)}
     */
    @Test
    void testUsersignin() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        user.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user.setPassword("iloveyou");
        user.setPlan(plan);
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepositry.findByEmail(Mockito.any())).thenReturn(ofResult);
        when(passwordEncoder.matches(Mockito.any(), Mockito.any())).thenReturn(true);
        User actualUsersigninResult = service.usersignin("jane.doe@example.org", "iloveyou");
        verify(userRepositry).findByEmail(Mockito.any());
        verify(passwordEncoder).matches(Mockito.any(), Mockito.any());
        assertSame(user, actualUsersigninResult);
    }

    /**
     * Method under test: {@link Service#usersignin(String, String)}
     */
    @Test
    void testUsersignin2() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        user.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user.setPassword("iloveyou");
        user.setPlan(plan);
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepositry.findByEmail(Mockito.any())).thenReturn(ofResult);
        when(passwordEncoder.matches(Mockito.any(), Mockito.any()))
                .thenThrow(new RuntimeException("foo"));
        assertThrows(RuntimeException.class, () -> service.usersignin("jane.doe@example.org", "iloveyou"));
        verify(userRepositry).findByEmail(Mockito.any());
        verify(passwordEncoder).matches(Mockito.any(), Mockito.any());
    }

    /**
     * Method under test: {@link Service#usersignin(String, String)}
     */
    @Test
    void testUsersignin3() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        User user = mock(User.class);
        when(user.getPassword()).thenThrow(new RuntimeException("foo"));
        when(user.isEnabled()).thenReturn(true);
        doNothing().when(user).setCoach(Mockito.any());
        doNothing().when(user).setCoaches_reports(Mockito.any());
        doNothing().when(user).setComment(Mockito.any());
        doNothing().when(user).setContact_number(Mockito.any());
        doNothing().when(user).setEmail(Mockito.any());
        doNothing().when(user).setEnabled(anyBoolean());
        doNothing().when(user).setImage(Mockito.any());
        doNothing().when(user).setPassword(Mockito.any());
        doNothing().when(user).setPlan(Mockito.any());
        doNothing().when(user).setUser_id(anyLong());
        doNothing().when(user).setUsername(Mockito.any());
        doNothing().when(user).setWeights(Mockito.any());
        user.setCoach(coach);
        user.setCoaches_reports(new ArrayList<>());
        user.setComment("Comment");
        user.setContact_number("42");
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user.setPassword("iloveyou");
        user.setPlan(plan);
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepositry.findByEmail(Mockito.any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class, () -> service.usersignin("jane.doe@example.org", "iloveyou"));
        verify(user).getPassword();
        verify(user).isEnabled();
        verify(user).setCoach(Mockito.any());
        verify(user).setCoaches_reports(Mockito.any());
        verify(user).setComment(Mockito.any());
        verify(user).setContact_number(Mockito.any());
        verify(user).setEmail(Mockito.any());
        verify(user).setEnabled(anyBoolean());
        verify(user).setImage(Mockito.any());
        verify(user).setPassword(Mockito.any());
        verify(user).setPlan(Mockito.any());
        verify(user).setUser_id(anyLong());
        verify(user).setUsername(Mockito.any());
        verify(user).setWeights(Mockito.any());
        verify(userRepositry).findByEmail(Mockito.any());
    }

    /**
     * Method under test: {@link Service#usersignin(String, String)}
     */
    @Test
    void testUsersignin4() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        User user = mock(User.class);
        when(user.isEnabled()).thenReturn(false);
        doNothing().when(user).setCoach(Mockito.any());
        doNothing().when(user).setCoaches_reports(Mockito.any());
        doNothing().when(user).setComment(Mockito.any());
        doNothing().when(user).setContact_number(Mockito.any());
        doNothing().when(user).setEmail(Mockito.any());
        doNothing().when(user).setEnabled(anyBoolean());
        doNothing().when(user).setImage(Mockito.any());
        doNothing().when(user).setPassword(Mockito.any());
        doNothing().when(user).setPlan(Mockito.any());
        doNothing().when(user).setUser_id(anyLong());
        doNothing().when(user).setUsername(Mockito.any());
        doNothing().when(user).setWeights(Mockito.any());
        user.setCoach(coach);
        user.setCoaches_reports(new ArrayList<>());
        user.setComment("Comment");
        user.setContact_number("42");
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user.setPassword("iloveyou");
        user.setPlan(plan);
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepositry.findByEmail(Mockito.any())).thenReturn(ofResult);
        User actualUsersigninResult = service.usersignin("jane.doe@example.org", "iloveyou");
        verify(user).isEnabled();
        verify(user).setCoach(Mockito.any());
        verify(user).setCoaches_reports(Mockito.any());
        verify(user).setComment(Mockito.any());
        verify(user).setContact_number(Mockito.any());
        verify(user).setEmail(Mockito.any());
        verify(user).setEnabled(anyBoolean());
        verify(user).setImage(Mockito.any());
        verify(user).setPassword(Mockito.any());
        verify(user).setPlan(Mockito.any());
        verify(user).setUser_id(anyLong());
        verify(user).setUsername(Mockito.any());
        verify(user).setWeights(Mockito.any());
        verify(userRepositry).findByEmail(Mockito.any());
        assertNull(actualUsersigninResult);
    }

    /**
     * Method under test: {@link Service#findByEmailRole(String, String)}
     */
    @Test
    void testFindByEmailRole() {
        assertNull(service.findByEmailRole("jane.doe@example.org", "Role"));
    }

    /**
     * Method under test: {@link Service#findByEmailRole(String, String)}
     */
    @Test
    void testFindByEmailRole2() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        user.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user.setPassword("iloveyou");
        user.setPlan(plan);
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepositry.findByEmail(Mockito.any())).thenReturn(ofResult);
        String actualFindByEmailRoleResult = service.findByEmailRole("jane.doe@example.org", "user");
        verify(userRepositry).findByEmail(Mockito.any());
        assertEquals("janedoe", actualFindByEmailRoleResult);
    }

    /**
     * Method under test:  {@link Service#sendForgetPasswordEmail(ResetPassword)}
     */
    @Test
    void testSendForgetPasswordEmail() {
        when(emailService.sendMemeMail(Mockito.any())).thenReturn("Send Meme Mail");

        ResetPassword resetPassword = new ResetPassword();
        resetPassword.setEmail("jane.doe@example.org");
        resetPassword.setId(1L);
        resetPassword.setToken("ABC123");
        resetPassword.setUsername("janedoe");
        service.sendForgetPasswordEmail(resetPassword);
        verify(emailService).sendMemeMail(Mockito.any());
    }

    /**
     * Method under test: {@link Service#sendForgetPasswordEmail(ResetPassword)}
     */
    @Test
    void testSendForgetPasswordEmail2() {
        ResetPassword resetPassword = mock(ResetPassword.class);
        when(resetPassword.getToken()).thenThrow(new RuntimeException("Please verify your registration"));
        when(resetPassword.getEmail()).thenReturn("jane.doe@example.org");
        when(resetPassword.getUsername()).thenReturn("janedoe");
        doNothing().when(resetPassword).setEmail(Mockito.any());
        doNothing().when(resetPassword).setId(Mockito.<Long>any());
        doNothing().when(resetPassword).setToken(Mockito.any());
        doNothing().when(resetPassword).setUsername(Mockito.any());
        resetPassword.setEmail("jane.doe@example.org");
        resetPassword.setId(1L);
        resetPassword.setToken("ABC123");
        resetPassword.setUsername("janedoe");
        assertThrows(RuntimeException.class, () -> service.sendForgetPasswordEmail(resetPassword));
        verify(resetPassword).getEmail();
        verify(resetPassword).getToken();
        verify(resetPassword).getUsername();
        verify(resetPassword).setEmail(Mockito.any());
        verify(resetPassword).setId(Mockito.<Long>any());
        verify(resetPassword).setToken(Mockito.any());
        verify(resetPassword).setUsername(Mockito.any());
    }

    /**
     * Method under test: {@link Service#create_reset_password(ResetPassword)}
     */
    @Test
    void testCreate_reset_password() {
        ResetPassword resetPassword = new ResetPassword();
        resetPassword.setEmail("jane.doe@example.org");
        resetPassword.setId(1L);
        resetPassword.setToken("ABC123");
        resetPassword.setUsername("janedoe");

        ResetPassword resetPassword2 = new ResetPassword();
        resetPassword2.setEmail("jane.doe@example.org");
        resetPassword2.setId(1L);
        resetPassword2.setToken("ABC123");
        resetPassword2.setUsername("janedoe");
        doNothing().when(resetPasswordRepository).deleteById(Mockito.<Long>any());
        when(resetPasswordRepository.findByEmail(Mockito.any())).thenReturn(resetPassword);
        when(resetPasswordRepository.save(Mockito.any())).thenReturn(resetPassword2);

        ResetPassword resetPassword3 = new ResetPassword();
        resetPassword3.setEmail("jane.doe@example.org");
        resetPassword3.setId(1L);
        resetPassword3.setToken("ABC123");
        resetPassword3.setUsername("janedoe");
        service.create_reset_password(resetPassword3);
        verify(resetPasswordRepository).findByEmail(Mockito.any());
        verify(resetPasswordRepository).deleteById(Mockito.<Long>any());
        verify(resetPasswordRepository).save(Mockito.any());
        assertEquals("ABC123", resetPassword3.getToken());
        assertEquals("jane.doe@example.org", resetPassword3.getEmail());
        assertEquals("janedoe", resetPassword3.getUsername());
        assertEquals(1L, resetPassword3.getId().longValue());
        assertTrue(service.getFoodCalorie().isEmpty());
        assertTrue(service.getallplans().isEmpty());
    }

    /**
     * Method under test: {@link Service#create_reset_password(ResetPassword)}
     */
    @Test
    void testCreate_reset_password2() {
        ResetPassword resetPassword = new ResetPassword();
        resetPassword.setEmail("jane.doe@example.org");
        resetPassword.setId(1L);
        resetPassword.setToken("ABC123");
        resetPassword.setUsername("janedoe");
        doThrow(new RuntimeException("foo")).when(resetPasswordRepository).deleteById(Mockito.<Long>any());
        when(resetPasswordRepository.findByEmail(Mockito.any())).thenReturn(resetPassword);

        ResetPassword resetPassword2 = new ResetPassword();
        resetPassword2.setEmail("jane.doe@example.org");
        resetPassword2.setId(1L);
        resetPassword2.setToken("ABC123");
        resetPassword2.setUsername("janedoe");
        assertThrows(RuntimeException.class, () -> service.create_reset_password(resetPassword2));
        verify(resetPasswordRepository).findByEmail(Mockito.any());
        verify(resetPasswordRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link Service#get_reset_password(String)}
     */
    @Test
    void testGet_reset_password() {
        ResetPassword resetPassword = new ResetPassword();
        resetPassword.setEmail("jane.doe@example.org");
        resetPassword.setId(1L);
        resetPassword.setToken("ABC123");
        resetPassword.setUsername("janedoe");
        when(resetPasswordRepository.findByEmail(Mockito.any())).thenReturn(resetPassword);
        ResetPassword actualGet_reset_passwordResult = service.get_reset_password("jane.doe@example.org");
        verify(resetPasswordRepository).findByEmail(Mockito.any());
        assertSame(resetPassword, actualGet_reset_passwordResult);
    }

    /**
     * Method under test:  {@link Service#get_reset_password(String)}
     */
    @Test
    void testGet_reset_password2() {
        when(resetPasswordRepository.findByEmail(Mockito.any())).thenThrow(new RuntimeException("foo"));
        assertThrows(RuntimeException.class, () -> service.get_reset_password("jane.doe@example.org"));
        verify(resetPasswordRepository).findByEmail(Mockito.any());
    }

    /**
     * Method under test: {@link Service#resetPassword(LoginRequest, String)}
     */
    @Test
    void testResetPassword() {
        LoginRequest loginRequest = new LoginRequest("jane.doe@example.org", "iloveyou");

        service.resetPassword(loginRequest, "Role");
        assertEquals("iloveyou", loginRequest.getPassword());
        assertEquals("jane.doe@example.org", loginRequest.getEmail());
        assertTrue(service.getFoodCalorie().isEmpty());
        assertTrue(service.getallplans().isEmpty());
    }

    /**
     * Method under test: {@link Service#resetPassword(LoginRequest, String)}
     */
    @Test
    void testResetPassword2() {
        service.resetPassword(mock(LoginRequest.class), "Role");
        assertTrue(service.getFoodCalorie().isEmpty());
        assertTrue(service.getallplans().isEmpty());
    }

    /**
     * Method under test: {@link Service#resetPassword(LoginRequest, String)}
     */
    @Test
    void testResetPassword3() throws UnsupportedEncodingException {
        ResetPassword resetPassword = new ResetPassword();
        resetPassword.setEmail("jane.doe@example.org");
        resetPassword.setId(1L);
        resetPassword.setToken("ABC123");
        resetPassword.setUsername("janedoe");
        doNothing().when(resetPasswordRepository).deleteById(Mockito.<Long>any());
        when(resetPasswordRepository.findByEmail(Mockito.any())).thenReturn(resetPassword);

        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        user.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user.setPassword("iloveyou");
        user.setPlan(plan);
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);

        Coach coach3 = new Coach();
        coach3.setAddress("42 Main St");
        coach3.setCoach_id(1L);
        coach3.setContact_number("42");
        coach3.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach3.setDescription("The characteristics of someone or something");
        coach3.setEmail("jane.doe@example.org");
        coach3.setEnabled(true);
        coach3.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach3.setIsapproved(1);
        coach3.setNo_users_subscribed(1);
        coach3.setPassword("iloveyou");
        coach3.setPlans(new ArrayList<>());
        coach3.setPrice("Price");
        coach3.setRating(1);
        coach3.setUsername("janedoe");
        coach3.setUsers(new ArrayList<>());
        coach3.setUsers_reports(new ArrayList<>());

        Coach coach4 = new Coach();
        coach4.setAddress("42 Main St");
        coach4.setCoach_id(1L);
        coach4.setContact_number("42");
        coach4.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach4.setDescription("The characteristics of someone or something");
        coach4.setEmail("jane.doe@example.org");
        coach4.setEnabled(true);
        coach4.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach4.setIsapproved(1);
        coach4.setNo_users_subscribed(1);
        coach4.setPassword("iloveyou");
        coach4.setPlans(new ArrayList<>());
        coach4.setPrice("Price");
        coach4.setRating(1);
        coach4.setUsername("janedoe");
        coach4.setUsers(new ArrayList<>());
        coach4.setUsers_reports(new ArrayList<>());

        Plan plan2 = new Plan();
        plan2.setCoach(coach4);
        plan2.setDescription("The characteristics of someone or something");
        plan2.setGoal("Goal");
        plan2.setPlanName("Plan Name");
        plan2.setUsers(new ArrayList<>());

        User user2 = new User();
        user2.setCoach(coach3);
        user2.setCoaches_reports(new ArrayList<>());
        user2.setComment("Comment");
        user2.setContact_number("42");
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user2.setPassword("iloveyou");
        user2.setPlan(plan2);
        user2.setUser_id(1L);
        user2.setUsername("janedoe");
        user2.setWeights(new ArrayList<>());
        when(userRepositry.save(Mockito.any())).thenReturn(user2);
        when(userRepositry.findByEmail(Mockito.any())).thenReturn(ofResult);
        when(passwordEncoder.encode(Mockito.any())).thenReturn("secret");
        service.resetPassword(new LoginRequest("jane.doe@example.org", "iloveyou"), "user");
        verify(resetPasswordRepository).findByEmail(Mockito.any());
        verify(userRepositry).findByEmail(Mockito.any());
        verify(resetPasswordRepository).deleteById(Mockito.<Long>any());
        verify(userRepositry).save(Mockito.any());
        verify(passwordEncoder).encode(Mockito.any());
    }

    /**
     * Method under test: {@link Service#resetPassword(LoginRequest, String)}
     */
    @Test
    void testResetPassword4() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        user.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user.setPassword("iloveyou");
        user.setPlan(plan);
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepositry.findByEmail(Mockito.any())).thenReturn(ofResult);
        when(passwordEncoder.encode(Mockito.any())).thenThrow(new RuntimeException("user"));
        assertThrows(RuntimeException.class,
                () -> service.resetPassword(new LoginRequest("jane.doe@example.org", "iloveyou"), "user"));
        verify(userRepositry).findByEmail(Mockito.any());
        verify(passwordEncoder).encode(Mockito.any());
    }

    /**
     * Method under test: {@link Service#coachsignin(String, String)}
     */
    @Test
    void testCoachsignin() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        Optional<Coach> ofResult = Optional.of(coach);
        when(coachRepositry.findByEmail(Mockito.any())).thenReturn(ofResult);
        when(passwordEncoder.matches(Mockito.any(), Mockito.any())).thenReturn(true);
        Coach actualCoachsigninResult = service.coachsignin("jane.doe@example.org", "Pass");
        verify(coachRepositry).findByEmail(Mockito.any());
        verify(passwordEncoder).matches(Mockito.any(), Mockito.any());
        assertSame(coach, actualCoachsigninResult);
    }

    /**
     * Method under test: {@link Service#coachsignin(String, String)}
     */
    @Test
    void testCoachsignin2() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        Optional<Coach> ofResult = Optional.of(coach);
        when(coachRepositry.findByEmail(Mockito.any())).thenReturn(ofResult);
        when(passwordEncoder.matches(Mockito.any(), Mockito.any()))
                .thenThrow(new RuntimeException("foo"));
        assertThrows(RuntimeException.class, () -> service.coachsignin("jane.doe@example.org", "Pass"));
        verify(coachRepositry).findByEmail(Mockito.any());
        verify(passwordEncoder).matches(Mockito.any(), Mockito.any());
    }

    /**
     * Method under test: {@link Service#coachsignin(String, String)}
     */
    @Test
    void testCoachsignin3() throws UnsupportedEncodingException {
        Coach coach = mock(Coach.class);
        when(coach.getPassword()).thenThrow(new RuntimeException("foo"));
        when(coach.isEnabled()).thenReturn(true);
        when(coach.getIsapproved()).thenReturn(1);
        doNothing().when(coach).setAddress(Mockito.any());
        doNothing().when(coach).setCoach_id(Mockito.<Long>any());
        doNothing().when(coach).setContact_number(Mockito.any());
        doNothing().when(coach).setCv(Mockito.any());
        doNothing().when(coach).setDescription(Mockito.any());
        doNothing().when(coach).setEmail(Mockito.any());
        doNothing().when(coach).setEnabled(anyBoolean());
        doNothing().when(coach).setImage(Mockito.any());
        doNothing().when(coach).setIsapproved(anyInt());
        doNothing().when(coach).setNo_users_subscribed(anyInt());
        doNothing().when(coach).setPassword(Mockito.any());
        doNothing().when(coach).setPlans(Mockito.any());
        doNothing().when(coach).setPrice(Mockito.any());
        doNothing().when(coach).setRating(anyInt());
        doNothing().when(coach).setUsername(Mockito.any());
        doNothing().when(coach).setUsers(Mockito.any());
        doNothing().when(coach).setUsers_reports(Mockito.any());
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        Optional<Coach> ofResult = Optional.of(coach);
        when(coachRepositry.findByEmail(Mockito.any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class, () -> service.coachsignin("jane.doe@example.org", "Pass"));
        verify(coach).getIsapproved();
        verify(coach).getPassword();
        verify(coach).isEnabled();
        verify(coach).setAddress(Mockito.any());
        verify(coach).setCoach_id(Mockito.<Long>any());
        verify(coach).setContact_number(Mockito.any());
        verify(coach).setCv(Mockito.any());
        verify(coach).setDescription(Mockito.any());
        verify(coach).setEmail(Mockito.any());
        verify(coach).setEnabled(anyBoolean());
        verify(coach).setImage(Mockito.any());
        verify(coach).setIsapproved(anyInt());
        verify(coach).setNo_users_subscribed(anyInt());
        verify(coach).setPassword(Mockito.any());
        verify(coach).setPlans(Mockito.any());
        verify(coach).setPrice(Mockito.any());
        verify(coach).setRating(anyInt());
        verify(coach).setUsername(Mockito.any());
        verify(coach).setUsers(Mockito.any());
        verify(coach).setUsers_reports(Mockito.any());
        verify(coachRepositry).findByEmail(Mockito.any());
    }

    /**
     * Method under test: {@link Service#coachsignin(String, String)}
     */
    @Test
    void testCoachsignin4() throws UnsupportedEncodingException {
        Coach coach = mock(Coach.class);
        when(coach.isEnabled()).thenReturn(false);
        when(coach.getIsapproved()).thenReturn(1);
        doNothing().when(coach).setAddress(Mockito.any());
        doNothing().when(coach).setCoach_id(Mockito.<Long>any());
        doNothing().when(coach).setContact_number(Mockito.any());
        doNothing().when(coach).setCv(Mockito.any());
        doNothing().when(coach).setDescription(Mockito.any());
        doNothing().when(coach).setEmail(Mockito.any());
        doNothing().when(coach).setEnabled(anyBoolean());
        doNothing().when(coach).setImage(Mockito.any());
        doNothing().when(coach).setIsapproved(anyInt());
        doNothing().when(coach).setNo_users_subscribed(anyInt());
        doNothing().when(coach).setPassword(Mockito.any());
        doNothing().when(coach).setPlans(Mockito.any());
        doNothing().when(coach).setPrice(Mockito.any());
        doNothing().when(coach).setRating(anyInt());
        doNothing().when(coach).setUsername(Mockito.any());
        doNothing().when(coach).setUsers(Mockito.any());
        doNothing().when(coach).setUsers_reports(Mockito.any());
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        Optional<Coach> ofResult = Optional.of(coach);
        when(coachRepositry.findByEmail(Mockito.any())).thenReturn(ofResult);
        service.coachsignin("jane.doe@example.org", "Pass");
        verify(coach).getIsapproved();
        verify(coach).isEnabled();
        verify(coach).setAddress(Mockito.any());
        verify(coach).setCoach_id(Mockito.<Long>any());
        verify(coach).setContact_number(Mockito.any());
        verify(coach).setCv(Mockito.any());
        verify(coach).setDescription(Mockito.any());
        verify(coach).setEmail(Mockito.any());
        verify(coach).setEnabled(anyBoolean());
        verify(coach).setImage(Mockito.any());
        verify(coach).setIsapproved(anyInt());
        verify(coach).setNo_users_subscribed(anyInt());
        verify(coach).setPassword(Mockito.any());
        verify(coach).setPlans(Mockito.any());
        verify(coach).setPrice(Mockito.any());
        verify(coach).setRating(anyInt());
        verify(coach).setUsername(Mockito.any());
        verify(coach).setUsers(Mockito.any());
        verify(coach).setUsers_reports(Mockito.any());
        verify(coachRepositry).findByEmail(Mockito.any());
    }

    /**
     * Method under test: {@link Service#coachsignin(String, String)}
     */
    @Test
    void testCoachsignin5() throws UnsupportedEncodingException {
        Coach coach = mock(Coach.class);
        when(coach.getIsapproved()).thenReturn(0);
        doNothing().when(coach).setAddress(Mockito.any());
        doNothing().when(coach).setCoach_id(Mockito.<Long>any());
        doNothing().when(coach).setContact_number(Mockito.any());
        doNothing().when(coach).setCv(Mockito.any());
        doNothing().when(coach).setDescription(Mockito.any());
        doNothing().when(coach).setEmail(Mockito.any());
        doNothing().when(coach).setEnabled(anyBoolean());
        doNothing().when(coach).setImage(Mockito.any());
        doNothing().when(coach).setIsapproved(anyInt());
        doNothing().when(coach).setNo_users_subscribed(anyInt());
        doNothing().when(coach).setPassword(Mockito.any());
        doNothing().when(coach).setPlans(Mockito.any());
        doNothing().when(coach).setPrice(Mockito.any());
        doNothing().when(coach).setRating(anyInt());
        doNothing().when(coach).setUsername(Mockito.any());
        doNothing().when(coach).setUsers(Mockito.any());
        doNothing().when(coach).setUsers_reports(Mockito.any());
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        Optional<Coach> ofResult = Optional.of(coach);
        when(coachRepositry.findByEmail(Mockito.any())).thenReturn(ofResult);
        service.coachsignin("jane.doe@example.org", "Pass");
        verify(coach).getIsapproved();
        verify(coach).setAddress(Mockito.any());
        verify(coach).setCoach_id(Mockito.<Long>any());
        verify(coach).setContact_number(Mockito.any());
        verify(coach).setCv(Mockito.any());
        verify(coach).setDescription(Mockito.any());
        verify(coach).setEmail(Mockito.any());
        verify(coach).setEnabled(anyBoolean());
        verify(coach).setImage(Mockito.any());
        verify(coach).setIsapproved(anyInt());
        verify(coach).setNo_users_subscribed(anyInt());
        verify(coach).setPassword(Mockito.any());
        verify(coach).setPlans(Mockito.any());
        verify(coach).setPrice(Mockito.any());
        verify(coach).setRating(anyInt());
        verify(coach).setUsername(Mockito.any());
        verify(coach).setUsers(Mockito.any());
        verify(coach).setUsers_reports(Mockito.any());
        verify(coachRepositry).findByEmail(Mockito.any());
    }

    /**
     * Method under test: {@link Service#coachsignin(String, String)}
     */
    @Test
    void testCoachsignin6() {
        Optional<Coach> emptyResult = Optional.empty();
        when(coachRepositry.findByEmail(Mockito.any())).thenReturn(emptyResult);
        new RuntimeException("foo");
        Coach actualCoachsigninResult = service.coachsignin("jane.doe@example.org", "Pass");
        verify(coachRepositry).findByEmail(Mockito.any());
        assertNull(actualCoachsigninResult);
    }

    /**
     * Method under test: {@link Service#saveplan(Plan)}
     */
    @Test
    void testSaveplan() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());

        Plan plan = new Plan();
        plan.setCoach(coach);
        plan.setDescription("The characteristics of someone or something");
        plan.setGoal("Goal");
        plan.setPlanName("Plan Name");
        plan.setUsers(new ArrayList<>());
        when(planRepositry.findByPlanName(Mockito.any())).thenReturn(plan);

        Coach coach2 = new Coach();
        coach2.setAddress("42 Main St");
        coach2.setCoach_id(1L);
        coach2.setContact_number("42");
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setIsapproved(1);
        coach2.setNo_users_subscribed(1);
        coach2.setPassword("iloveyou");
        coach2.setPlans(new ArrayList<>());
        coach2.setPrice("Price");
        coach2.setRating(1);
        coach2.setUsername("janedoe");
        coach2.setUsers(new ArrayList<>());
        coach2.setUsers_reports(new ArrayList<>());

        Plan plan2 = new Plan();
        plan2.setCoach(coach2);
        plan2.setDescription("The characteristics of someone or something");
        plan2.setGoal("Goal");
        plan2.setPlanName("Plan Name");
        plan2.setUsers(new ArrayList<>());
        Plan actualSaveplanResult = service.saveplan(plan2);
        verify(planRepositry).findByPlanName(Mockito.any());
        assertNull(actualSaveplanResult);
    }

    /**
     * Method under test: {@link Service#subscribe_to_plan(String, Long)}
     */

    @Test
    void testSubscribe_to_plan2() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        Optional<Coach> ofResult = Optional.of(coach);

        Coach coach2 = new Coach();
        coach2.setAddress("42 Main St");
        coach2.setCoach_id(1L);
        coach2.setContact_number("42");
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setIsapproved(1);
        coach2.setNo_users_subscribed(1);
        coach2.setPassword("iloveyou");
        coach2.setPlans(new ArrayList<>());
        coach2.setPrice("Price");
        coach2.setRating(1);
        coach2.setUsername("janedoe");
        coach2.setUsers(new ArrayList<>());
        coach2.setUsers_reports(new ArrayList<>());
        when(coachRepositry.save(Mockito.any())).thenReturn(coach2);
        when(coachRepositry.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Coach coach3 = new Coach();
        coach3.setAddress("42 Main St");
        coach3.setCoach_id(1L);
        coach3.setContact_number("42");
        coach3.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach3.setDescription("The characteristics of someone or something");
        coach3.setEmail("jane.doe@example.org");
        coach3.setEnabled(true);
        coach3.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach3.setIsapproved(1);
        coach3.setNo_users_subscribed(1);
        coach3.setPassword("iloveyou");
        coach3.setPlans(new ArrayList<>());
        coach3.setPrice("Price");
        coach3.setRating(1);
        coach3.setUsername("janedoe");
        coach3.setUsers(new ArrayList<>());
        coach3.setUsers_reports(new ArrayList<>());

        Plan plan = new Plan();
        plan.setCoach(coach3);
        plan.setDescription("The characteristics of someone or something");
        plan.setGoal("Goal");
        plan.setPlanName("Plan Name");
        plan.setUsers(new ArrayList<>());
        when(planRepositry.findByPlanName(Mockito.any())).thenReturn(plan);

        Coach coach4 = new Coach();
        coach4.setAddress("42 Main St");
        coach4.setCoach_id(1L);
        coach4.setContact_number("42");
        coach4.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach4.setDescription("The characteristics of someone or something");
        coach4.setEmail("jane.doe@example.org");
        coach4.setEnabled(true);
        coach4.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach4.setIsapproved(1);
        coach4.setNo_users_subscribed(1);
        coach4.setPassword("iloveyou");
        coach4.setPlans(new ArrayList<>());
        coach4.setPrice("Price");
        coach4.setRating(1);
        coach4.setUsername("janedoe");
        coach4.setUsers(new ArrayList<>());
        coach4.setUsers_reports(new ArrayList<>());

        Coach coach5 = new Coach();
        coach5.setAddress("42 Main St");
        coach5.setCoach_id(1L);
        coach5.setContact_number("42");
        coach5.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach5.setDescription("The characteristics of someone or something");
        coach5.setEmail("jane.doe@example.org");
        coach5.setEnabled(true);
        coach5.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach5.setIsapproved(1);
        coach5.setNo_users_subscribed(1);
        coach5.setPassword("iloveyou");
        coach5.setPlans(new ArrayList<>());
        coach5.setPrice("Price");
        coach5.setRating(1);
        coach5.setUsername("janedoe");
        coach5.setUsers(new ArrayList<>());
        coach5.setUsers_reports(new ArrayList<>());

        Plan plan2 = new Plan();
        plan2.setCoach(coach5);
        plan2.setDescription("The characteristics of someone or something");
        plan2.setGoal("Goal");
        plan2.setPlanName("Plan Name");
        plan2.setUsers(new ArrayList<>());

        User user = new User();
        user.setCoach(coach4);
        user.setCoaches_reports(new ArrayList<>());
        user.setComment("Comment");
        user.setContact_number("42");
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user.setPassword("iloveyou");
        user.setPlan(plan2);
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());
        Optional<User> ofResult2 = Optional.of(user);
        when(userRepositry.save(Mockito.any())).thenThrow(new RuntimeException("foo"));
        when(userRepositry.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        assertThrows(RuntimeException.class, () -> service.subscribe_to_plan("Plan Name", 1L));
        verify(planRepositry).findByPlanName(Mockito.any());
        verify(coachRepositry).findById(Mockito.<Long>any());
        verify(userRepositry).findById(Mockito.<Long>any());
        verify(coachRepositry).save(Mockito.any());
        verify(userRepositry).save(Mockito.any());
    }


    /**
     * Method under test: {@link Service#get_subscribed_users(Long)}
     */

    @Test
    void testDeleteSubscriptionUserNotFound() {
        Long userId = 1L;
        when(userRepositry.findById(userId)).thenReturn(Optional.empty());
        User result = service.deletesubscription(userId);
        assertNull(result);
        verify(coachRepositry, never()).save(any());
        verify(userRepositry, never()).save(any());
        verify(notificationRepository, never()).save(any());
    }


    @Test
    void testDeleteSubscriptionUserWithCoach() {
        Long userId = 1L;
        User user = new User();
        user.setUser_id(userId);
        Coach coach = new Coach();
        coach.setNo_users_subscribed(3);
        user.setCoach(coach);
        when(userRepositry.findById(userId)).thenReturn(Optional.of(user));
        when(userRepositry.save(any())).thenReturn(user);
        User result = service.deletesubscription(userId);
        assertNotNull(result);
        assertNull(result.getCoach());
        assertNull(result.getPlan());
        assertEquals(2, coach.getNo_users_subscribed());
        verify(coachRepositry, times(1)).save(coach);
        verify(userRepositry, times(1)).save(user);
        verify(notificationRepository, times(1)).save(any());
    }
    @Test
    void testGet_subscribed_users()  {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        ArrayList<User> users = new ArrayList<>();
        coach.setUsers(users);
        coach.setUsers_reports(new ArrayList<>());
        Optional<Coach> ofResult = Optional.of(coach);
        when(coachRepositry.findById(Mockito.<Long>any())).thenReturn(ofResult);
        List<User> actualGet_subscribed_usersResult = service.get_subscribed_users(1L);
        verify(coachRepositry).findById(Mockito.<Long>any());
        assertTrue(actualGet_subscribed_usersResult.isEmpty());
        assertSame(users, actualGet_subscribed_usersResult);
    }

    /**
     * Method under test: {@link Service#get_subscribed_users(Long)}
     */
    @Test
    void testGet_subscribed_users2() {
        Optional<Coach> emptyResult = Optional.empty();
        when(coachRepositry.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        List<User> actualGet_subscribed_usersResult = service.get_subscribed_users(1L);
        verify(coachRepositry).findById(Mockito.<Long>any());
        assertNull(actualGet_subscribed_usersResult);
    }

    /**
     * Method under test:  {@link Service#get_subscribed_users(Long)}
     */
    @Test
    void testGet_subscribed_users3() {
        when(coachRepositry.findById(Mockito.<Long>any())).thenThrow(new RuntimeException("foo"));
        assertThrows(RuntimeException.class, () -> service.get_subscribed_users(1L));
        verify(coachRepositry).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link Service#update_comment(String, Long)}
     */
    @Test
    void testUpdate_comment() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        user.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user.setPassword("iloveyou");
        user.setPlan(plan);
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);

        Coach coach3 = new Coach();
        coach3.setAddress("42 Main St");
        coach3.setCoach_id(1L);
        coach3.setContact_number("42");
        coach3.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach3.setDescription("The characteristics of someone or something");
        coach3.setEmail("jane.doe@example.org");
        coach3.setEnabled(true);
        coach3.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach3.setIsapproved(1);
        coach3.setNo_users_subscribed(1);
        coach3.setPassword("iloveyou");
        coach3.setPlans(new ArrayList<>());
        coach3.setPrice("Price");
        coach3.setRating(1);
        coach3.setUsername("janedoe");
        coach3.setUsers(new ArrayList<>());
        coach3.setUsers_reports(new ArrayList<>());

        Coach coach4 = new Coach();
        coach4.setAddress("42 Main St");
        coach4.setCoach_id(1L);
        coach4.setContact_number("42");
        coach4.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach4.setDescription("The characteristics of someone or something");
        coach4.setEmail("jane.doe@example.org");
        coach4.setEnabled(true);
        coach4.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach4.setIsapproved(1);
        coach4.setNo_users_subscribed(1);
        coach4.setPassword("iloveyou");
        coach4.setPlans(new ArrayList<>());
        coach4.setPrice("Price");
        coach4.setRating(1);
        coach4.setUsername("janedoe");
        coach4.setUsers(new ArrayList<>());
        coach4.setUsers_reports(new ArrayList<>());

        Plan plan2 = new Plan();
        plan2.setCoach(coach4);
        plan2.setDescription("The characteristics of someone or something");
        plan2.setGoal("Goal");
        plan2.setPlanName("Plan Name");
        plan2.setUsers(new ArrayList<>());

        User user2 = new User();
        user2.setCoach(coach3);
        user2.setCoaches_reports(new ArrayList<>());
        user2.setComment("Comment");
        user2.setContact_number("42");
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user2.setPassword("iloveyou");
        user2.setPlan(plan2);
        user2.setUser_id(1L);
        user2.setUsername("janedoe");
        user2.setWeights(new ArrayList<>());
        when(userRepositry.save(Mockito.any())).thenReturn(user2);
        when(userRepositry.findById(Mockito.<Long>any())).thenReturn(ofResult);
        User actualUpdate_commentResult = service.update_comment("Comment", 1L);
        verify(userRepositry).findById(Mockito.<Long>any());
        verify(userRepositry).save(Mockito.any());
        assertSame(user2, actualUpdate_commentResult);
    }

    /**
     * Method under test: {@link Service#update_comment(String, Long)}
     */
    @Test
    void testUpdate_comment2() throws UnsupportedEncodingException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
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
        user.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        user.setPassword("iloveyou");
        user.setPlan(plan);
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepositry.save(Mockito.any())).thenThrow(new RuntimeException("foo"));
        when(userRepositry.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class, () -> service.update_comment("Comment", 1L));
        verify(userRepositry).findById(Mockito.<Long>any());
        verify(userRepositry).save(Mockito.any());
    }

    /**
     * Method under test: {@link Service#addImageToCoach(String, MultipartFile)}
     */
    @Test
    void testAddImageToCoach() throws IOException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        Optional<Coach> ofResult = Optional.of(coach);

        Coach coach2 = new Coach();
        coach2.setAddress("42 Main St");
        coach2.setCoach_id(1L);
        coach2.setContact_number("42");
        coach2.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setDescription("The characteristics of someone or something");
        coach2.setEmail("jane.doe@example.org");
        coach2.setEnabled(true);
        coach2.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach2.setIsapproved(1);
        coach2.setNo_users_subscribed(1);
        coach2.setPassword("iloveyou");
        coach2.setPlans(new ArrayList<>());
        coach2.setPrice("Price");
        coach2.setRating(1);
        coach2.setUsername("janedoe");
        coach2.setUsers(new ArrayList<>());
        coach2.setUsers_reports(new ArrayList<>());
        when(coachRepositry.save(Mockito.any())).thenReturn(coach2);
        when(coachRepositry.findByEmail(Mockito.any())).thenReturn(ofResult);
        Coach actualAddImageToCoachResult = service.addImageToCoach("jane.doe@example.org",
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes(StandardCharsets.UTF_8))));
        verify(coachRepositry).findByEmail(Mockito.any());
        verify(coachRepositry).save(Mockito.any());
        assertSame(coach, actualAddImageToCoachResult);
        byte[] expectedImage = "AXAXAXAX".getBytes(StandardCharsets.UTF_8);
        assertArrayEquals(expectedImage, actualAddImageToCoachResult.getImage());
    }

    /**
     * Method under test: {@link Service#addImageToCoach(String, MultipartFile)}
     */
    @Test
    void testAddImageToCoach2() throws IOException {
        Coach coach = new Coach();
        coach.setAddress("42 Main St");
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setEnabled(true);
        coach.setImage("AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        Optional<Coach> ofResult = Optional.of(coach);
        when(coachRepositry.save(Mockito.any())).thenThrow(new RuntimeException("foo"));
        when(coachRepositry.findByEmail(Mockito.any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class, () -> service.addImageToCoach("jane.doe@example.org",
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes(StandardCharsets.UTF_8)))));
        verify(coachRepositry).findByEmail(Mockito.any());
        verify(coachRepositry).save(Mockito.any());
    }

    /**
     * Method under test: {@link Service#getFoodCalorie()}
     */
    @Test
    void testGetFoodCalorie() {
        ArrayList<FoodCalorie> foodCalorieList = new ArrayList<>();
        when(foodCalorieRepositry.findAll()).thenReturn(foodCalorieList);
        List<FoodCalorie> actualFoodCalorie = service.getFoodCalorie();
        verify(foodCalorieRepositry).findAll();
        assertTrue(actualFoodCalorie.isEmpty());
        assertSame(foodCalorieList, actualFoodCalorie);
    }

    /**
     * Method under test:  {@link Service#getFoodCalorie()}
     */
    @Test
    void testGetFoodCalorie2() {
        when(foodCalorieRepositry.findAll()).thenThrow(new RuntimeException("foo"));
        assertThrows(RuntimeException.class, () -> service.getFoodCalorie());
        verify(foodCalorieRepositry).findAll();
    }


    @Test
    void savechat() {
        User user = new User();
        user.setUser_id(1L);
        user.setUsername("username");
        user.setPassword("password");
        user.setPlan(new Plan());
        user.setCoaches_reports(new ArrayList<>());
        user.setWeights(new ArrayList<>());
        user.setEnabled(true);
        user.setEmail("kk@gmail.com");
        Coach coach = new Coach();
        coach.setCoach_id(2L);
        coach.setUsername("username");
        coach.setPassword("password");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        coach.setPlans(new ArrayList<>());
        coach.setNo_users_subscribed(0);
        coach.setIsapproved(1);
        Chat chat = new Chat();
        chat.setUser(1L);
        chat.setCoach(2L);
        chat.setMessage("message");
        ChatDto chatDto = new ChatDto();
        chatDto.setUser_id(1L);
        chatDto.setCoach_id(2L);
        chatDto.setMessage("message");

        //happy case
   when(userRepositry.findById(1L)).thenReturn(Optional.of(user));
   when(coachRepositry.findById(2L)).thenReturn(Optional.of(coach));
   when(chatRepository.save(chat)).thenReturn(chat);
    Chat actual = service.savechat(chatDto);
    chat.setLocalDateTime(actual.getLocalDateTime());
    assertEquals(chat,actual);

    //user not found
    when(userRepositry.findById(1L)).thenReturn(Optional.empty());
    assertNull(service.savechat(chatDto));
   // coach not found
    when(userRepositry.findById(1L)).thenReturn(Optional.of(user));
    when(coachRepositry.findById(2L)).thenReturn(Optional.empty());
    assertNull(service.savechat(chatDto));
    // coach and user not found
    when(userRepositry.findById(1L)).thenReturn(Optional.empty());
    when(coachRepositry.findById(2L)).thenReturn(Optional.empty());
    assertNull(service.savechat(chatDto));

    }

    @Test
    void getUserChats() {
        User user = new User();
        user.setUser_id(1L);
        user.setUsername("username");
        user.setPassword("password");
        user.setPlan(new Plan());
        user.setCoaches_reports(new ArrayList<>());
        user.setWeights(new ArrayList<>());
        user.setEnabled(true);
        user.setEmail("coach@gmail.com");
        //test the oredering of the chats
        Chat chat = new Chat();
        chat.setUser(1L);
        chat.setCoach(2L);
        chat.setMessage("message");
        chat.setLocalDateTime(LocalDateTime.now());
        Chat chat2 = new Chat();
        chat2.setUser(1L);
        chat2.setCoach(2L);
        chat2.setMessage("message");
        chat2.setLocalDateTime(LocalDateTime.now());
        Chat chat3 = new Chat();
        chat3.setUser(1L);
        chat3.setCoach(2L);
        chat3.setMessage("message");
        chat3.setLocalDateTime(LocalDateTime.now());
        List<Chat> chats = new ArrayList<>();
        chats.add(chat);
        chats.add(chat2);
        chats.add(chat3);
        when(userRepositry.findById(1L)).thenReturn(Optional.of(user));
        when(chatRepository.findByUserOrderByLocalDateTimeAsc(1L)).thenReturn(chats);
        List<Chat> actual = service.getUserChats(1L);
        assertEquals(chats,actual);
        //user not found
        when(userRepositry.findById(1L)).thenReturn(Optional.empty());
        assertNull(service.getUserChats(1L));


    }

    @Test
    void getCoachChats() {
        Coach coach = new Coach();
        coach.setCoach_id(1L);
        Chat chat = new Chat();
        chat.setUser(2L);
        chat.setCoach(1L);
        chat.setMessage("message");
        chat.setLocalDateTime(LocalDateTime.now());
        Chat chat2 = new Chat();
        chat2.setUser(2L);
        chat2.setCoach(1L);
        chat2.setMessage("message");
        chat2.setLocalDateTime(LocalDateTime.now());
        Chat chat3 = new Chat();
        chat3.setUser(2L);
        chat3.setCoach(1L);
        chat3.setMessage("message");
        chat3.setLocalDateTime(LocalDateTime.now());
        List<Chat> chats = new ArrayList<>();
        chats.add(chat);
        chats.add(chat2);
        chats.add(chat3);
        when(coachRepositry.findById(1L)).thenReturn(Optional.of(coach));
        when(chatRepository.findByCoachOrderByLocalDateTimeAsc(1L)).thenReturn(chats);
        List<Chat> actual = service.getCoachChats(1L);
        assertEquals(chats,actual);
        //coach not found
        when(coachRepositry.findById(1L)).thenReturn(Optional.empty());
        assertNull(service.getCoachChats(1L));
    }


    /**
     * Method under test:  {@link Service#changePassword(String, String, String, String)}
     */
    @Test
    void testChangePasswordForUserSuccess() {

        String email = "user@example.com";
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        String role = "user";

        User user = new User();
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder(10).encode(oldPassword));
        when(passwordEncoder.matches(oldPassword, user.getPassword())).thenReturn( new BCryptPasswordEncoder(10).matches(oldPassword, user.getPassword()));
        when(userRepositry.findByEmail(email)).thenReturn(Optional.of(user));


        String result = service.changePassword(email, oldPassword, newPassword, role);


        assertEquals("success", result);
        verify(userRepositry, times(1)).findByEmail(email);
        verify(userRepositry, times(1)).save(any(User.class));
    }
    /**
     * Method under test:  {@link Service#changePassword(String, String, String, String)}
     */
    @Test
    void testChangePasswordForUserNotFound() {

        String email = "nonexistent@example.com";
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        String role = "user";

        when(userRepositry.findByEmail(email)).thenReturn(Optional.empty());

        String result = service.changePassword(email, oldPassword, newPassword, role);
        assertEquals("Email is not valid!", result);
        verify(userRepositry, times(1)).findByEmail(email);
        verify(userRepositry, never()).save(any(User.class));
    }

    /**
     * Method under test:  {@link Service#changePassword(String, String, String, String)}
     */
    @Test
    void testChangePasswordForUserIncorrectOldPassword() {

        String email = "user@example.com";
        String oldPassword = "incorrectOldPassword";
        String newPassword = "newPassword";
        String role = "user";

        User user = new User();
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder(10).encode("oldPassword"));

        when(userRepositry.findByEmail(email)).thenReturn(Optional.of(user));

        String result = service.changePassword(email, oldPassword, newPassword, role);
        assertEquals("Old Password is not valid!", result);
        verify(userRepositry, times(1)).findByEmail(email);
        verify(userRepositry, never()).save(any(User.class));
    }
    /**
     * Method under test:  {@link Service#changePassword(String, String, String, String)}
     */

    @Test
    void testChangePasswordForCoachSuccess() {

        String email = "coach@example.com";
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        String role = "coach";

        Coach coach = new Coach();
        coach.setEmail(email);
        coach.setPassword(new BCryptPasswordEncoder(10).encode(oldPassword));

        when(passwordEncoder.matches(oldPassword, coach.getPassword())).thenReturn( new BCryptPasswordEncoder(10).matches(oldPassword, coach.getPassword()));
        when(coachRepositry.findByEmail(email)).thenReturn(Optional.of(coach));

        String result = service.changePassword(email, oldPassword, newPassword, role);

        assertEquals("success", result);
        verify(coachRepositry, times(1)).findByEmail(email);
        verify(coachRepositry, times(1)).save(any(Coach.class));
    }

    /**
     * Method under test:  {@link Service#changePassword(String, String, String, String)}
     */
    @Test
    void testChangePasswordForCoachNotFound() {

        String email = "nonexistentcoach@example.com";
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        String role = "coach";

        when(coachRepositry.findByEmail(email)).thenReturn(Optional.empty());

        String result = service.changePassword(email, oldPassword, newPassword, role);
        assertEquals("Email is not valid!", result);
        verify(coachRepositry, times(1)).findByEmail(email);
        verify(coachRepositry, never()).save(any(Coach.class));
    }

    /**
     * Method under test:  {@link Service#changePassword(String, String, String, String)}
     */
    @Test
    void testChangePasswordInvalidRole() {
        String email = "user@example.com";
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        String role = "invalidRole";

        String result = service.changePassword(email, oldPassword, newPassword, role);
        assertEquals("Email is not valid!", result);
        verify(userRepositry, never()).findByEmail(anyString());
        verify(userRepositry, never()).save(any(User.class));
        verify(coachRepositry, never()).findByEmail(anyString());
        verify(coachRepositry, never()).save(any(Coach.class));
    }

    @Test
    void getUnseenChats() {
        User user = new User();
        user.setUser_id(1L);
        Coach coach = new Coach();
        coach.setCoach_id(2L);
        Chat chat1 = new Chat();
        chat1.setUser(1L);
        chat1.setCoach(2L);
        chat1.setMessage("message");
        chat1.setLocalDateTime(LocalDateTime.now());
        chat1.setSeen(1);
        Chat chat2 = new Chat();
        chat2.setUser(1L);
        chat2.setCoach(2L);
        chat2.setMessage("message");
        chat2.setLocalDateTime(LocalDateTime.now());
        chat2.setSeen(1);
        Chat chat3 = new Chat();
        chat3.setUser(1L);
        chat3.setCoach(2L);
        chat3.setMessage("message");
        chat3.setLocalDateTime(LocalDateTime.now());
        chat3.setSeen(1);
        Chat chat4 = new Chat();
        chat4.setUser(1L);
        chat4.setCoach(2L);
        chat4.setMessage("message");
        chat4.setLocalDateTime(LocalDateTime.now());
        chat4.setSeen(0);
        Chat chat5 = new Chat();
        chat5.setUser(1L);
        chat5.setCoach(2L);
        chat5.setMessage("message");
        chat5.setLocalDateTime(LocalDateTime.now());
        chat5.setSeen(0);
        List<Chat> chats = new ArrayList<>();
        chats.add(chat1);
        chats.add(chat2);
        chats.add(chat3);
        chats.add(chat4);
        chats.add(chat5);
        when(userRepositry.findById(1L)).thenReturn(Optional.of(user));
        when(coachRepositry.findById(2L)).thenReturn(Optional.of(coach));
        when(chatRepository.findByUserAndCoachOrderByLocalDateTimeAsc(1L,2L)).thenReturn(chats);
        int actual = service.getUnseenChats(1L,2L);
        assertEquals(2,actual);
        //all seen
        chat4.setSeen(1);
        chat5.setSeen(1);
        List<Chat> chats1 = new ArrayList<>();
        chats1.add(chat1);
        chats1.add(chat2);
        chats1.add(chat3);
        chats1.add(chat4);
        chats1.add(chat5);
        when(chatRepository.findByUserAndCoachOrderByLocalDateTimeAsc(1L,2L)).thenReturn(chats1);
        actual = service.getUnseenChats(1L,2L);
        assertEquals(0,actual);
        // user not found
        when(userRepositry.findById(1L)).thenReturn(Optional.empty());
        actual = service.getUnseenChats(1L,2L);
        assertEquals(-1,actual);
        // coach not found
        when(userRepositry.findById(1L)).thenReturn(Optional.of(user));
        when(coachRepositry.findById(2L)).thenReturn(Optional.empty());
        actual = service.getUnseenChats(1L,2L);
        assertEquals(-1,actual);
        // user and coach not found
        when(userRepositry.findById(1L)).thenReturn(Optional.empty());
        when(coachRepositry.findById(2L)).thenReturn(Optional.empty());
        actual = service.getUnseenChats(1L,2L);
        assertEquals(-1,actual);
    }

    @Test
    void getNotificationsCoachRoleReturnsNotifications() {
        Long coachId = 1L;
        List<Notification> expectedNotifications = Collections.singletonList(new Notification());
        Coach coach = new Coach();
        coach.setCoach_id(coachId);
        when(coachRepositry.findById(coachId)).thenReturn(java.util.Optional.of(coach));
        when(notificationRepository.findByCoach_Coach_id(coachId)).thenReturn(expectedNotifications);
        List<NotificationDto> result = service.getNotifications(coachId, "coach");
        assertEquals(expectedNotifications.size(), result.size());
        verify(coachRepositry, times(1)).findById(coachId);
        verify(notificationRepository, times(1)).findByCoach_Coach_id(coachId);
    }
    @Test
    void getNotificationsUserRoleReturnsNotifications() {
        Long userId = 1L;
        List<Notification> expectedNotifications = Collections.singletonList(new Notification());
        User user = new User();
        user.setUser_id(userId);
        when(userRepositry.findById(userId)).thenReturn(java.util.Optional.of(user));
        when(notificationRepository.findByUser_User_id(userId)).thenReturn(expectedNotifications);
        List<NotificationDto> result = service.getNotifications(userId, "user");
        assertEquals(expectedNotifications.size(), result.size());
        verify(userRepositry, times(1)).findById(userId);
        verify(notificationRepository, times(1)).findByUser_User_id(userId);
    }

    @Test
    void getNotificationsInvalidRoleThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.getNotifications(1L, "invalid_role");
        });
    }



    @Test
    void deleteNotificationNotificationExistsDeletesNotification() {
        Long notificationId = 1L;
        Notification notification = new Notification();
        notification.setNotification_id(notificationId);
        when(notificationRepository.findById(notificationId)).thenReturn(Optional.of(notification));
        String result = service.deleteNotification(notificationId);
        assertEquals("Notification deleted", result);
        verify(notificationRepository, times(1)).deleteById(notificationId);
    }

    @Test
    void deleteNotificationNotificationNotFoundReturnsErrorMessage() {
        Long notificationId = 1L;
        doThrow(new IllegalArgumentException("Notification not found")).when(notificationRepository)
                .deleteById(notificationId);
        String result = service.deleteNotification(notificationId);
        assertEquals("Notification not found", result);
    }

    @Test
    void addNotification_Coach_AddsNotification() {
        Coach coach = new Coach();
        String message = "Test message";
        int type = 1;
        service.addNotification(message, type, coach);
        verify(notificationRepository, times(1)).save(any(Notification.class));
    }

    @Test
    void addNotificationUserAddsNotification() {
        User user = new User();
        String message = "Test message";
        int type = 1;
        service.addNotification(message, type, user);
        verify(notificationRepository, times(1)).save(any(Notification.class));
    }
    @Test
    void addNotificationInvalidObjectTypeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addNotification("Test message", 1, new Object());
        });
    }


}
