package com.example.nutribalance.Mails;

// Importing required classes

// Interface
public interface EmailService {
    String sendSimpleMail(EmailDetails details);
    String sendMemeMail(EmailDetails details);

}