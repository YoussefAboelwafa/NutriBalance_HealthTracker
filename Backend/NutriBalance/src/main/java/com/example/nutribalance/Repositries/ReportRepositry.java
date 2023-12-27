package com.example.nutribalance.Repositries;

import com.example.nutribalance.Entities.Report;
import com.example.nutribalance.Entities.ReportId;
import com.example.nutribalance.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepositry extends JpaRepository<Report, ReportId> {

}
