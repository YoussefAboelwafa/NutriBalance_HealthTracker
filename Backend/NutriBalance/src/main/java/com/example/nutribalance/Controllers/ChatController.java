package com.example.nutribalance.Controllers;
import com.example.nutribalance.Entities.Chat;
import com.example.nutribalance.Services.Iservice;
import com.example.nutribalance.dto.ChatDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private Iservice iservice;
    @PostMapping("/save")
 public Chat savechat(@RequestBody ChatDto chatDto){
        return iservice.savechat(chatDto);
    }
    @GetMapping("/get_ordered_user_chats")
    public List<Chat> get_user_chat(@Param("user_id") Long user_id){
        return iservice.getUserChats(user_id);
    }
    @GetMapping("/get_ordered_coach_chats")
    public List<Chat> get_coach_chat(@Param("coach_id") Long coach_id){
        return iservice.getCoachChats(coach_id);
    }
    @DeleteMapping("/delete")
    public void deletechat(@Param("user_id") Long user_id){
         iservice.deleteChatByUser(user_id);
    }
    @GetMapping("/get_unseen_chats")
    public int get_unseen_chats(@Param("user_id") Long user_id, @Param("coach_id") Long coach_id){
        return iservice.getUnseenChats(user_id, coach_id);
    }
    @GetMapping("/set_seen")
    public void update_seen(@Param("user_id") Long user_id, @Param("coach_id") Long coach_id){
        iservice.setSeen(user_id, coach_id);
    }
}
