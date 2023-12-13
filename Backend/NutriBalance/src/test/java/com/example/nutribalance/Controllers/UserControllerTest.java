//package com.example.nutribalance.Controllers;
//
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.mockito.Mockito.anyLong;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.example.nutribalance.Entities.Coach;
//import com.example.nutribalance.Entities.Plan;
//import com.example.nutribalance.Entities.User;
//import com.example.nutribalance.Entities.Weight;
//import com.example.nutribalance.Services.Iservice;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//@ContextConfiguration(classes = {UserController.class})
//@ExtendWith(SpringExtension.class)
//class UserControllerTest {
//    @MockBean
//    private Iservice iservice;
//
//    @Autowired
//    private UserController userController;
//
//    /**
//     * Method under test: {@link UserController#saveUser(User)}
//     */
//    @Test
//    void testSaveUser() throws Exception {
//        User user = new User();
//        user.setCoaches(new ArrayList<>());
//        user.setCoaches_reports(new ArrayList<>());
//        user.setContact_number("42");
//        user.setEmail("jane.doe@example.org");
//        user.setPassword("iloveyou");
//        user.setPlan(new Plan());
//        user.setUser_id(1L);
//        user.setUsername("janedoe");
//        user.setWeights(new ArrayList<>());
//        when(iservice.saveuser(Mockito.<User>any())).thenReturn(user);
//
//        User user2 = new User();
//        user2.setCoaches(new ArrayList<>());
//        user2.setCoaches_reports(new ArrayList<>());
//        user2.setContact_number("42");
//        user2.setEmail("jane.doe@example.org");
//        user2.setPassword("iloveyou");
//        user2.setPlan(new Plan());
//        user2.setUser_id(1L);
//        user2.setUsername("janedoe");
//        user2.setWeights(new ArrayList<>());
//        String content = (new ObjectMapper()).writeValueAsString(user2);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/save")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        MockMvcBuilders.standaloneSetup(userController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content()
//                        .string(
//                                "{\"user_id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"contact_number"
//                                        + "\":\"42\",\"weights\":[],\"plan\":{},\"coaches\":[],\"coaches_reports\":[]}"));
//    }
//
//    /**
//     * Method under test: {@link UserController#saveuser(User)}
//     */
//    @Test
//    void testSaveuser() {
//        User user = new User();
//        user.setCoaches(new ArrayList<>());
//        user.setCoaches_reports(new ArrayList<>());
//        user.setContact_number("42");
//        user.setEmail("jane.doe@example.org");
//        user.setPassword("iloveyou");
//        user.setPlan(new Plan());
//        user.setUser_id(1L);
//        user.setUsername("janedoe");
//        user.setWeights(new ArrayList<>());
//        assertNull(userController.saveuser(user));
//    }
//
//    /**
//     * Method under test: {@link UserController#saveuser(User)}
//     */
//    @Test
//    void testSaveuser2() {
//        User user = mock(User.class);
//        doNothing().when(user).setCoaches(Mockito.<List<Coach>>any());
//        doNothing().when(user).setCoaches_reports(Mockito.<List<Coach>>any());
//        doNothing().when(user).setContact_number(Mockito.<String>any());
//        doNothing().when(user).setEmail(Mockito.<String>any());
//        doNothing().when(user).setPassword(Mockito.<String>any());
//        doNothing().when(user).setPlan(Mockito.<Plan>any());
//        doNothing().when(user).setUser_id(anyLong());
//        doNothing().when(user).setUsername(Mockito.<String>any());
//        doNothing().when(user).setWeights(Mockito.<List<Weight>>any());
//        user.setCoaches(new ArrayList<>());
//        user.setCoaches_reports(new ArrayList<>());
//        user.setContact_number("42");
//        user.setEmail("jane.doe@example.org");
//        user.setPassword("iloveyou");
//        user.setPlan(new Plan());
//        user.setUser_id(1L);
//        user.setUsername("janedoe");
//        user.setWeights(new ArrayList<>());
//        ResponseEntity<Object> actualSaveuserResult = userController.saveuser(user);
//        verify(user).setCoaches(Mockito.<List<Coach>>any());
//        verify(user).setCoaches_reports(Mockito.<List<Coach>>any());
//        verify(user).setContact_number(Mockito.<String>any());
//        verify(user).setEmail(Mockito.<String>any());
//        verify(user).setPassword(Mockito.<String>any());
//        verify(user).setPlan(Mockito.<Plan>any());
//        verify(user).setUser_id(anyLong());
//        verify(user).setUsername(Mockito.<String>any());
//        verify(user).setWeights(Mockito.<List<Weight>>any());
//        assertNull(actualSaveuserResult);
//    }
//
//    /**
//     * Method under test: {@link UserController#signIn(String, String)}
//     */
//    @Test
//    void testSignIn() throws Exception {
//        User user = new User();
//        user.setCoaches(new ArrayList<>());
//        user.setCoaches_reports(new ArrayList<>());
//        user.setContact_number("42");
//        user.setEmail("jane.doe@example.org");
//        user.setPassword("iloveyou");
//        user.setPlan(new Plan());
//        user.setUser_id(1L);
//        user.setUsername("janedoe");
//        user.setWeights(new ArrayList<>());
//        when(iservice.usersignin(Mockito.<String>any(), Mockito.<String>any())).thenReturn(user);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/usersignin/{email}/{password}",
//                "jane.doe@example.org", "iloveyou");
//        MockMvcBuilders.standaloneSetup(userController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content()
//                        .string(
//                                "{\"user_id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"contact_number"
//                                        + "\":\"42\",\"weights\":[],\"plan\":{},\"coaches\":[],\"coaches_reports\":[]}"));
//    }
//}
