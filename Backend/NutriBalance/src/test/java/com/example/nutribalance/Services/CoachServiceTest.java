package com.example.nutribalance.Services;

import com.example.nutribalance.Entities.Coach;
import com.example.nutribalance.Mails.EmailService;
import com.example.nutribalance.Repositries.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;


@ContextConfiguration(classes = {Service.class})
@ExtendWith(SpringExtension.class)
public class CoachServiceTest {
    @InjectMocks
    private Service service;

    @MockBean
    private CoachRepositry coachRepo;


    @MockBean
    private UserRepositry userRepositry;

    @MockBean
    private ResetPasswordRepository resetPasswordRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @Qualifier("email2Sender")
    @MockBean
    private JavaMailSender mailSender;
    @MockBean
    private EmailService emailService;
    @MockBean
    private PlanRepositry planRepositry;
    @MockBean
    private FoodCalorieRepositry foodCalorieRepositry;
    @MockBean
    private ChatRepository chatRepository;
    @MockBean
    private NotificationRepository notificationRepository;
    @MockBean
    private WeightRepositry weightRepositry;
    @MockBean
    private ReportRepositry reportRepositry;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testUpdateCoachCV() throws Exception {
        Coach coach = new Coach();
        coach.setCoach_id(1L);
        coach.setUsername("janedoe");
        coach.setEmail("jane.doe@example.org");
        when(coachRepo.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(coach));
        when(coachRepo.save(Mockito.<Coach>any())).thenReturn(coach);
        Coach result = service.updateCoachCV("jane.doe@example.org", new byte[0]);
        assertEquals(coach, result);
    }
    @Test
    public void testUpdateCoachCV2() throws Exception {
        Coach coach = new Coach();
        coach.setCoach_id(1L);
        coach.setUsername("janedoe");
        coach.setEmail("jane.doe1@example.org");
        when(coachRepo.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        when(coachRepo.save(Mockito.<Coach>any())).thenReturn(coach);
        Coach result = service.updateCoachCV("jane.doe@example.org", new byte[0]);
        assertNull(result);
    }

    @Test
    public void testAddImageToCoach() throws Exception {
        Coach coach = new Coach();
        coach.setCoach_id(1L);
        coach.setUsername("janedoe");
        when(coachRepo.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(coach));
        MultipartFile file = new MockMultipartFile("file", "image.jpg", "image/jpeg", "image content".getBytes());
        when(coachRepo.save(Mockito.<Coach>any())).thenReturn(coach);
        Coach result = service.addImageToCoach("jane.doe@example.org", file);
        assertEquals(coach, result);
    }

    @Test
    public void testUpdateCoach() {
        Coach coach = new Coach();
        coach.setCoach_id(1L);
        coach.setUsername("janedoe");
        when(coachRepo.findById(Mockito.<Long>any())).thenReturn(Optional.of(coach));
        Coach newCoach = new Coach();
        newCoach.setCoach_id(1L);
        newCoach.setUsername("janedoe");
        newCoach.setDescription("foo");
        when(coachRepo.save(Mockito.<Coach>any())).thenReturn(newCoach);
        coach.setDescription("foo");
        Coach result = service.updateCoach(coach);
        assertEquals(coach, result);
    }

}

