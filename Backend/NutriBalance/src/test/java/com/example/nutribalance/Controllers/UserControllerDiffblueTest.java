package com.example.nutribalance.Controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.nutribalance.Entities.Coach;
import com.example.nutribalance.Entities.Plan;
import com.example.nutribalance.Entities.User;
import com.example.nutribalance.Services.Iservice;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerDiffblueTest {
    @MockBean
    private Iservice iservice;

    @Autowired
    private UserController userController;

    /**
     * Method under test: {@link UserController#updateUser(User)}
     */
    @Test
    void testUpdateUser() throws Exception {
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
        when(iservice.updateUser(Mockito.<User>any())).thenReturn(user);

        Coach coach3 = new Coach();
        coach3.setAddress("42 Main St");
        coach3.setCoach_id(1L);
        coach3.setContact_number("42");
        coach3.setCv("AXAXAXAX".getBytes("UTF-8"));
        coach3.setDescription("The characteristics of someone or something");
        coach3.setEmail("jane.doe@example.org");
        coach3.setEnabled(true);
        coach3.setImage("AXAXAXAX".getBytes("UTF-8"));
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
        coach4.setCv("AXAXAXAX".getBytes("UTF-8"));
        coach4.setDescription("The characteristics of someone or something");
        coach4.setEmail("jane.doe@example.org");
        coach4.setEnabled(true);
        coach4.setImage("AXAXAXAX".getBytes("UTF-8"));
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
        user2.setImage("AXAXAXAX".getBytes("UTF-8"));
        user2.setPassword("iloveyou");
        user2.setPlan(plan2);
        user2.setUser_id(1L);
        user2.setUsername("janedoe");
        user2.setWeights(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(user2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/user/updateUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"user_id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"contact_number"
                                        + "\":\"42\",\"weights\":[],\"image\":\"QVhBWEFYQVg=\",\"plan\":{\"planName\":\"Plan Name\",\"description\":\"The characteristics"
                                        + " of someone or something\",\"coach\":{\"coach_id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\","
                                        + "\"image\":\"QVhBWEFYQVg=\",\"password\":\"iloveyou\",\"contact_number\":\"42\",\"address\":\"42 Main St\",\"description\":\"The"
                                        + " characteristics of someone or something\",\"cv\":\"QVhBWEFYQVg=\",\"rating\":1,\"price\":\"Price\",\"no_users"
                                        + "_subscribed\":1,\"isapproved\":1,\"enabled\":true},\"goal\":\"Goal\"},\"coach\":{\"coach_id\":1,\"username\":\"janedoe"
                                        + "\",\"email\":\"jane.doe@example.org\",\"image\":\"QVhBWEFYQVg=\",\"password\":\"iloveyou\",\"contact_number\":\"42\","
                                        + "\"address\":\"42 Main St\",\"description\":\"The characteristics of someone or something\",\"cv\":\"QVhBWEFYQVg"
                                        + "=\",\"rating\":1,\"price\":\"Price\",\"no_users_subscribed\":1,\"isapproved\":1,\"enabled\":true},\"coaches_reports"
                                        + "\":[],\"comment\":\"Comment\",\"enabled\":true}"));
    }

    /**
     * Method under test:
     * {@link UserController#addImageToUser(String, MultipartFile)}
     */
    @Test
    void testAddImageToUser() throws Exception {
        DataInputStream contentStream = mock(DataInputStream.class);
        when(contentStream.readAllBytes()).thenReturn("AXAXAXAX".getBytes("UTF-8"));
        doNothing().when(contentStream).close();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/addImageToUser/{Email}", "", "Uri Variables")
                .param("file", String.valueOf(new MockMultipartFile("Name", contentStream)));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test:  {@link UserController#getFoodCalorie()}
     */
    @Test
    void testGetFoodCalorie() throws Exception {
        when(iservice.getFoodCalorie()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/food_calorie");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UserController#saveUser(User)}
     */
    @Test
    void testSaveUser() throws Exception {
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
        when(iservice.saveuser(Mockito.<User>any())).thenReturn(user);

        Coach coach3 = new Coach();
        coach3.setAddress("42 Main St");
        coach3.setCoach_id(1L);
        coach3.setContact_number("42");
        coach3.setCv("AXAXAXAX".getBytes("UTF-8"));
        coach3.setDescription("The characteristics of someone or something");
        coach3.setEmail("jane.doe@example.org");
        coach3.setEnabled(true);
        coach3.setImage("AXAXAXAX".getBytes("UTF-8"));
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
        coach4.setCv("AXAXAXAX".getBytes("UTF-8"));
        coach4.setDescription("The characteristics of someone or something");
        coach4.setEmail("jane.doe@example.org");
        coach4.setEnabled(true);
        coach4.setImage("AXAXAXAX".getBytes("UTF-8"));
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
        user2.setImage("AXAXAXAX".getBytes("UTF-8"));
        user2.setPassword("iloveyou");
        user2.setPlan(plan2);
        user2.setUser_id(1L);
        user2.setUsername("janedoe");
        user2.setWeights(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(user2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"user_id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"contact_number"
                                        + "\":\"42\",\"weights\":[],\"image\":\"QVhBWEFYQVg=\",\"plan\":{\"planName\":\"Plan Name\",\"description\":\"The characteristics"
                                        + " of someone or something\",\"coach\":{\"coach_id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\","
                                        + "\"image\":\"QVhBWEFYQVg=\",\"password\":\"iloveyou\",\"contact_number\":\"42\",\"address\":\"42 Main St\",\"description\":\"The"
                                        + " characteristics of someone or something\",\"cv\":\"QVhBWEFYQVg=\",\"rating\":1,\"price\":\"Price\",\"no_users"
                                        + "_subscribed\":1,\"isapproved\":1,\"enabled\":true},\"goal\":\"Goal\"},\"coach\":{\"coach_id\":1,\"username\":\"janedoe"
                                        + "\",\"email\":\"jane.doe@example.org\",\"image\":\"QVhBWEFYQVg=\",\"password\":\"iloveyou\",\"contact_number\":\"42\","
                                        + "\"address\":\"42 Main St\",\"description\":\"The characteristics of someone or something\",\"cv\":\"QVhBWEFYQVg"
                                        + "=\",\"rating\":1,\"price\":\"Price\",\"no_users_subscribed\":1,\"isapproved\":1,\"enabled\":true},\"coaches_reports"
                                        + "\":[],\"comment\":\"Comment\",\"enabled\":true}"));
    }

    /**
     * Method under test: {@link UserController#signIn(String, String)}
     */
    @Test
    void testSignIn() throws Exception {
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
        when(iservice.usersignin(Mockito.<String>any(), Mockito.<String>any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/usersignin/{email}/{password}",
                "jane.doe@example.org", "iloveyou");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"user_id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"contact_number"
                                        + "\":\"42\",\"weights\":[],\"image\":\"QVhBWEFYQVg=\",\"plan\":{\"planName\":\"Plan Name\",\"description\":\"The characteristics"
                                        + " of someone or something\",\"coach\":{\"coach_id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\","
                                        + "\"image\":\"QVhBWEFYQVg=\",\"password\":\"iloveyou\",\"contact_number\":\"42\",\"address\":\"42 Main St\",\"description\":\"The"
                                        + " characteristics of someone or something\",\"cv\":\"QVhBWEFYQVg=\",\"rating\":1,\"price\":\"Price\",\"no_users"
                                        + "_subscribed\":1,\"isapproved\":1,\"enabled\":true},\"goal\":\"Goal\"},\"coach\":{\"coach_id\":1,\"username\":\"janedoe"
                                        + "\",\"email\":\"jane.doe@example.org\",\"image\":\"QVhBWEFYQVg=\",\"password\":\"iloveyou\",\"contact_number\":\"42\","
                                        + "\"address\":\"42 Main St\",\"description\":\"The characteristics of someone or something\",\"cv\":\"QVhBWEFYQVg"
                                        + "=\",\"rating\":1,\"price\":\"Price\",\"no_users_subscribed\":1,\"isapproved\":1,\"enabled\":true},\"coaches_reports"
                                        + "\":[],\"comment\":\"Comment\",\"enabled\":true}"));
    }

    /**
     * Method under test: {@link UserController#subscribe(String, Long)}
     */
    @Test
    void testSubscribe() throws Exception {
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
        when(iservice.subscribe_to_plan(Mockito.<String>any(), Mockito.<Long>any())).thenReturn(user);
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/user/subscribe").param("planName", "foo");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("user_id", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"user_id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"contact_number"
                                        + "\":\"42\",\"weights\":[],\"image\":\"QVhBWEFYQVg=\",\"plan\":{\"planName\":\"Plan Name\",\"description\":\"The characteristics"
                                        + " of someone or something\",\"coach\":{\"coach_id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\","
                                        + "\"image\":\"QVhBWEFYQVg=\",\"password\":\"iloveyou\",\"contact_number\":\"42\",\"address\":\"42 Main St\",\"description\":\"The"
                                        + " characteristics of someone or something\",\"cv\":\"QVhBWEFYQVg=\",\"rating\":1,\"price\":\"Price\",\"no_users"
                                        + "_subscribed\":1,\"isapproved\":1,\"enabled\":true},\"goal\":\"Goal\"},\"coach\":{\"coach_id\":1,\"username\":\"janedoe"
                                        + "\",\"email\":\"jane.doe@example.org\",\"image\":\"QVhBWEFYQVg=\",\"password\":\"iloveyou\",\"contact_number\":\"42\","
                                        + "\"address\":\"42 Main St\",\"description\":\"The characteristics of someone or something\",\"cv\":\"QVhBWEFYQVg"
                                        + "=\",\"rating\":1,\"price\":\"Price\",\"no_users_subscribed\":1,\"isapproved\":1,\"enabled\":true},\"coaches_reports"
                                        + "\":[],\"comment\":\"Comment\",\"enabled\":true}"));
    }

    /**
     * Method under test:  {@link UserController#verifyUser(String)}
     */
    @Test
    void testVerifyUser() throws Exception {
        when(iservice.verify(Mockito.<String>any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/verify").param("code", "foo");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test:  {@link UserController#verifyUser(String)}
     */
    @Test
    void testVerifyUser2() throws Exception {
        when(iservice.verify(Mockito.<String>any())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/verify").param("code", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Error: Verification code is invalid!"));
    }
}
