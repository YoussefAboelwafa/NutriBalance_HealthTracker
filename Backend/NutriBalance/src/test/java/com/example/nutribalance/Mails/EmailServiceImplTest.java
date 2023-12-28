package com.example.nutribalance.Mails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmailServiceImpl.class})
@ExtendWith(SpringExtension.class)
class EmailServiceImplTest {
    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @MockBean(name = "email2Sender")
    private JavaMailSender javaMailSender;

    @MockBean(name = "email1Sender")
    private MailSender mailSender;

    /**
     * Method under test: {@link EmailServiceImpl#sendSimpleMail(EmailDetails)}
     */
    @Test
    void testSendSimpleMail() throws MailException {
        doNothing().when(mailSender).send(Mockito.<SimpleMailMessage>any());
        String actualSendSimpleMailResult = emailServiceImpl.sendSimpleMail(
                new EmailDetails("Recipient", "Not all who wander are lost", "Hello from the Dreaming Spires", "Attachment"));
        verify(mailSender).send(Mockito.<SimpleMailMessage>any());
        assertEquals("done", actualSendSimpleMailResult);
    }

    /**
     * Method under test: {@link EmailServiceImpl#sendSimpleMail(EmailDetails)}
     */
    @Test
    void testSendSimpleMail2() {
        assertEquals("Failed to send mail after multiple attempts.", emailServiceImpl.sendSimpleMail(null));
    }

    /**
     * Method under test: {@link EmailServiceImpl#sendMemeMail(EmailDetails)}
     */
    @Test
    void testSendMemeMail() throws MailException {
        doNothing().when(javaMailSender).send(Mockito.<MimeMessage>any());
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
        String actualSendMemeMailResult = emailServiceImpl.sendMemeMail(
                new EmailDetails("Recipient", "Not all who wander are lost", "Hello from the Dreaming Spires", "Attachment"));
        verify(javaMailSender).createMimeMessage();
        verify(javaMailSender).send(Mockito.<MimeMessage>any());
        assertEquals("done", actualSendMemeMailResult);
    }
}
