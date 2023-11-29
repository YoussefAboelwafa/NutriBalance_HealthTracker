package com.example.nutribalance.Services;

import com.example.nutribalance.Entities.Coach;
import com.example.nutribalance.Entities.User;

import java.util.List;
import java.util.Optional;

public interface Iservice {
   //todo: add methods
    //------------------------------coach methods--------------------------------
    Coach savecoach(Coach coach);

    List<Coach> get_waiting_coaches();
    String deletecoach(Long id);
    Coach approvecoach(Long id);
    Coach coachsignin(String email, String pass);

    //------------------------------user methods--------------------------------
    User saveuser(User user);

}
