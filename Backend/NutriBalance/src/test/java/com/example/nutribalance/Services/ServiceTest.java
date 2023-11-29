//package com.example.nutribalance.Services;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertSame;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.anyInt;
//import static org.mockito.Mockito.anyLong;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.example.nutribalance.Entities.Coach;
//import com.example.nutribalance.Entities.Plan;
//import com.example.nutribalance.Entities.ResetPassword;
//import com.example.nutribalance.Entities.User;
//import com.example.nutribalance.Entities.Weight;
//import com.example.nutribalance.Repositries.CoachRepositry;
//import com.example.nutribalance.Repositries.ResetPasswordRepository;
//import com.example.nutribalance.Repositries.SubscriptionRepositry;
//import com.example.nutribalance.Repositries.UserRepositry;
//import com.example.nutribalance.dto.LoginRequest;
//import jakarta.mail.MessagingException;
//import jakarta.mail.Session;
//import jakarta.mail.internet.MimeMessage;
//
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.mail.MailException;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ContextConfiguration(classes = {Service.class})
//@ExtendWith(SpringExtension.class)
//class ServiceTest {
//    @MockBean
//    private CoachRepositry coachRepositry;
//
//    @MockBean(name = "email2Sender")
//    private JavaMailSender javaMailSender;
//
//    @MockBean
//    private PasswordEncoder passwordEncoder;
//
//    @MockBean
//    private ResetPasswordRepository resetPasswordRepository;
//
//    @Autowired
//    private Iservice service;
//
//    @MockBean
//    private SubscriptionRepositry subscriptionRepositry;
//
//    @MockBean
//    private UserRepositry userRepositry;
//
//    /**
//     * Method under test: {@link Service#savecoach(Coach)}
//     */
//    @Test
//    void testSavecoach() throws UnsupportedEncodingException {
//        Coach coach = new Coach();
//        coach.setCoach_id(1L);
//        coach.setContact_number("42");
//        coach.setCv("AXAXAXAX".getBytes("UTF-8"));
//        coach.setDescription("The characteristics of someone or something");
//        coach.setEmail("jane.doe@example.org");
//        coach.setIsapproved(1);
//        coach.setNo_users_subscribed(1);
//        coach.setPassword("iloveyou");
//        coach.setPlans(new ArrayList<>());
//        coach.setPrice("Price");
//        coach.setRating(1);
//        coach.setUsername("janedoe");
//        coach.setUsers(new ArrayList<>());
//        coach.setUsers_reports(new ArrayList<>());
//        Optional<Coach> ofResult = Optional.of(coach);
//
//        Coach coach2 = new Coach();
//        coach2.setCoach_id(1L);
//        coach2.setContact_number("42");
//        coach2.setCv("AXAXAXAX".getBytes("UTF-8"));
//        coach2.setDescription("The characteristics of someone or something");
//        coach2.setEmail("jane.doe@example.org");
//        coach2.setIsapproved(1);
//        coach2.setNo_users_subscribed(1);
//        coach2.setPassword("iloveyou");
//        coach2.setPlans(new ArrayList<>());
//        coach2.setPrice("Price");
//        coach2.setRating(1);
//        coach2.setUsername("janedoe");
//        coach2.setUsers(new ArrayList<>());
//        coach2.setUsers_reports(new ArrayList<>());
//        Optional<Coach> ofResult2 = Optional.of(coach2);
//        when(coachRepositry.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
//        when(coachRepositry.findByUsername(Mockito.<String>any())).thenReturn(ofResult2);
//
//        Coach coach3 = new Coach();
//        coach3.setCoach_id(1L);
//        coach3.setContact_number("42");
//        coach3.setCv("AXAXAXAX".getBytes("UTF-8"));
//        coach3.setDescription("The characteristics of someone or something");
//        coach3.setEmail("jane.doe@example.org");
//        coach3.setIsapproved(1);
//        coach3.setNo_users_subscribed(1);
//        coach3.setPassword("iloveyou");
//        coach3.setPlans(new ArrayList<>());
//        coach3.setPrice("Price");
//        coach3.setRating(1);
//        coach3.setUsername("janedoe");
//        coach3.setUsers(new ArrayList<>());
//        coach3.setUsers_reports(new ArrayList<>());
//        Coach actualSavecoachResult = service.savecoach(coach3);
//        verify(coachRepositry).findByEmail(Mockito.<String>any());
//        verify(coachRepositry).findByUsername(Mockito.<String>any());
//        assertNull(actualSavecoachResult);
//    }
//
//    /**
//     * Method under test: {@link Service#savecoach(Coach)}
//     */
//    @Test
//    void testSavecoach2() throws UnsupportedEncodingException {
//        Coach coach = new Coach();
//        coach.setCoach_id(1L);
//        coach.setContact_number("42");
//        coach.setCv("AXAXAXAX".getBytes("UTF-8"));
//        coach.setDescription("The characteristics of someone or something");
//        coach.setEmail("jane.doe@example.org");
//        coach.setIsapproved(1);
//        coach.setNo_users_subscribed(1);
//        coach.setPassword("iloveyou");
//        coach.setPlans(new ArrayList<>());
//        coach.setPrice("Price");
//        coach.setRating(1);
//        coach.setUsername("janedoe");
//        coach.setUsers(new ArrayList<>());
//        coach.setUsers_reports(new ArrayList<>());
//        Optional<Coach> ofResult = Optional.of(coach);
//        Optional<Coach> emptyResult = Optional.empty();
//        when(coachRepositry.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);
//        when(coachRepositry.findByUsername(Mockito.<String>any())).thenReturn(ofResult);
//
//        Coach coach2 = new Coach();
//        coach2.setCoach_id(1L);
//        coach2.setContact_number("42");
//        coach2.setCv("AXAXAXAX".getBytes("UTF-8"));
//        coach2.setDescription("The characteristics of someone or something");
//        coach2.setEmail("jane.doe@example.org");
//        coach2.setIsapproved(1);
//        coach2.setNo_users_subscribed(1);
//        coach2.setPassword("iloveyou");
//        coach2.setPlans(new ArrayList<>());
//        coach2.setPrice("Price");
//        coach2.setRating(1);
//        coach2.setUsername("janedoe");
//        coach2.setUsers(new ArrayList<>());
//        coach2.setUsers_reports(new ArrayList<>());
//        Coach actualSavecoachResult = service.savecoach(coach2);
//        verify(coachRepositry).findByEmail(Mockito.<String>any());
//        verify(coachRepositry).findByUsername(Mockito.<String>any());
//        assertNull(actualSavecoachResult);
//    }
//
//    /**
//     * Method under test: {@link Service#get_waiting_coaches()}
//     */
//    @Test
//    void testGet_waiting_coaches() {
//        ArrayList<Coach> coachList = new ArrayList<>();
//        when(coachRepositry.findByisapproved(anyInt())).thenReturn(coachList);
//        List<Coach> actualGet_waiting_coachesResult = service.get_waiting_coaches();
//        verify(coachRepositry).findByisapproved(anyInt());
//        assertTrue(actualGet_waiting_coachesResult.isEmpty());
//        assertSame(coachList, actualGet_waiting_coachesResult);
//    }
//
//    /**
//     * Method under test: {@link Service#deletecoach(Long)}
//     */
//    @Test
//    void testDeletecoach() {
//        doNothing().when(coachRepositry).deleteById(Mockito.<Long>any());
//        String actualDeletecoachResult = service.deletecoach(1L);
//        verify(coachRepositry).deleteById(Mockito.<Long>any());
//        assertEquals("coach deleted", actualDeletecoachResult);
//    }
//
//    /**
//     * Method under test: {@link Service#saveuser(User)}
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
//        Optional<User> ofResult = Optional.of(user);
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
//        Optional<User> ofResult2 = Optional.of(user2);
//        when(userRepositry.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
//        when(userRepositry.findByUsername(Mockito.<String>any())).thenReturn(ofResult2);
//
//        User user3 = new User();
//        user3.setCoaches(new ArrayList<>());
//        user3.setCoaches_reports(new ArrayList<>());
//        user3.setContact_number("42");
//        user3.setEmail("jane.doe@example.org");
//        user3.setPassword("iloveyou");
//        user3.setPlan(new Plan());
//        user3.setUser_id(1L);
//        user3.setUsername("janedoe");
//        user3.setWeights(new ArrayList<>());
//        User actualSaveuserResult = service.saveuser(user3);
//        verify(userRepositry).findByEmail(Mockito.<String>any());
//        verify(userRepositry).findByUsername(Mockito.<String>any());
//        assertNull(actualSaveuserResult);
//    }
//
//    /**
//     * Method under test: {@link Service#saveuser(User)}
//     */
//    @Test
//    void testSaveuser2() {
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
//        Optional<User> ofResult = Optional.of(user);
//        Optional<User> emptyResult = Optional.empty();
//        when(userRepositry.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);
//        when(userRepositry.findByUsername(Mockito.<String>any())).thenReturn(ofResult);
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
//        User actualSaveuserResult = service.saveuser(user2);
//        verify(userRepositry).findByEmail(Mockito.<String>any());
//        verify(userRepositry).findByUsername(Mockito.<String>any());
//        assertNull(actualSaveuserResult);
//    }
//
//    /**
//     * Method under test: {@link Service#usersignin(String, String)}
//     */
//    @Test
//    void testUsersignin() {
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
//        Optional<User> ofResult = Optional.of(user);
//        when(userRepositry.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
//        User actualUsersigninResult = service.usersignin("jane.doe@example.org", "iloveyou");
//        verify(userRepositry).findByEmail(Mockito.<String>any());
//        assertSame(user, actualUsersigninResult);
//    }
//
//    /**
//     * Method under test: {@link Service#usersignin(String, String)}
//     */
//    @Test
//    void testUsersignin2() {
//        User user = mock(User.class);
//        when(user.getPassword()).thenReturn("foo");
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
//        Optional<User> ofResult = Optional.of(user);
//        when(userRepositry.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
//        User actualUsersigninResult = service.usersignin("jane.doe@example.org", "iloveyou");
//        verify(user).getPassword();
//        verify(user).setCoaches(Mockito.<List<Coach>>any());
//        verify(user).setCoaches_reports(Mockito.<List<Coach>>any());
//        verify(user).setContact_number(Mockito.<String>any());
//        verify(user).setEmail(Mockito.<String>any());
//        verify(user).setPassword(Mockito.<String>any());
//        verify(user).setPlan(Mockito.<Plan>any());
//        verify(user).setUser_id(anyLong());
//        verify(user).setUsername(Mockito.<String>any());
//        verify(user).setWeights(Mockito.<List<Weight>>any());
//        verify(userRepositry).findByEmail(Mockito.<String>any());
//        assertNull(actualUsersigninResult);
//    }
//
//    /**
//     * Method under test: {@link Service#usersignin(String, String)}
//     */
//    @Test
//    void testUsersignin3() {
//        Optional<User> emptyResult = Optional.empty();
//        when(userRepositry.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);
//        User actualUsersigninResult = service.usersignin("jane.doe@example.org", "iloveyou");
//        verify(userRepositry).findByEmail(Mockito.<String>any());
//        assertNull(actualUsersigninResult);
//    }
//
//    /**
//     * Method under test: {@link Service#findByEmailRole(String, String)}
//     */
//    @Test
//    void testFindByEmailRole() {
//        assertNull(service.findByEmailRole("jane.doe@example.org", "Role"));
//    }
//
//    /**
//     * Method under test: {@link Service#findByEmailRole(String, String)}
//     */
//    @Test
//    void testFindByEmailRole2() {
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
//        Optional<User> ofResult = Optional.of(user);
//        when(userRepositry.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
//        String actualFindByEmailRoleResult = service.findByEmailRole("jane.doe@example.org", "user");
//        verify(userRepositry).findByEmail(Mockito.<String>any());
//        assertEquals("janedoe", actualFindByEmailRoleResult);
//    }
//
//    /**
//     * Method under test: {@link Service#findByEmailRole(String, String)}
//     */
//    @Test
//    void testFindByEmailRole3() {
//        Optional<User> emptyResult = Optional.empty();
//        when(userRepositry.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);
//        String actualFindByEmailRoleResult = service.findByEmailRole("jane.doe@example.org", "user");
//        verify(userRepositry).findByEmail(Mockito.<String>any());
//        assertNull(actualFindByEmailRoleResult);
//    }
//
//    /**
//     * Method under test: {@link Service#findByEmailRole(String, String)}
//     */
//    @Test
//    void testFindByEmailRole4() throws UnsupportedEncodingException {
//        Coach coach = new Coach();
//        coach.setCoach_id(1L);
//        coach.setContact_number("42");
//        coach.setCv("AXAXAXAX".getBytes("UTF-8"));
//        coach.setDescription("The characteristics of someone or something");
//        coach.setEmail("jane.doe@example.org");
//        coach.setIsapproved(1);
//        coach.setNo_users_subscribed(1);
//        coach.setPassword("iloveyou");
//        coach.setPlans(new ArrayList<>());
//        coach.setPrice("Price");
//        coach.setRating(1);
//        coach.setUsername("janedoe");
//        coach.setUsers(new ArrayList<>());
//        coach.setUsers_reports(new ArrayList<>());
//        Optional<Coach> ofResult = Optional.of(coach);
//        when(coachRepositry.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
//        String actualFindByEmailRoleResult = service.findByEmailRole("jane.doe@example.org", "coach");
//        verify(coachRepositry).findByEmail(Mockito.<String>any());
//        assertEquals("janedoe", actualFindByEmailRoleResult);
//    }
//
//    /**
//     * Method under test: {@link Service#findByEmailRole(String, String)}
//     */
//    @Test
//    void testFindByEmailRole5() {
//        Optional<Coach> emptyResult = Optional.empty();
//        when(coachRepositry.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);
//        String actualFindByEmailRoleResult = service.findByEmailRole("jane.doe@example.org", "coach");
//        verify(coachRepositry).findByEmail(Mockito.<String>any());
//        assertNull(actualFindByEmailRoleResult);
//    }
//
//    /**
//     * Method under test: {@link Service#sendForgetPasswordEmail(ResetPassword)}
//     */
//    @Test
//    void testSendForgetPasswordEmail() throws MessagingException, UnsupportedEncodingException, MailException {
//        doNothing().when(javaMailSender).send(Mockito.<MimeMessage>any());
//        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
//
//        ResetPassword resetPassword = new ResetPassword();
//        resetPassword.setEmail("jane.doe@example.org");
//        resetPassword.setId(1L);
//        resetPassword.setToken("ABC123");
//        resetPassword.setUsername("janedoe");
//        service.sendForgetPasswordEmail(resetPassword);
//        verify(javaMailSender).createMimeMessage();
//        verify(javaMailSender).send(Mockito.<MimeMessage>any());
//    }
//
//    /**
//     * Method under test: {@link Service#create_reset_password(ResetPassword)}
//     */
//    @Test
//    void testCreate_reset_password() {
//        ResetPassword resetPassword = new ResetPassword();
//        resetPassword.setEmail("jane.doe@example.org");
//        resetPassword.setId(1L);
//        resetPassword.setToken("ABC123");
//        resetPassword.setUsername("janedoe");
//
//        ResetPassword resetPassword2 = new ResetPassword();
//        resetPassword2.setEmail("jane.doe@example.org");
//        resetPassword2.setId(1L);
//        resetPassword2.setToken("ABC123");
//        resetPassword2.setUsername("janedoe");
//        doNothing().when(resetPasswordRepository).deleteById(Mockito.<Long>any());
//        when(resetPasswordRepository.findByEmail(Mockito.<String>any())).thenReturn(resetPassword);
//        when(resetPasswordRepository.save(Mockito.<ResetPassword>any())).thenReturn(resetPassword2);
//
//        ResetPassword resetPassword3 = new ResetPassword();
//        resetPassword3.setEmail("jane.doe@example.org");
//        resetPassword3.setId(1L);
//        resetPassword3.setToken("ABC123");
//        resetPassword3.setUsername("janedoe");
//        service.create_reset_password(resetPassword3);
//        verify(resetPasswordRepository).findByEmail(Mockito.<String>any());
//        verify(resetPasswordRepository).deleteById(Mockito.<Long>any());
//        verify(resetPasswordRepository).save(Mockito.<ResetPassword>any());
//        assertEquals("ABC123", resetPassword3.getToken());
//        assertEquals("jane.doe@example.org", resetPassword3.getEmail());
//        assertEquals("janedoe", resetPassword3.getUsername());
//        assertEquals(1L, resetPassword3.getId().longValue());
//    }
//
//    /**
//     * Method under test: {@link Service#get_reset_password(String)}
//     */
//    @Test
//    void testGet_reset_password() {
//        ResetPassword resetPassword = new ResetPassword();
//        resetPassword.setEmail("jane.doe@example.org");
//        resetPassword.setId(1L);
//        resetPassword.setToken("ABC123");
//        resetPassword.setUsername("janedoe");
//        when(resetPasswordRepository.findByEmail(Mockito.<String>any())).thenReturn(resetPassword);
//        ResetPassword actualGet_reset_passwordResult = service.get_reset_password("jane.doe@example.org");
//        verify(resetPasswordRepository).findByEmail(Mockito.<String>any());
//        assertSame(resetPassword, actualGet_reset_passwordResult);
//    }
//
//    /**
//     * Method under test: {@link Service#resetPassword(LoginRequest, String)}
//     */
//    @Test
//    void testResetPassword() {
//        LoginRequest loginRequest = new LoginRequest("jane.doe@example.org", "iloveyou");
//
//        service.resetPassword(loginRequest, "Role");
//        assertEquals("iloveyou", loginRequest.getPassword());
//        assertEquals("jane.doe@example.org", loginRequest.getEmail());
//    }
//
//    /**
//     * Method under test: {@link Service#resetPassword(LoginRequest, String)}
//     */
//    @Test
//    void testResetPassword2() {
//        ResetPassword resetPassword = new ResetPassword();
//        resetPassword.setEmail("jane.doe@example.org");
//        resetPassword.setId(1L);
//        resetPassword.setToken("ABC123");
//        resetPassword.setUsername("janedoe");
//        doNothing().when(resetPasswordRepository).deleteById(Mockito.<Long>any());
//        when(resetPasswordRepository.findByEmail(Mockito.<String>any())).thenReturn(resetPassword);
//
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
//        Optional<User> ofResult = Optional.of(user);
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
//        when(userRepositry.save(Mockito.<User>any())).thenReturn(user2);
//        when(userRepositry.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
//        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
//        service.resetPassword(new LoginRequest("jane.doe@example.org", "iloveyou"), "user");
//        verify(resetPasswordRepository).findByEmail(Mockito.<String>any());
//        verify(userRepositry).findByEmail(Mockito.<String>any());
//        verify(resetPasswordRepository).deleteById(Mockito.<Long>any());
//        verify(userRepositry).save(Mockito.<User>any());
//        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
//    }
//
//    /**
//     * Method under test: {@link Service#resetPassword(LoginRequest, String)}
//     */
//    @Test
//    void testResetPassword3() {
//        Optional<User> emptyResult = Optional.empty();
//        when(userRepositry.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);
//        LoginRequest loginRequest = new LoginRequest("jane.doe@example.org", "iloveyou");
//
//        service.resetPassword(loginRequest, "user");
//        verify(userRepositry).findByEmail(Mockito.<String>any());
//        assertEquals("iloveyou", loginRequest.getPassword());
//        assertEquals("jane.doe@example.org", loginRequest.getEmail());
//    }
//
//    /**
//     * Method under test: {@link Service#resetPassword(LoginRequest, String)}
//     */
//    @Test
//    void testResetPassword4() throws UnsupportedEncodingException {
//        Coach coach = new Coach();
//        coach.setCoach_id(1L);
//        coach.setContact_number("42");
//        coach.setCv("AXAXAXAX".getBytes("UTF-8"));
//        coach.setDescription("The characteristics of someone or something");
//        coach.setEmail("jane.doe@example.org");
//        coach.setIsapproved(1);
//        coach.setNo_users_subscribed(1);
//        coach.setPassword("iloveyou");
//        coach.setPlans(new ArrayList<>());
//        coach.setPrice("Price");
//        coach.setRating(1);
//        coach.setUsername("janedoe");
//        coach.setUsers(new ArrayList<>());
//        coach.setUsers_reports(new ArrayList<>());
//        Optional<Coach> ofResult = Optional.of(coach);
//
//        Coach coach2 = new Coach();
//        coach2.setCoach_id(1L);
//        coach2.setContact_number("42");
//        coach2.setCv("AXAXAXAX".getBytes("UTF-8"));
//        coach2.setDescription("The characteristics of someone or something");
//        coach2.setEmail("jane.doe@example.org");
//        coach2.setIsapproved(1);
//        coach2.setNo_users_subscribed(1);
//        coach2.setPassword("iloveyou");
//        coach2.setPlans(new ArrayList<>());
//        coach2.setPrice("Price");
//        coach2.setRating(1);
//        coach2.setUsername("janedoe");
//        coach2.setUsers(new ArrayList<>());
//        coach2.setUsers_reports(new ArrayList<>());
//        when(coachRepositry.save(Mockito.<Coach>any())).thenReturn(coach2);
//        when(coachRepositry.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
//
//        ResetPassword resetPassword = new ResetPassword();
//        resetPassword.setEmail("jane.doe@example.org");
//        resetPassword.setId(1L);
//        resetPassword.setToken("ABC123");
//        resetPassword.setUsername("janedoe");
//        doNothing().when(resetPasswordRepository).deleteById(Mockito.<Long>any());
//        when(resetPasswordRepository.findByEmail(Mockito.<String>any())).thenReturn(resetPassword);
//
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
//        when(userRepositry.save(Mockito.<User>any())).thenReturn(user);
//        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
//        service.resetPassword(new LoginRequest("jane.doe@example.org", "iloveyou"), "coach");
//        verify(coachRepositry).findByEmail(Mockito.<String>any());
//        verify(resetPasswordRepository).findByEmail(Mockito.<String>any());
//        verify(resetPasswordRepository).deleteById(Mockito.<Long>any());
//        verify(coachRepositry).save(Mockito.<Coach>any());
//        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
//    }
//
//    /**
//     * Method under test: {@link Service#coachsignin(String, String)}
//     */
//    @Test
//    void testCoachsignin() throws UnsupportedEncodingException {
//        Coach coach = new Coach();
//        coach.setCoach_id(1L);
//        coach.setContact_number("42");
//        coach.setCv("AXAXAXAX".getBytes("UTF-8"));
//        coach.setDescription("The characteristics of someone or something");
//        coach.setEmail("jane.doe@example.org");
//        coach.setIsapproved(1);
//        coach.setNo_users_subscribed(1);
//        coach.setPassword("iloveyou");
//        coach.setPlans(new ArrayList<>());
//        coach.setPrice("Price");
//        coach.setRating(1);
//        coach.setUsername("janedoe");
//        coach.setUsers(new ArrayList<>());
//        coach.setUsers_reports(new ArrayList<>());
//        Optional<Coach> ofResult = Optional.of(coach);
//        when(coachRepositry.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
//        Coach actualCoachsigninResult = service.coachsignin("jane.doe@example.org", "Pass");
//        verify(coachRepositry).findByEmail(Mockito.<String>any());
//        assertNull(actualCoachsigninResult);
//    }
//
//
//
//    /**
//     * Method under test: {@link Service#coachsignin(String, String)}
//     */
//    @Test
//    void testCoachsignin3() {
//        Optional<Coach> emptyResult = Optional.empty();
//        when(coachRepositry.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);
//        Coach actualCoachsigninResult = service.coachsignin("jane.doe@example.org", "Pass");
//        verify(coachRepositry).findByEmail(Mockito.<String>any());
//        assertNull(actualCoachsigninResult);
//    }
//}
