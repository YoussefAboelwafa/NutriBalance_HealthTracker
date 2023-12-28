package com.example.nutribalance.Repositries;

import com.example.nutribalance.Entities.Chat;
import com.example.nutribalance.Entities.ChatID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, ChatID> {
    List<Chat> findByUserOrderByLocalDateTimeAsc(Long user);
    List<Chat> findByCoachOrderByLocalDateTimeAsc(Long coach);
    List<Chat> findByUserAndCoachOrderByLocalDateTimeAsc(Long user, Long coach);
    void deleteByUser(Long userId);
}
