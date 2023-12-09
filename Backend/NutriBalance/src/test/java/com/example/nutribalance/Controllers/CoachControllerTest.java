package com.example.nutribalance.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import com.example.nutribalance.Entities.Coach;
import com.example.nutribalance.Entities.Plan;
import com.example.nutribalance.Entities.User;
import com.example.nutribalance.Mails.EmailService;
import com.example.nutribalance.Services.Iservice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {CoachController.class})
@ExtendWith(SpringExtension.class)
class CoachControllerTest {
    @Autowired
    private CoachController coachController;

    @MockBean
    private EmailService emailService;

    @MockBean
    private Iservice iservice;

    /**
     * Method under test: {@link CoachController#savecoach(MultipartFile, String)}
     */


    /**
     * Method under test:  {@link CoachController#get_waiting_coaches()}
     */
    @Test
    void testGet_waiting_coaches() throws Exception {
        when(iservice.get_waiting_coaches()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/coach/get_waiting_coaches");
        MockMvcBuilders.standaloneSetup(coachController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CoachController#get_waiting_coaches()}
     */
    @Test
    void testGet_waiting_coaches2() throws Exception {
        Coach coach = new Coach();
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes("UTF-8"));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());

        ArrayList<Coach> coachList = new ArrayList<>();
        coachList.add(coach);
        when(iservice.get_waiting_coaches()).thenReturn(coachList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/coach/get_waiting_coaches");
        MockMvcBuilders.standaloneSetup(coachController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"coach_id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"contact"
                                        + "_number\":\"42\",\"description\":\"The characteristics of someone or something\",\"cv\":\"QVhBWEFYQVg=\",\"rating"
                                        + "\":1,\"price\":\"Price\",\"no_users_subscribed\":1,\"isapproved\":1,\"users\":[],\"users_reports\":[],\"plans\":[]}"
                                        + "]"));
    }

    /**
     * Method under test: {@link CoachController#get_waiting_coaches()}
     */
    @Test
    void testGet_waiting_coaches3() throws Exception {
        Coach coach = new Coach();
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes("UTF-8"));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
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
        coach2.setCoach_id(2L);
        coach2.setContact_number("Contact number");
        coach2.setCv("AXAXAXAX".getBytes("UTF-8"));
        coach2.setDescription("Description");
        coach2.setEmail("john.smith@example.org");
        coach2.setIsapproved(0);
        coach2.setNo_users_subscribed(0);
        coach2.setPassword("Password");
        coach2.setPlans(new ArrayList<>());
        coach2.setPrice("com.example.nutribalance.Entities.Coach");
        coach2.setRating(0);
        coach2.setUsername("Username");
        coach2.setUsers(new ArrayList<>());
        coach2.setUsers_reports(new ArrayList<>());

        ArrayList<Coach> coachList = new ArrayList<>();
        coachList.add(coach2);
        coachList.add(coach);
        when(iservice.get_waiting_coaches()).thenReturn(coachList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/coach/get_waiting_coaches");
        MockMvcBuilders.standaloneSetup(coachController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"coach_id\":2,\"username\":\"Username\",\"email\":\"john.smith@example.org\",\"password\":\"Password\",\"contact"
                                        + "_number\":\"Contact number\",\"description\":\"Description\",\"cv\":\"QVhBWEFYQVg=\",\"rating\":0,\"price\":\"com"
                                        + ".example.nutribalance.Entities.Coach\",\"no_users_subscribed\":0,\"isapproved\":0,\"users\":[],\"users_reports"
                                        + "\":[],\"plans\":[]},{\"coach_id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou"
                                        + "\",\"contact_number\":\"42\",\"description\":\"The characteristics of someone or something\",\"cv\":\"QVhBWEFYQVg"
                                        + "=\",\"rating\":1,\"price\":\"Price\",\"no_users_subscribed\":1,\"isapproved\":1,\"users\":[],\"users_reports\":[],"
                                        + "\"plans\":[]}]"));
    }

