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
        // To send a simple email
    public String sendSimpleMail(EmailDetails details)
    {
        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();
            // Setting up necessary details
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            System.out.println(e.getMessage()

            );
            return "Error while Sending Mail";
        }
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
