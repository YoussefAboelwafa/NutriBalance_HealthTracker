package com.example.nutribalance.Services;

import com.example.nutribalance.Entities.*;
import com.example.nutribalance.Mails.EmailDetails;
import com.example.nutribalance.Mails.EmailService;
import com.example.nutribalance.Repositries.*;
import com.example.nutribalance.dto.LoginRequest;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class Service implements Iservice {
    @Autowired
    private UserRepositry userRepo;
    @Autowired
    private CoachRepositry coachRepo;
    @Autowired
    private ResetPasswordRepository resetPasswordRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Qualifier("email2Sender")
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PlanRepositry planRepositry;
    @Autowired
    private FoodCalorieRepositry foodCalorieRepositry;
    @Override
    public Coach savecoach(Coach coach) {
        return coachRepo.save(coach);
    }

    @Override
    public Coach updateCoach(Coach coach) {
        Optional<Coach> existingCoachOpt = coachRepo.findById(coach.getCoach_id());
        if (existingCoachOpt.isEmpty()) {
            return null;
        }
        Coach existingCoach = existingCoachOpt.get();
        existingCoach.setDescription(coach.getDescription());
        existingCoach.setPrice(coach.getPrice());
        existingCoach.setContact_number(coach.getContact_number());
        existingCoach.setAddress(coach.getAddress());
        return coachRepo.save(existingCoach);

    }

    @Override
    public Coach updateCoachCV(String email, byte[] bytes) {
        Optional<Coach> coach = coachRepo.findByEmail(email);
        if (coach.isPresent()) {
            coach.get().setCv(bytes);
            return coachRepo.save(coach.get());
        }
        return null;
    }

    @Override

    public List<Plan> getallplans() {
        return planRepositry.findAll();
    }
    public List<Plan> getPlans(Long coachId) {
        Optional<Coach> coach = coachRepo.findById(coachId);
        return coach.map(value -> (List<Plan>) value.getPlans()).orElse(null);
    }

    @Override
    public Plan updatePlan(Plan plan) {
        Optional<Plan> existingPlanOpt = planRepositry.findById(plan.getPlanName());
        if (existingPlanOpt.isEmpty()) {
            return null;
        }
        Plan existingPlan = existingPlanOpt.get();
        existingPlan.setDescription(plan.getDescription());
        existingPlan.setGoal(plan.getGoal());
        return planRepositry.save(existingPlan);
    }

    @Override
    public String deletePlan(String planName) {
        Plan plan = planRepositry.findById(planName).orElse(null);
        assert plan != null;
        if (!plan.getUsers().isEmpty()) {
            return "There are users subscribed to this plan";
        }
        planRepositry.deleteById(planName);
        return "Plan deleted";

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
        if (coach.isPresent()) {
            coach.get().setIsapproved(1);
            return coachRepo.save(coach.get());
        }
        return null;
    }


    @Override
    public User saveuser(User user) {
        Optional<User> old_user_1 = userRepo.findByEmail(user.getEmail());
        if (old_user_1.isPresent()) {
            return null;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        sendVerificationMail(user, "user");
        return userRepo.save(user);
    }

    @Override
    public User updateUser(User user) {
        Optional<User> existingUserOpt = userRepo.findById(user.getUser_id());
        if (existingUserOpt.isEmpty()) {
            return null;
        }
        return userRepo.save(user);
    }

    @Override
    public User addImageToUser(String Email, MultipartFile image ){
        try {
            User user = userRepo.findByEmail(Email).orElse(null);
            if (user == null) {
                return null;
            }
            user.setImage(image.getBytes());
            userRepo.save(user);
            return user;
        } catch (Exception e) {
            throw new RuntimeException("Error while changing user image", e);
        }
    }
    public boolean verify(String verificationCode) {
        ResetPassword resetPassword = resetPasswordRepository.findByToken(verificationCode);
        if (resetPassword == null ) {
            return false;
        }
        User user = userRepo.findByEmail(resetPassword.getEmail()).orElse(null);

        String role = user == null ? "coach" : "user";
        if (role.equals("user")) {
            user.setEnabled(true);
            userRepo.save(user);
        }
        else {
            Coach coach = coachRepo.findByEmail(resetPassword.getEmail()).orElse(null);
            if (coach == null) {
                return false;
            }
            coach.setEnabled(true);
            EmailDetails details = getApprovalEmailDetails(coach);
            emailService.sendSimpleMail(details);
            coachRepo.save(coach);
        }
        resetPasswordRepository.deleteById(resetPassword.getId());
        return true;
    }

    private static EmailDetails getApprovalEmailDetails(Coach coach) {
        EmailDetails details = new EmailDetails();
        details.setRecipient(coach.getEmail());
        details.setSubject("Waiting for approval Email");
        details.setMsgBody("Dear " + coach.getUsername() + ",\n" +
                "Thank you for your interest in joining our team. We have received your application for the position of Nutrition Coach. We are currently reviewing all applications and will be in touch with those who we feel are best suited for the position.\n" +
                "Thank you again for your interest in working with us. We wish you the best of luck with your job search.\n" +
                "Sincerely,\n" +
                "NutriBalance Team");
        return details;
    }

    @Override
    public Coach registerCoach(Coach coach) {
        Optional<Coach> old_coach_1 = coachRepo.findByEmail(coach.getEmail());
        if (old_coach_1.isPresent()) {
            return null;
        }
        coach.setPassword(passwordEncoder.encode(coach.getPassword()));
        sendVerificationMail(coach, "coach");
        EmailDetails details = new EmailDetails();
        details.setRecipient(coach.getEmail());
        return coachRepo.save(coach);
    }


    private void sendVerificationMail(Object user, String role) {
       Optional<ResetPassword> old_reset_password = Optional.ofNullable(resetPasswordRepository.findByEmail((role=="user")? ((User)user).getEmail():((Coach)user).getEmail()));
       old_reset_password.ifPresent(password -> resetPasswordRepository.deleteById(password.getId()));
        if (role.equals("user")) {
            User user1 = (User) user;
            ResetPassword resetPassword = new ResetPassword();
            resetPassword.setEmail(user1.getEmail());
            resetPassword.setUsername(user1.getUsername());
            String token = RandomString.make(64);
            resetPassword.setToken(token);
            resetPasswordRepository.save(resetPassword);
            user1.setEnabled(false);
            EmailDetails mail = getEmailDetails(user1.getEmail(), user1.getUsername(), token);
            emailService.sendSimpleMail(mail);

        }
        else if (role.equals("coach")) {
            Coach coach = (Coach) user;
            ResetPassword resetPassword = new ResetPassword();
            resetPassword.setEmail(coach.getEmail());
            resetPassword.setUsername(coach.getUsername());
            String token = RandomString.make(64);
            resetPassword.setToken(token);
            resetPasswordRepository.save(resetPassword);
            coach.setEnabled(false);
            EmailDetails mail = getEmailDetails(coach.getEmail(), coach.getUsername(), token);
            emailService.sendSimpleMail(mail);
        }


    }

    private static EmailDetails getEmailDetails(String email, String name, String token) {
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Thank you for signing up with Galaxy!<br>"
                + " To complete your registration and ensure the security of your account, please click on the following verification link:<br>"
                + "<h3><a href=\"[[URL]]\" onclick=\"return handleLinkClick(event);\">VERIFY</a></h3>"
                + "<br>If you didn't request this confirmation code, please ignore this email. "
                + "<br>Your account will remain inactive until you confirm your email address."
                + "Thank you for choosing NutriBalance!<br>"
                + "NutriBalance team";
        String siteURL = "http://localhost:4200/verify";
        content = content.replace("[[name]]", name);
        String verifyURL = siteURL + "/" + token;
        content = content.replace("[[URL]]", verifyURL);
        String script = "<script type=\"text/javascript\">\n"
                + "    function handleLinkClick(event) {\n"
                + "        event.preventDefault(); // Prevent the link from opening in a new tab\n"
                + "        window.location.href = event.target.getAttribute(\"href\"); // Navigate to the link's URL\n"
                + "        return false;\n"
                + "    }\n"
                + "</script>\n";
        content = script + content;
        EmailDetails mail = new EmailDetails();
        mail.setMsgBody(content);
        mail.setRecipient(email);
        mail.setSubject(subject);
        return mail;
    }


    @Override
    public User usersignin(String email, String password) {
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isPresent() ) {
            if (user.get().isEnabled()) {
                if (passwordEncoder.matches(password, user.get().getPassword())) {
                    return user.get();
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public String findByEmailRole(String email, String role) {
        if (role.equals("user")) {
            Optional<User> user = userRepo.findByEmail(email);
            if (user.isPresent()) return user.get().getUsername();
        } else if (role.equals("coach")) {
            Optional<Coach> coach = coachRepo.findByEmail(email);
            if (coach.isPresent()) return coach.get().getUsername();
        }
        return null;
    }

    @Override
    public void sendForgetPasswordEmail(ResetPassword resetPassword) throws UnsupportedEncodingException, MessagingException {
        String toAddress = resetPassword.getEmail();
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>" + "Here is the OTP code to forget password :<br>" + "<h3>[[URL]]</h3>" + "Thank you,<br>" + "NutriBalance team";


        content = content.replace("[[name]]", resetPassword.getUsername());
        String verifyURL = resetPassword.getToken();

        content = content.replace("[[URL]]", verifyURL);
        EmailDetails mail = new EmailDetails();
        mail.setMsgBody(content);
        mail.setRecipient(toAddress);
        mail.setSubject(subject);
        emailService.sendSimpleMail(mail);

        System.out.println("Email has been sent");

    }

    @Override
    public void create_reset_password(ResetPassword resetPassword) {
        Optional<ResetPassword> old_reset_password = Optional.ofNullable(resetPasswordRepository.findByEmail(resetPassword.getEmail()));
        old_reset_password.ifPresent(password -> resetPasswordRepository.deleteById(password.getId()));
        resetPasswordRepository.save(resetPassword);
    }

    @Override
    public ResetPassword get_reset_password(String email) {
        Optional<ResetPassword> resetPassword = Optional.ofNullable(resetPasswordRepository.findByEmail(email));
        return resetPassword.orElse(null);
    }

    @Override
    public void resetPassword(LoginRequest loginRequest, String role) {
        if (role.equals("user")) {
            Optional<User> user = userRepo.findByEmail(loginRequest.getEmail());
            if (user.isPresent()) {
                user.get().setPassword(passwordEncoder.encode(loginRequest.getPassword()));
                userRepo.save(user.get());
                resetPasswordRepository.deleteById(resetPasswordRepository.findByEmail(loginRequest.getEmail()).getId());
            }
        } else if (role.equals("coach")) {
            Optional<Coach> coach = coachRepo.findByEmail(loginRequest.getEmail());
            if (coach.isPresent()) {
                coach.get().setPassword(passwordEncoder.encode(loginRequest.getPassword()));
                coachRepo.save(coach.get());
                resetPasswordRepository.deleteById(resetPasswordRepository.findByEmail(loginRequest.getEmail()).getId());
            }
        }
    }

    @Override
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
            return coach1;
        }
        return null;
    }

    @Override
    public Plan saveplan(Plan plan) {
        Plan existingPlan = planRepositry.findByPlanName(plan.getPlanName());
        if (existingPlan != null) {
            return null;
        }
        return planRepositry.save(plan);
    }

    // to @aboelwafa, @medany, @ayman this is for you
    @Override
    public User subscribe_to_plan(String planName, Long user_id) {
        Plan plan = planRepositry.findByPlanName(planName);
        User user = userRepo.findById(user_id).orElse(null);
        if (plan != null && user != null) {
            Long coach_id = plan.getCoach().getCoach_id();
            Coach coach = coachRepo.findById(coach_id).orElse(null);
            user.setCoach(coach);
            user.setPlan(plan);
            return userRepo.save(user);
        }
        return null;
    }

    @Override
    public List<User> get_subscribed_users(Long coach_id) {
        Coach coach = coachRepo.findById(coach_id).orElse(null);
        if (coach != null) {
            return coach.getUsers();
        }
        return null;
    }

    @Override
    public User update_comment(String comment, Long user_id) {
        User user = userRepo.findById(user_id).orElse(null);
        if (user != null) {
            user.setComment(comment);
            return userRepo.save(user);
        }
        return null;
    }

    @Override
    public Coach addImageToCoach(String Email, MultipartFile image) {
        try {
            Coach coach = coachRepo.findByEmail(Email).orElse(null);
            if (coach == null) {
                return null;
            }
            coach.setImage(image.getBytes());
            coachRepo.save(coach);
            return coach;
        } catch (Exception e) {
            throw new RuntimeException("Error while changing user image", e);
        }
    }
    @Override
    public List<FoodCalorie> getFoodCalorie(){
        return foodCalorieRepositry.findAll();
    }

}

