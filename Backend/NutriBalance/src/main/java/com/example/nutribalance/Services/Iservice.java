package com.example.nutribalance.Services;

import com.example.nutribalance.Entities.Coach;
import com.example.nutribalance.Entities.User;

import java.util.Optional;

public interface Iservice {
   //todo: add methods
    //------------------------------coach methods--------------------------------
    Coach savecoach(Coach coach);

    //------------------------------user methods--------------------------------
    User saveuser(User user);
}
