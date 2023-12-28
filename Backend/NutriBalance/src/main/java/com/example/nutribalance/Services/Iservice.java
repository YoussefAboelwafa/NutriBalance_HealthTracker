package com.example.nutribalance.Services;

import com.example.nutribalance.Entities.*;
import com.example.nutribalance.dto.ChatDto;
import com.example.nutribalance.dto.LoginRequest;
import com.example.nutribalance.dto.NotificationDto;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

public interface Iservice {
    //todo: add methods
    //------------------------------coach methods--------------------------------
    Coach savecoach(Coach coach);
    User usersignin(String email, String password);
    List<FoodCalorie> getFoodCalorie();

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


    String changePassword(String email, String oldPassword, String password, String role);

    Coach updateCoachCV(String email, byte[] bytes);

   List<Plan> getallplans();


    List<Plan> getPlans(Long coachId);

    Plan updatePlan(Plan plan);

    String deletePlan(String planName);

    boolean verify(String code);

    Coach registerCoach(Coach coach);
    User AddWeight(Long id, Double weight, Date date);
    List<Weight> GetWeights(Long id);
    User deletesubscription(Long id);
    List<NotificationDto> getNotifications(Long id, String role);
    String deleteNotification(Long notificationId);
    User getUser(Long id);

    Chat savechat(ChatDto chatDto);
    List<Chat> getUserChats(Long user_id);
    List<Chat> getCoachChats(Long coach_id);
    void deleteChatByUser(Long user_id);
  
    List<Coach> getCoaches();
    Report addReport(Long user_id,Long coach_id,String message,String author);
    List<Report> getReports();

    void deleteUser(Long id);
    void deleteCoach(Long id);

    void deleteReport(Long user_id,Long coach_id);

    int getUnseenChats(Long user_id, Long coach_id);
    void setSeen(Long user_id, Long coach_id);

}