    /**
     * Method under test: {@link CoachController#get_waiting_coaches()}
     */
    @Test
    void testGet_waiting_coaches4() throws Exception {
        ArrayList<Plan> plans = new ArrayList<>();
        plans.add(new Plan());

        Coach coach = new Coach();
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes("UTF-8"));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(plans);
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());

        ArrayList<Coach> coachList = new ArrayList<>();
        coachList.add(coach);
        when(iservice.get_waiting_coaches()).thenReturn(coachList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/coach/get_waiting_coaches");
        MockMvcBuilders.standaloneSetup(coachController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"coach_id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"contact"
                                        + "_number\":\"42\",\"description\":\"The characteristics of someone or something\",\"cv\":\"QVhBWEFYQVg=\","
                                        + "\"rating\":1,\"price\":\"Price\",\"no_users_subscribed\":1,\"isapproved\":1,\"users\":[],\"users_reports\":[],"
                                        + "\"plans\":[{}]}]"));
    }

    /**
     * Method under test: {@link CoachController#get_waiting_coaches()}
     */
    @Test
    void testGet_waiting_coaches5() throws Exception {
        User user = new User();
        user.setCoaches(new ArrayList<>());
        user.setCoaches_reports(new ArrayList<>());
        user.setContact_number("42");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setPlan(new Plan());
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());

        ArrayList<User> users = new ArrayList<>();
        users.add(user);

        Coach coach = new Coach();
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes("UTF-8"));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(users);
        coach.setUsers_reports(new ArrayList<>());

        ArrayList<Coach> coachList = new ArrayList<>();
        coachList.add(coach);
        when(iservice.get_waiting_coaches()).thenReturn(coachList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/coach/get_waiting_coaches");
        MockMvcBuilders.standaloneSetup(coachController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"coach_id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"contact"
                                        + "_number\":\"42\",\"description\":\"The characteristics of someone or something\",\"cv\":\"QVhBWEFYQVg=\",\"rating"
                                        + "\":1,\"price\":\"Price\",\"no_users_subscribed\":1,\"isapproved\":1,\"users\":[{\"user_id\":1,\"username\":\"janedoe"
                                        + "\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"contact_number\":\"42\",\"weights\":[],\"plan\":{},"
                                        + "\"coaches_reports\":[]}],\"users_reports\":[],\"plans\":[]}]"));
    }

    /**
     * Method under test:  {@link CoachController#deletecoach(Long)}
     */
    @Test
    void testDeletecoach() throws Exception {
        when(iservice.deletecoach(Mockito.<Long>any())).thenReturn("Deletecoach");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/coach/delete/{id}", 1L);
        MockMvcBuilders.standaloneSetup(coachController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Deletecoach"));
    }

    /**
     * Method under test: {@link CoachController#approvecoach(Long)}
     */
    @Test
    void testApprovecoach() throws Exception {
        Coach coach = new Coach();
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes("UTF-8"));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        when(iservice.approvecoach(Mockito.<Long>any())).thenReturn(coach);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/coach/approve/{id}", 1L);
        MockMvcBuilders.standaloneSetup(coachController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"coach_id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"contact_number"
                                        + "\":\"42\",\"description\":\"The characteristics of someone or something\",\"cv\":\"QVhBWEFYQVg=\",\"rating\":1,"
                                        + "\"price\":\"Price\",\"no_users_subscribed\":1,\"isapproved\":1,\"users\":[],\"users_reports\":[],\"plans\":[]}"));
    }

    /**
     * Method under test: {@link CoachController#approvecoach(Long)}
     */
    @Test
    void testApprovecoach2() throws Exception {
        ArrayList<Plan> plans = new ArrayList<>();
        plans.add(new Plan());

        Coach coach = new Coach();
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes("UTF-8"));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(plans);
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        when(iservice.approvecoach(Mockito.<Long>any())).thenReturn(coach);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/coach/approve/{id}", 1L);
        MockMvcBuilders.standaloneSetup(coachController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"coach_id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"contact_number"
                                        + "\":\"42\",\"description\":\"The characteristics of someone or something\",\"cv\":\"QVhBWEFYQVg=\",\"rating\":1,"
                                        + "\"price\":\"Price\",\"no_users_subscribed\":1,\"isapproved\":1,\"users\":[],\"users_reports\":[],\"plans\":[{}]}"));
    }

    /**
     * Method under test: {@link CoachController#approvecoach(Long)}
     */
    @Test
    void testApprovecoach3() throws Exception {
        User user = new User();
        user.setCoaches(new ArrayList<>());
        user.setCoaches_reports(new ArrayList<>());
        user.setContact_number("42");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setPlan(new Plan());
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());

        ArrayList<User> users = new ArrayList<>();
        users.add(user);

        Coach coach = new Coach();
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes("UTF-8"));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(users);
        coach.setUsers_reports(new ArrayList<>());
        when(iservice.approvecoach(Mockito.<Long>any())).thenReturn(coach);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/coach/approve/{id}", 1L);
        MockMvcBuilders.standaloneSetup(coachController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"coach_id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"contact_number"
                                        + "\":\"42\",\"description\":\"The characteristics of someone or something\",\"cv\":\"QVhBWEFYQVg=\",\"rating\":1,"
                                        + "\"price\":\"Price\",\"no_users_subscribed\":1,\"isapproved\":1,\"users\":[{\"user_id\":1,\"username\":\"janedoe\","
                                        + "\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"contact_number\":\"42\",\"weights\":[],\"plan\":{},"
                                        + "\"coaches_reports\":[]}],\"users_reports\":[],\"plans\":[]}"));
    }

    /**
     * Method under test: {@link CoachController#coachsignin(String, String)}
     */
    @Test
    void testCoachsignin() throws Exception {
        Coach coach = new Coach();
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes("UTF-8"));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        when(iservice.coachsignin(Mockito.<String>any(), Mockito.<String>any())).thenReturn(coach);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/coach/checksignin/{email}/42", "jane.doe@example.org")
                .param("password", "foo");
        MockMvcBuilders.standaloneSetup(coachController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"coach_id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"contact_number"
                                        + "\":\"42\",\"description\":\"The characteristics of someone or something\",\"cv\":\"QVhBWEFYQVg=\",\"rating\":1,"
                                        + "\"price\":\"Price\",\"no_users_subscribed\":1,\"isapproved\":1,\"users\":[],\"users_reports\":[],\"plans\":[]}"));
    }

    /**
     * Method under test: {@link CoachController#coachsignin(String, String)}
     */
    @Test
    void testCoachsignin2() throws Exception {
        ArrayList<Plan> plans = new ArrayList<>();
        plans.add(new Plan());

        Coach coach = new Coach();
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes("UTF-8"));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(plans);
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(new ArrayList<>());
        coach.setUsers_reports(new ArrayList<>());
        when(iservice.coachsignin(Mockito.<String>any(), Mockito.<String>any())).thenReturn(coach);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/coach/checksignin/{email}/42", "jane.doe@example.org")
                .param("password", "foo");
        MockMvcBuilders.standaloneSetup(coachController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"coach_id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"contact_number"
                                        + "\":\"42\",\"description\":\"The characteristics of someone or something\",\"cv\":\"QVhBWEFYQVg=\",\"rating\":1,"
                                        + "\"price\":\"Price\",\"no_users_subscribed\":1,\"isapproved\":1,\"users\":[],\"users_reports\":[],\"plans\":[{}]}"));
    }

    /**
     * Method under test: {@link CoachController#coachsignin(String, String)}
     */
    @Test
    void testCoachsignin3() throws Exception {
        User user = new User();
        user.setCoaches(new ArrayList<>());
        user.setCoaches_reports(new ArrayList<>());
        user.setContact_number("42");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setPlan(new Plan());
        user.setUser_id(1L);
        user.setUsername("janedoe");
        user.setWeights(new ArrayList<>());

        ArrayList<User> users = new ArrayList<>();
        users.add(user);

        Coach coach = new Coach();
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes("UTF-8"));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setUsers(users);
        coach.setUsers_reports(new ArrayList<>());
        when(iservice.coachsignin(Mockito.<String>any(), Mockito.<String>any())).thenReturn(coach);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/coach/checksignin/{email}/42", "jane.doe@example.org")
                .param("password", "foo");
        MockMvcBuilders.standaloneSetup(coachController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"coach_id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"contact_number"
                                        + "\":\"42\",\"description\":\"The characteristics of someone or something\",\"cv\":\"QVhBWEFYQVg=\",\"rating\":1,"
                                        + "\"price\":\"Price\",\"no_users_subscribed\":1,\"isapproved\":1,\"users\":[{\"user_id\":1,\"username\":\"janedoe\","
                                        + "\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"contact_number\":\"42\",\"weights\":[],\"plan\":{},"
                                        + "\"coaches_reports\":[]}],\"users_reports\":[],\"plans\":[]}"));
    }
    /**
     * Method under test: {@link CoachController#addImageToCoach(String, MultipartFile)}}
     */
    @Test
    void testAddImageToCoach() throws Exception {
        Coach coach = new Coach();
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes("UTF-8"));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPlans(new ArrayList<>());
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setImage("Image".getBytes("UTF-8"));
        when(iservice.addImageToCoach(Mockito.<String>any(), Mockito.<MultipartFile>any())).thenReturn(coach);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .multipart("/coach/addImageToCoach/{Email}", "jane.doe@example.org")
                .param("file", "foo");
        MockMvcBuilders.standaloneSetup(coachController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(400));
    }
    @Test
    void testAddImageToCoach2() throws Exception {
        Coach coach = new Coach();
        coach.setCoach_id(1L);
        coach.setContact_number("42");
        coach.setCv("AXAXAXAX".getBytes("UTF-8"));
        coach.setDescription("The characteristics of someone or something");
        coach.setEmail("jane.doe@example.org");
        coach.setIsapproved(1);
        coach.setNo_users_subscribed(1);
        coach.setPassword("iloveyou");
        coach.setPrice("Price");
        coach.setRating(1);
        coach.setUsername("janedoe");
        coach.setImage("Image".getBytes("UTF-8"));
        when(iservice.addImageToCoach(Mockito.<String>any(), Mockito.<MultipartFile>any())).thenReturn(coach);
        //build the request that have an image as multipart file in the param
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .multipart("/coach/addImageToCoach/{Email}", "jane.doe@example.org")
                .file(file);
        MockMvcBuilders.standaloneSetup(coachController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"coach_id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"image\":\"SW1hZ2U=\"," +
                                        "\"password\":\"iloveyou\",\"contact_number"
                                        + "\":\"42\",\"address\":null,\"description\":\"The characteristics of someone or something\"," +
                                        "\"cv\":\"QVhBWEFYQVg=\",\"rating\":1,"
                                        + "\"price\":\"Price\",\"no_users_subscribed\":1,\"isapproved\":1,\"users\":null,\"users_reports\":null,\"plans\":null}"));

    }
    /**
     * Method under test: {@link CoachController#updateCaoch(Coach)}
     */
    @Test
    void testUpdateCaoch() throws Exception {
        Coach coach = new Coach();
        coach.setCoach_id(1L);
        coach.setUsername("janedoe");
        when(iservice.updateCoach(Mockito.<Coach>any())).thenReturn(coach);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/coach/updateCoach")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"coach_id\":1,\"username\":\"janedoe\"}");
        MockMvcBuilders.standaloneSetup(coachController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().json("{\"coach_id\":1,\"username\":\"janedoe\"}"));
    }

    @Test
    void testUpdateCV() throws Exception {
        Coach coach = new Coach();
        coach.setCoach_id(1L);
        coach.setUsername("janedoe");
        when(iservice.updateCoachCV(Mockito.<String>any(), Mockito.<byte[]>any())).thenReturn(coach);
        MockMultipartFile file = new MockMultipartFile("file", "cv.txt", MediaType.TEXT_PLAIN_VALUE, "CV content".getBytes());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .multipart("/coach/updateCV/{email}", "jane.doe@example.org")
                .file(file);
        MockMvcBuilders.standaloneSetup(coachController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().json("{\"coach_id\":1,\"username\":\"janedoe\"}"));
    }


}
