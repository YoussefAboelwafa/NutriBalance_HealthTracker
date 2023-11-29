package com.example.nutribalance.Services;

import com.example.nutribalance.Entities.Coach;
import com.example.nutribalance.Entities.ResetPassword;
import com.example.nutribalance.Entities.User;
import com.example.nutribalance.Mails.EmailDetails;
import com.example.nutribalance.Mails.EmailService;
import com.example.nutribalance.Repositries.CoachRepositry;
import com.example.nutribalance.Repositries.ResetPasswordRepository;
import com.example.nutribalance.Repositries.SubscriptionRepositry;
import com.example.nutribalance.Repositries.UserRepositry;
import com.example.nutribalance.dto.LoginRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class Service implements Iservice{
    @Autowired
    private UserRepositry userRepo;
    @Autowired
    private CoachRepositry coachRepo;
    @Autowired
    private SubscriptionRepositry subscriptionRepo;
    @Autowired
    private ResetPasswordRepository resetPasswordRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Qualifier("email2Sender")
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Coach savecoach(Coach coach) {
        Optional<Coach> old_coach_1= coachRepo.findByEmail(coach.getEmail());
        Optional<Coach> old_coach_2= coachRepo.findByUsername(coach.getUsername());
        if(old_coach_1.isPresent() || old_coach_2.isPresent()){
            return null;
        }
        return coachRepo.save(coach);
    }

    @Override
    public List<Coach> get_waiting_coaches() {
        return coachRepo.findByisapproved(0);
    }

    @Override
    public String deletecoach(Long id) {
        coachRepo.deleteById(id);
        return "coach deleted";
    }

    @Override
    public Coach approvecoach(Long id) {
        Optional<Coach> coach = coachRepo.findById(id);
        if(coach.isPresent()){
            coach.get().setIsapproved(1);
            return coachRepo.save(coach.get());
        }
        return null;
    }

    @Override
    public User saveuser(User user) {
        Optional<User> old_user_1= userRepo.findByEmail(user.getEmail());
        Optional<User> old_user_2= userRepo.findByUsername(user.getUsername());
        if(old_user_1.isPresent() || old_user_2.isPresent()){
            return null;
        }
        return userRepo.save(user);
    }
    @Override
    public User usersignin(String email, String password){
        Optional<User> user=userRepo.f0indByEmail(email);
        if(user.isPresent() && user.get().getPassword().equals(password)) return user.get();
        return null;
    }

    @Override
    public String findByEmailRole(String email, String role){
        if(role.equals("user")){
            Optional<User> user=userRepo.findByEmail(email);
            if(user.isPresent()) return user.get().getUsername();
        }
        else if(role.equals("coach")){
            Optional<Coach> coach=coachRepo.findByEmail(email);
            if(coach.isPresent()) return coach.get().getUsername();
        }
        return null;
    }

    @Override
    public void sendForgetPasswordEmail(ResetPassword resetPassword) throws UnsupportedEncodingException, MessagingException {
        String toAddress = resetPassword.getEmail();
        String fromAddress = "nutribalance553@gmail.com";
        String senderName = "NutriBalance team";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Here is the OTP code to forget password :<br>"
                + "<h3>[[URL]]</h3>"
                + "Thank you,<br>"
                + "NutriBalance team";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", resetPassword.getUsername());
        String verifyURL = resetPassword.getToken();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
        System.out.println("Email has been sent");

    }
    @Override
    public void create_reset_password(ResetPassword resetPassword){
        Optional<ResetPassword> old_reset_password= Optional.ofNullable(resetPasswordRepository.findByEmail(resetPassword.getEmail()));
        old_reset_password.ifPresent(password -> resetPasswordRepository.deleteById(password.getId()));
        resetPasswordRepository.save(resetPassword);
    }
    @Override
    public ResetPassword get_reset_password(String email){
        Optional<ResetPassword> resetPassword= Optional.ofNullable(resetPasswordRepository.findByEmail(email));
        return resetPassword.orElse(null);
    }
    @Override
    public void resetPassword(LoginRequest loginRequest,String role){
        if(role.equals("user")){
            Optional<User> user=userRepo.findByEmail(loginRequest.getEmail());
            if(user.isPresent()){
                user.get().setPassword(passwordEncoder.encode(loginRequest.getPassword()));
                userRepo.save(user.get());
                resetPasswordRepository.deleteById(resetPasswordRepository.findByEmail(loginRequest.getEmail()).getId());
            }
        }
        else if(role.equals("coach")){
            Optional<Coach> coach=coachRepo.findByEmail(loginRequest.getEmail());
            if(coach.isPresent()){
                coach.get().setPassword(passwordEncoder.encode(loginRequest.getPassword()));
                coachRepo.save(coach.get());
                resetPasswordRepository.deleteById(resetPasswordRepository.findByEmail(loginRequest.getEmail()).getId());
            }
        }
    public Coach coachsignin(String email, String pass) {
        Optional<Coach> coach = coachRepo.findByEmail(email);
        Coach coach1 = coach.orElse(null);
        if (coach.isPresent()) {
            if (coach1.getIsapproved() == 1) {
                if (coach1.getPassword().equals(pass)) {
                    return coach1;
                } else {
                    return null;
                }
            }
        }
        return null;
    }
}


