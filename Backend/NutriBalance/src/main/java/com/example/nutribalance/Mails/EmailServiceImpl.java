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

//    @Value("${email1.username}") private String sender;

        // Method 1
        public String sendSimpleMail(EmailDetails details) {
            int maxRetries = 3; // Set the maximum number of retries
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

                    // Introduce a delay before retrying (e.g., 5 seconds)
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            } while (true);
        }

    // Method 2
    // To send an email with attachment
    public String
    sendMailWithAttachment(EmailDetails details)
    {
        return "Not implemented";
        // Creating a mime message
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper;
//
//        try {
//
//            // Setting multipart as true for attachments to
//            // be send
//            mimeMessageHelper
//                    = new MimeMessageHelper(mimeMessage, true);
//            mimeMessageHelper.setTo(sender);
//            mimeMessageHelper.setText(details.getMsgBody());
//            mimeMessageHelper.setSubject(
//                    details.getSubject());
//
//            // Adding the attachment
//            FileSystemResource file
//                    = new FileSystemResource(
//                    new File(details.getAttachment()));
//
//            mimeMessageHelper.addAttachment(
//                    file.getFilename(), file);
//
//            // Sending the mail
//            javaMailSender.send(mimeMessage);
//            return "Mail sent Successfully";
//        }
//
//        // Catch block to handle MessagingException
//        catch (MessagingException e) {
//
//            // Display message when exception occurred
//            return "Error while sending mail!!!";
//        }
    }
}
