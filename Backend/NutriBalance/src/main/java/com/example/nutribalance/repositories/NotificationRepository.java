package com.example.nutribalance.repositories;

import com.example.nutribalance.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    @Query(value = "select * from notification where coach_id = ?1", nativeQuery = true)
    List<Notification> findByCoach_Coach_id(Long coach_id);
    @Query(value = "select * from notification where user_id = ?1", nativeQuery = true)
    List<Notification> findByUser_User_id(Long user_id);
}