package com.example.nutribalance.Services;

import com.example.nutribalance.Entities.*;
import com.example.nutribalance.dto.LoginRequest;
import com.example.nutribalance.dto.PlanDto;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Iservice {
    //todo: add methods
    //------------------------------coach methods--------------------------------
    Coach savecoach(Coach coach);
    User usersignin(String email, String password);


    User updateUser(User user);

    User addImageToUser(String Email, MultipartFile image );

    List<Coach> get_waiting_coaches();

    String deletecoach(Long id);

    Coach approvecoach(Long id);
    Coach coachsignin(String email, String pass);


    //------------------------------user methods--------------------------------
    User saveuser(User user);

    void sendForgetPasswordEmail(ResetPassword resetPassword) throws UnsupportedEncodingException, MessagingException;

    String findByEmailRole(String email, String role);

    void create_reset_password(ResetPassword resetPassword);

    ResetPassword get_reset_password(String email);

    void resetPassword(LoginRequest loginRequest, String role);
    Plan saveplan(Plan plan);

    User subscribe_to_plan(String planName, Long user_id);
    List<User> get_subscribed_users(Long coach_id);
    User update_comment(String comment, Long user_id);

    Coach addImageToCoach(String Email, MultipartFile image );

    Coach updateCoach(Coach coach);



    Coach updateCoachCV(String email, byte[] bytes);

   List<Plan> getallplans();


    List<Plan> getPlans(Long coachId);

    Plan updatePlan(Plan plan);

    String deletePlan(String planName);

    boolean verify(String code);

    Coach registerCoach(Coach coach);

}
