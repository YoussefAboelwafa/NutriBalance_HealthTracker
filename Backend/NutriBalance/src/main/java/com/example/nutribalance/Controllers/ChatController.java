package com.example.nutribalance.controllers;
import com.example.nutribalance.entities.Chat;
import com.example.nutribalance.services.IService;
import com.example.nutribalance.dto.ChatDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private IService iservice;
    @PostMapping("/save")
 public Chat saveChat(@RequestBody ChatDto chatDto){
        return iservice.saveChat(chatDto);
    }
    @GetMapping("/get_ordered_user_chats")
    public List<Chat> getUserChat(@Param("user_id") Long user_id){
        return iservice.getUserChats(user_id);
    }
    @GetMapping("/get_ordered_coach_chats")
    public List<Chat> getCoachChat(@Param("coach_id") Long coach_id){
        return iservice.getCoachChats(coach_id);
    }
    @DeleteMapping("/delete")
    public void deleteChat(@Param("user_id") Long user_id){
         iservice.deleteChatByUser(user_id);
    }
    @GetMapping("/get_unseen_chats")
    public int getUnseenChats(@Param("user_id") Long user_id, @Param("coach_id") Long coach_id){
        return iservice.getUnseenChats(user_id, coach_id);
    }
    @GetMapping("/set_seen")
    public void updateSeen(@Param("user_id") Long user_id, @Param("coach_id") Long coach_id){
        iservice.setSeen(user_id, coach_id);
    }
}
