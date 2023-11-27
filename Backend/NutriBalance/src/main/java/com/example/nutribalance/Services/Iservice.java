package com.example.nutribalance.Services;

import com.example.nutribalance.Entities.Coach;

import java.util.List;
import java.util.Optional;

public interface Iservice {
   //todo: add methods
    //------------------------------coach methods--------------------------------
    Coach savecoach(Coach coach);
    List<Coach> get_waiting_coaches();
}
