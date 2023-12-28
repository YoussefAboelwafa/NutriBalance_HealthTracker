package com.example.nutribalance.repositories;

import com.example.nutribalance.entities.Chat;
import com.example.nutribalance.entities.ChatID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, ChatID> {
    List<Chat> findByUserOrderByLocalDateTimeAsc(Long user);
    List<Chat> findByCoachOrderByLocalDateTimeAsc(Long coach);
    List<Chat> findByUserAndCoachOrderByLocalDateTimeAsc(Long user, Long coach);
    void deleteByUser(Long userId);
}
