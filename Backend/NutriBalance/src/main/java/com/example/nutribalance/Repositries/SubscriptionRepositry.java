package com.example.nutribalance.Repositries;

import com.example.nutribalance.Entities.Coach;
import com.example.nutribalance.Entities.Subscription;
import com.example.nutribalance.Entities.SubscriptionId;
import com.example.nutribalance.Entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubscriptionRepositry extends JpaRepository<Subscription, SubscriptionId> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Subscription s WHERE s.user = :user")
    void deleteByUser(@Param("user") User user);
    @Modifying
    @Transactional
    @Query("DELETE FROM Subscription s WHERE s.coach = :coach")
    void deleteByCoach(@Param("coach") Coach coach);

}
