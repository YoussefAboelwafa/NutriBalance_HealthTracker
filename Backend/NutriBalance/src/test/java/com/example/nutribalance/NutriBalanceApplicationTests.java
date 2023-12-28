package com.example.nutribalance;

import com.example.nutribalance.controllers.UserController;
import com.example.nutribalance.entities.*;
import com.example.nutribalance.repositories.*;
import com.example.nutribalance.services.IService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
@RunWith(SpringRunner.class)
@SpringBootTest
class NutriBalanceApplicationTests {
    @Autowired
    private IService service;
    @MockBean
    private CoachRepository coachRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserController userController;
    @MockBean
    private ReportRepository reportRepository;

    @MockBean
    private WeightRepository weightRepository;

    @MockBean
    private PlanRepository planRepository;

    @MockBean
    private ChatRepository chatRepository;
    @Test
    public void testSaveCoach() {
        Coach coach = new Coach();
        coach.setUsername("coach1");
        coach.setEmail("coach1@gmail.com");
        coach.setPassword("123456");
        coach.setDescription("coach1 description");
        coach.setContact_number("01029979868");
        coach.setIsapproved(0);
        //case1:  email  is not found in the database
        when(coachRepository.findByEmail(coach.getEmail())).thenReturn(Optional.empty());
        when(coachRepository.save(coach)).thenReturn(coach);
        assertEquals(coach, service.saveCoach(coach));
        //case2: email is found in the database
        when(coachRepository.findByEmail(coach.getEmail())).thenReturn(Optional.of(coach));
        assertNull(service.saveCoach(coach));

    }



    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("user1");
        user.setEmail("user1@gmail.com");
        user.setPassword("12345678");
        user.setContact_number("01029979868");
        //case1:  email   is not found in the database
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, service.saveuser(user));
        //case2: email is found in the database
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
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

        when(userRepository.findByEmail("tvscx@gmail.com")).thenReturn(Optional.empty());
        assertNull(service.userSignIn("tvscx@gmail.com", "123456"));



        when(userRepository.findByEmail("user1@gmail.com")).thenReturn(Optional.of(user));
        assertNull(service.userSignIn("user1@gmail.com", "111111"));

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
    public void testgetwaitingcoaches() {
        Coach coach1 = new Coach();
        coach1.setUsername("coach1");
        coach1.setEmail("coach1@gmail.com");
        coach1.setPassword("123456");
        coach1.setDescription("coach1 description");
        coach1.setContact_number("01029979868");
        coach1.setIsapproved(0);
        Coach coach2 = new Coach();
        coach2.setUsername("coach2");
        coach2.setEmail("coach2@gmail.com");
        coach2.setPassword("123456");
        coach2.setDescription("coach2 description");
        coach2.setContact_number("01029979868");
        coach2.setIsapproved(0);
        when(coachRepository.findByisapproved(0)).thenReturn(null);
        assertNull(service.getWaitingCoaches());
        when(coachRepository.findByisapproved(0)).thenReturn(List.of(coach1, coach2));
        assertEquals(List.of(coach1, coach2), service.getWaitingCoaches());
    }

@Test
    public void addReport(){
    Report report=new Report();
    User user=new User();
    user.setUser_id(1234L);
    Coach coach=new Coach();
    coach.setCoach_id(4567L);
    report.setUser(user);
    report.setCoach(coach);
    report.setAuthor("user");
    report.setMessage("message");
    when(userRepository.findById(1234L)).thenReturn(Optional.of(user));
    when(coachRepository.findById(4567L)).thenReturn(Optional.of(coach));
    when(reportRepository.save(Mockito.any())).thenReturn(report);
    assertEquals(service.addReport(1234L,4567L,"message","user"),report);
}
@Test
    public void testgetreports(){
        List<Report> reports=new ArrayList<>();
        when(reportRepository.findAll()).thenReturn(null);
        assertNull(service.getReports());
        when(reportRepository.findAll()).thenReturn(reports);
        assertEquals(service.getReports(),reports);
}
@Test public void deletereports(){
    Report report=new Report();
    ReportId reportId=new ReportId();
    User user=new User();
    user.setUser_id(1234L);
    Coach coach=new Coach();
    coach.setCoach_id(4567L);
    reportId.setUser(1234L);
    reportId.setCoach(4567L);
    report.setUser(user);
    report.setCoach(coach);
    report.setMessage("message");
    report.setAuthor("coach");
    service.deleteReport(1234L,4567L);
    verify(reportRepository).deleteById(reportId);
}
@Test
    public void testuserdelete(){
        User user=new User();
        user.setUser_id(1234L);
        Coach coach=new Coach();
        coach.setCoach_id(4567L);
        Report report=new Report();
        report.setUser(user);
        report.setCoach(coach);
        report.setMessage("message");
        report.setAuthor("user");
        Weight weight=new Weight();
        weight.setUser(user);
        weight.setWeight(75);
        Date date=new Date();
        weight.setDate(date);

        Chat chat=new Chat();
        chat.setUser(1234L);
        chat.setCoach(4567L);
        LocalDateTime localDateTime= LocalDate.now().atStartOfDay();
        chat.setLocalDateTime(localDateTime);


        when(reportRepository.findAll()).thenReturn(List.of(report));
        when(weightRepository.findAll()).thenReturn(List.of(weight));

        when(chatRepository.findAll()).thenReturn(List.of(chat));

        service.deleteUser(1234L);

        verify(reportRepository).deleteAll(Mockito.anyList());
        verify(weightRepository).deleteAll(Mockito.anyList());
        verify(chatRepository).deleteAll(Mockito.anyList());
        verify(userRepository).deleteById(1234L);

}
@Test
    public void testcoachdelete(){
    User user=new User();
    user.setUser_id(1234L);
    Coach coach=new Coach();
    coach.setCoach_id(4567L);
    Report report=new Report();
    report.setUser(user);
    report.setCoach(coach);
    report.setMessage("message");
    report.setAuthor("coach");
    Chat chat=new Chat();
    chat.setUser(1234L);
    chat.setCoach(4567L);
    LocalDateTime localDateTime= LocalDate.now().atStartOfDay();
    chat.setLocalDateTime(localDateTime);
    Plan plan=new Plan();
    plan.setCoach(coach);
    plan.setPlanName("planName");

    when(planRepository.findAll()).thenReturn(List.of(plan));
    when(reportRepository.findAll()).thenReturn(List.of(report));
    when(chatRepository.findAll()).thenReturn(List.of(chat));


    service.deleteCoach(4567L);

    verify(planRepository).deleteAll(Mockito.anyList());
    verify(reportRepository).deleteAll(Mockito.anyList());
    verify(chatRepository).deleteAll(Mockito.anyList());
    verify(coachRepository).deleteById(4567L);
}


}
