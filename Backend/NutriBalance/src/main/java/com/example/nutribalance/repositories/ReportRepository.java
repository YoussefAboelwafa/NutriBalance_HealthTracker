package com.example.nutribalance.repositories;

import com.example.nutribalance.entities.Report;
import com.example.nutribalance.entities.ReportId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, ReportId> {

}
