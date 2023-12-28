package com.example.nutribalance.mails;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {


    private final JavaMailSender javaMailSender;

    @Qualifier("email1Sender")
    @Autowired
    MailSender mailSender;


    @Autowired
    public EmailServiceImpl(@Qualifier("email2Sender") JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String sendSimpleMail(EmailDetails details) {
        int maxRetries = 3;
        int currentAttempt = 0;

        do {
            try {
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(details.getRecipient());
                mailMessage.setText(details.getMsgBody());
                mailMessage.setSubject(details.getSubject());
                mailSender.send(mailMessage);
                return "done";
            } catch (Exception e) {
                System.out.println("Error while sending mail: " + e.getMessage());
                currentAttempt++;

                if (currentAttempt >= maxRetries) {
                    return "Failed to send mail after multiple attempts.";
                }

                try {
                    Thread.sleep(10);
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
                return "done";
            } catch (Exception e) {
                System.out.println("Error while sending mail: " + e.getMessage());
                currentAttempt++;

                if (currentAttempt >= maxRetries) {
                    return "Failed to send mail after multiple attempts.";
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        } while (true);
    }
}
