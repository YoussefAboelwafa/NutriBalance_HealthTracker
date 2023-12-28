package com.example.nutribalance.Controllers;

import static org.mockito.Mockito.when;

import com.example.nutribalance.Mails.EmailDetails;
import com.example.nutribalance.Mails.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {EmailController.class})
@ExtendWith(SpringExtension.class)
class EmailControllerTest {
    @Autowired
    private EmailController emailController;

    @MockBean
    private EmailService emailService;

    /**
     * Method under test:  {@link EmailController#sendMail(EmailDetails)}
     */
    @Test
    void testSendMail() throws Exception {
        when(emailService.sendSimpleMail(Mockito.<EmailDetails>any())).thenReturn("Send Simple Mail");

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setAttachment("Attachment");
        emailDetails.setMsgBody("Not all who wander are lost");
        emailDetails.setRecipient("Recipient");
        emailDetails.setSubject("Hello from the Dreaming Spires");
        String content = (new ObjectMapper()).writeValueAsString(emailDetails);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/sendMail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(emailController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Send Simple Mail"));
    }

    /**
     * Method under test:  {@link EmailController#sendMemeMail(EmailDetails)}
     */
    @Test
    void testSendMemeMail() throws Exception {
        when(emailService.sendMemeMail(Mockito.<EmailDetails>any())).thenReturn("Send Meme Mail");

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setAttachment("Attachment");
        emailDetails.setMsgBody("Not all who wander are lost");
        emailDetails.setRecipient("Recipient");
        emailDetails.setSubject("Hello from the Dreaming Spires");
        String content = (new ObjectMapper()).writeValueAsString(emailDetails);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/sendMemeMail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(emailController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Send Meme Mail"));
    }
}
