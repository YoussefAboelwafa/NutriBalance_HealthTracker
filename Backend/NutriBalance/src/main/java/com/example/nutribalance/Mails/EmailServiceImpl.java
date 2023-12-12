// Java Program to Illustrate Creation Of
// Service implementation class
package com.example.nutribalance.Mails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

// Annotation
@Service
public class EmailServiceImpl implements EmailService {



    private final JavaMailSender javaMailSender;


    @Autowired
    public EmailServiceImpl(@Qualifier("email2Sender") JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


        // Method 1
        public String sendSimpleMail(EmailDetails details) {
            int maxRetries = 3;
            int currentAttempt = 0;

            do {
                try {
                    SimpleMailMessage mailMessage = new SimpleMailMessage();
                    mailMessage.setTo(details.getRecipient());
                    mailMessage.setText(details.getMsgBody());
                    mailMessage.setSubject(details.getSubject());

                    javaMailSender.send(mailMessage);

                    return "Mail Sent Successfully...";
                } catch (Exception e) {
                    System.out.println("Error while sending mail: " + e.getMessage());
                    currentAttempt++;

                    if (currentAttempt >= maxRetries) {
                        return "Failed to send mail after multiple attempts.";
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            } while (true);
        }
        @Override
        public String sendMemeMail(EmailDetails details) {
            int maxRetries = 3;
            int currentAttempt = 0;

            do {
                try {
                    MimeMessage message = javaMailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(message, true);
                    helper.setTo(details.getRecipient());
                    helper.setText(details.getMsgBody(), true);
                    helper.setSubject(details.getSubject());
                    javaMailSender.send(message);
                    return "Mail Sent Successfully...";
                } catch (Exception e) {
                    System.out.println("Error while sending mail: " + e.getMessage());
                    currentAttempt++;

                    if (currentAttempt >= maxRetries) {
                        return "Failed to send mail after multiple attempts.";
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            } while (true);
        }
}
