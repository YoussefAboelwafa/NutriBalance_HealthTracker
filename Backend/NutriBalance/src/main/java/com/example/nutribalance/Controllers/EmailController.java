package com.example.nutribalance.Controllers;
import com.example.nutribalance.Mails.EmailDetails;
import com.example.nutribalance.Mails.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired private EmailService emailService;
    @PostMapping("/sendMail")
    public ResponseEntity<?>
    sendMail(@RequestBody EmailDetails details)
    {
        return ResponseEntity.ok(emailService.sendSimpleMail(details));
    }
    @PostMapping("/sendMemeMail")
    public ResponseEntity<?> sendMemeMail(@RequestBody EmailDetails details) {
        return ResponseEntity.ok(emailService.sendMemeMail(details));
    }
}
