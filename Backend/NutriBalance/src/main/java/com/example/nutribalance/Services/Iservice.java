package com.example.nutribalance.services;

import com.example.nutribalance.entities.*;
import com.example.nutribalance.dto.ChatDto;
import com.example.nutribalance.dto.LoginRequest;
import com.example.nutribalance.dto.NotificationDto;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

public interface IService {


    //------------------------------password methods--------------------------------
    void sendForgetPasswordEmail(ResetPassword resetPassword) throws UnsupportedEncodingException, MessagingException;
    void createResetPassword(ResetPassword resetPassword);
    ResetPassword getResetPassword(String email);

    void resetPassword(LoginRequest loginRequest, String role);
    String changePassword(String email, String oldPassword, String password, String role);
    boolean verify(String code);


    //------------------------------coach methods--------------------------------
    Coach saveCoach(Coach coach);
    String deletecoach(Long id);
    List<FoodCalorie> getFoodCalorie();
    Coach approvecoach(Long id);
    Coach coachSignIn(String email, String pass);
    List<Coach> getWaitingCoaches();
    Coach addImageToCoach(String Email, MultipartFile image );
    List<User> getSubscribedUsers(Long coach_id);
    User updateComment(String comment, Long user_id);
    Coach updateCoach(Coach coach);
    Coach updateCoachCV(String email, byte[] bytes);
    Coach registerCoach(Coach coach);
    List<Coach> getCoaches();
    void deleteCoach(Long id);



    //------------------------------plan methods--------------------------------
    List<Plan> getPlans(Long coachId);
    Plan saveplan(Plan plan);
    Plan updatePlan(Plan plan);
   List<Plan> getAllPlans();




    //------------------------------user methods--------------------------------
    User getUser(Long id);
    User saveuser(User user);
    User updateUser(User user);
    User userSignIn(String email, String password);
    User addImageToUser(String Email, MultipartFile image );
    String findByEmailRole(String email, String role);
    User subscribeToPlan(String planName, Long user_id);
    String deletePlan(String planName);
    User addWeight(Long id, Double weight, Date date);
    List<Weight> getWeights(Long id);
    User deleteSubscription(Long id);
    void deleteUser(Long id);



    //------------------------------notification methods--------------------------------
    List<NotificationDto> getNotifications(Long id, String role);
    String deleteNotification(Long notificationId);





    //------------------------------chat methods--------------------------------
    Chat saveChat(ChatDto chatDto);
    List<Chat> getUserChats(Long user_id);
    List<Chat> getCoachChats(Long coach_id);
    void deleteChatByUser(Long user_id);
    int getUnseenChats(Long user_id, Long coach_id);
    void setSeen(Long user_id, Long coach_id);



    //------------------------------report methods--------------------------------
    Report addReport(Long user_id,Long coach_id,String message,String author);
    List<Report> getReports();
    void deleteReport(Long user_id,Long coach_id);

}
