package com.example.nutribalance.Services;

import com.example.nutribalance.Entities.Coach;
import com.example.nutribalance.Entities.ResetPassword;
import com.example.nutribalance.Entities.User;
import com.example.nutribalance.dto.LoginRequest;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

public interface Iservice {
    //todo: add methods
    //------------------------------coach methods--------------------------------
    Coach savecoach(Coach coach);

    User usersignin(String email, String password);

    List<Coach> get_waiting_coaches();

    String deletecoach(Long id);

    Coach approvecoach(Long id);


    //------------------------------user methods--------------------------------
    User saveuser(User user);

    void sendForgetPasswordEmail(ResetPassword resetPassword) throws UnsupportedEncodingException, MessagingException;

    String findByEmailRole(String email, String role);

    void create_reset_password(ResetPassword resetPassword);

    ResetPassword get_reset_password(String email);

    void resetPassword(LoginRequest loginRequest, String role);
}
