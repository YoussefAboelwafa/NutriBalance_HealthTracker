package com.example.nutribalance.Controllers;

import static org.mockito.Mockito.when;

import com.example.nutribalance.Entities.Coach;
import com.example.nutribalance.Entities.Plan;
import com.example.nutribalance.Services.Iservice;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PlanController.class})
@ExtendWith(SpringExtension.class)
class PlanControllerTest {
    @MockBean
    private Iservice iservice;

    @Autowired
    private PlanController planController;

    /**
     * Method under test: {@link PlanController#saveplan(Plan)}
     */
    @Test
    void testSaveplan() throws Exception {
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

        Plan plan = new Plan();
        plan.setCoach(coach);
        plan.setDescription("The characteristics of someone or something");
        plan.setGoal("Goal");
        plan.setPlanName("Plan Name");
        plan.setUsers(new ArrayList<>());
        when(iservice.saveplan(Mockito.<Plan>any())).thenReturn(plan);

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

        Plan plan2 = new Plan();
        plan2.setCoach(coach2);
        plan2.setDescription("The characteristics of someone or something");
        plan2.setGoal("Goal");
        plan2.setPlanName("Plan Name");
        plan2.setUsers(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(plan2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/plan/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(planController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"planName\":\"Plan Name\",\"description\":\"The characteristics of someone or something\",\"coach\":{\"coach"
                                        + "_id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"image\":\"QVhBWEFYQVg=\",\"password\":\"iloveyou"
                                        + "\",\"contact_number\":\"42\",\"address\":\"42 Main St\",\"description\":\"The characteristics of someone or"
                                        + " something\",\"cv\":\"QVhBWEFYQVg=\",\"rating\":1,\"price\":\"Price\",\"no_users_subscribed\":1,\"isapproved\":1,"
                                        + "\"enabled\":true},\"goal\":\"Goal\"}"));
    }

    /**
     * Method under test:  {@link PlanController#getallplans()}
     */
    @Test
    void testGetallplans() throws Exception {
        when(iservice.getallplans()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/plan/getall");
        MockMvcBuilders.standaloneSetup(planController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
