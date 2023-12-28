package com.example.nutribalance.mails;


public interface EmailService {
    String sendSimpleMail(EmailDetails details);
    String sendMemeMail(EmailDetails details);

}