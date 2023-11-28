package com.example.nutribalance.Mails;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig{

    @Value("${email1.host}")
    private String email1Host;

    @Value("${email1.port}")
    private int email1Port;

    @Value("${email1.username}")
    private String email1Username;

    @Value("${email1.password}")
    private String email1Password;

    @Value("${email2.host}")
    private String email2Host;

    @Value("${email2.port}")
    private int email2Port;

    @Value("${email2.username}")
    private String email2Username;

    @Value("${email2.password}")
    private String email2Password;

    @Bean(name = "email1Sender")
    public JavaMailSender email1Sender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(email1Host);
        mailSender.setPort(email1Port);
        mailSender.setUsername(email1Username);
        mailSender.setPassword(email1Password);
        mailSender.setDefaultEncoding("UTF-8");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
//        props.put("mail.smtp.connectiontimeout", 800000);
//        props.put("mail.debug", "true");
        return mailSender;
    }

    @Bean(name = "email2Sender")
    public JavaMailSender email2Sender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(email2Host);
        mailSender.setPort(email2Port);
        mailSender.setUsername(email2Username);
        mailSender.setPassword(email2Password);
        mailSender.setDefaultEncoding("UTF-8");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        //increase the timeout to 30 seconds
//        props.put("mail.smtp.connectiontimeout", 800000);
//        props.put("mail.debug", "true");
        return mailSender;
    }
}
