package com.example.nutribalance.services;

import com.example.nutribalance.entities.*;
import com.example.nutribalance.mails.EmailDetails;
import com.example.nutribalance.mails.EmailService;
import com.example.nutribalance.repositories.*;
import com.example.nutribalance.dto.ChatDto;
import com.example.nutribalance.dto.LoginRequest;
import com.example.nutribalance.dto.NotificationDto;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class Service implements IService {

    //------------------------------services--------------------------------
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    //------------------------------repositories--------------------------------
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private WeightRepository weightRepository;
    @Autowired
    private FoodCalorieRepository foodCalorieRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private ResetPasswordRepository resetPasswordRepository;


    //------------------------------handlers--------------------------------
    private final Message messageHandler = new Message();
    


    //------------------------------ coach methods--------------------------------
    
    @Override
    public Coach saveCoach(Coach coach) {
        Optional<Coach> old_coach_1 = coachRepository.findByEmail(coach.getEmail());
        if(old_coach_1.isPresent()){
            return null;
        }
        return coachRepository.save(coach);
    }

    @Override
    public Coach updateCoach(Coach coach) {
        Optional<Coach> existingCoachOpt = coachRepository.findById(coach.getCoach_id());
        if (existingCoachOpt.isEmpty()) {
            return null;
        }
        Coach existingCoach = existingCoachOpt.get();
        existingCoach.setDescription(coach.getDescription());
        existingCoach.setPrice(coach.getPrice());
        existingCoach.setContact_number(coach.getContact_number());
        existingCoach.setAddress(coach.getAddress());
        return coachRepository.save(existingCoach);

    }
    @Override
    public List<Coach> getWaitingCoaches() {
        return coachRepository.findByisapproved(0);
    }

    @Override
    public Coach updateCoachCV(String email, byte[] bytes) {
        Optional<Coach> coach = coachRepository.findByEmail(email);
        if (coach.isPresent()) {
            coach.get().setCv(bytes);
            return coachRepository.save(coach.get());
        }
        return null;
    }

    @Override
    public String deletecoach(Long id) {
        coachRepository.deleteById(id);
        return "coach deleted";
    }

    @Override
    public Coach approvecoach(Long id) {
        Optional<Coach> coach = coachRepository.findById(id);
        if (coach.isPresent()) {
            coach.get().setIsapproved(1);
            EmailDetails details = getApprovedCoachEmail(coach.get());
            emailService.sendMemeMail(details);
            return coachRepository.save(coach.get());
        }
        return null;
    }

    @Override
    public Coach registerCoach(Coach coach) {
        Optional<Coach> old_coach_1 = coachRepository.findByEmail(coach.getEmail());
        if (old_coach_1.isPresent()) {
            return null;
        }
        coach.setPassword(passwordEncoder.encode(coach.getPassword()));
        boolean sent = sendVerificationMail(coach, "coach");
        if (!sent) {
            return null;
        }
        return coachRepository.save(coach);
    }

    @Override
    public List<User> getSubscribedUsers(Long coach_id) {
        Coach coach = coachRepository.findById(coach_id).orElse(null);
        if (coach != null) {
            return coach.getUsers();
        }
        return null;
    }

    @Override
    public User updateComment(String comment, Long user_id) {
        User user = userRepository.findById(user_id).orElse(null);
        if (user != null) {
            user.setComment(comment);
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public Coach addImageToCoach(String Email, MultipartFile image) {
        try {
            Coach coach = coachRepository.findByEmail(Email).orElse(null);
            if (coach == null) {
                return null;
            }
            coach.setImage(image.getBytes());
            coachRepository.save(coach);
            return coach;
        } catch (Exception e) {
            throw new RuntimeException("Error while changing user image", e);
        }
    }

    @Override
    public Coach coachSignIn(String email, String pass) {
        Optional<Coach> coach = coachRepository.findByEmail(email);
        Coach coach1 = coach.orElse(null);
        if (coach.isPresent()) {
            if (coach1.getIsapproved() == 1 && coach1.isEnabled()) {
                if (passwordEncoder.matches(pass, coach1.getPassword())) {
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
    public void deleteCoach(Long id){

        List<Plan> plans= planRepository.findAll();
        List<Plan> plans_delete=new ArrayList<>();
        for(Plan plan:plans){
            if(plan.getCoach().getCoach_id().longValue()==id){
                plans_delete.add(plan);
            }
        }
        planRepository.deleteAll(plans_delete);
        List<Report> reports= reportRepository.findAll();
        List<Report> reports_delete=new ArrayList<>();
        for(Report report:reports){
            if(report.getCoach().getCoach_id().longValue()==id){
                reports_delete.add(report);
            }
        }
        reportRepository.deleteAll(reports_delete);
        List<Chat> chats= chatRepository.findAll();
        List<Chat> chats_delete=new ArrayList<>();
        for(Chat chat:chats){
            if(chat.getCoach().longValue()==id){
                chats_delete.add(chat);
            }
        }
        chatRepository.deleteAll(chats_delete);
        coachRepository.deleteById(id);
    }

    @Override
    public List<Coach> getCoaches(){
        return coachRepository.findAll();
    }



    //------------------------------plan methods--------------------------------
    @Override
    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }

    public List<Plan> getPlans(Long coachId) {
        Optional<Coach> coach = coachRepository.findById(coachId);
        return coach.map(Coach::getPlans).orElse(null);
    }

    @Override
    public Plan updatePlan(Plan plan) {
        Optional<Plan> existingPlanOpt = planRepository.findById(plan.getPlanName());
        if (existingPlanOpt.isEmpty()) {
            return null;
        }
        Plan existingPlan = existingPlanOpt.get();
        existingPlan.setDescription(plan.getDescription());
        existingPlan.setGoal(plan.getGoal());
        Coach coach = existingPlan.getCoach();
        List<User> users = coach.getUsers();
        for (User user : users) {
            addNotification(messageHandler.getMessage(coach.getUsername(), NotificationType.UPDATE_PLAN), NotificationType.UPDATE_PLAN.ordinal(), user);
        }
        return planRepository.save(existingPlan);
    }
    @Override
    public String deletePlan(String planName) {
        Plan plan = planRepository.findById(planName).orElse(null);
        assert plan != null;
        if (!plan.getUsers().isEmpty()) {
            return "There are users subscribed to this plan";
        }
        planRepository.deleteById(planName);
        return "Plan deleted";

    }
    @Override
    public Plan saveplan(Plan plan) {
        Plan existingPlan = planRepository.findByPlanName(plan.getPlanName());
        Coach coach = coachRepository.findById(plan.getCoach().getCoach_id()).orElse(null);
        if (coach == null) {
            return null;
        }
        if (existingPlan != null) {
            return null;
        }
        //for all users subscribed to this coach send notification that he added new plan
        List<User> users = coach.getUsers();
        for (User user : users) {
            addNotification(messageHandler.getMessage(coach.getUsername(), NotificationType.NEW_PLAN), NotificationType.NEW_PLAN.ordinal(), user);
        }
        return planRepository.save(plan);
    }






    //------------------------------mail methods--------------------------------
    private EmailDetails getApprovedCoachEmail(Coach coach) {
        EmailDetails details = new EmailDetails();
        details.setRecipient(coach.getEmail());
        details.setSubject("Approved Coach Email");
        details.setMsgBody(
                "Dear" + coach.getUsername() + ",\n" +
                        "\n" +
                        "We are pleased to inform you that your coaching account with NutriBalance has been approved. Your extensive experience and qualifications have been recognized, and we believe that your expertise will greatly contribute to the success of our platform.\n" +
                        "\n" +
                        "You can now access your account and start providing valuable coaching services to our users. If you have any questions or need further assistance, please feel free to reach out to our support team.\n" +
                        "\n" +
                        "Thank you for joining NutriBalance. We look forward to a successful collaboration.\n" +
                        "\n" +
                        "Best regards,\n" +
                        "NutriBalance Team");
        return details;
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

    private boolean sendVerificationMail(Object user, String role) {
        if (role.equals("user")) {
            User user1 = (User) user;
            Optional<ResetPassword> old_reset_password = Optional.ofNullable(resetPasswordRepository.findByEmail(user1.getEmail()));
            old_reset_password.ifPresent(password -> resetPasswordRepository.deleteById(password.getId()));
            ResetPassword resetPassword = new ResetPassword();
            resetPassword.setEmail(user1.getEmail());
            resetPassword.setUsername(user1.getUsername());
            String token = RandomString.make(64);
            resetPassword.setToken(token);
            resetPasswordRepository.save(resetPassword);
            user1.setEnabled(false);
            EmailDetails mail = getEmailDetails(user1.getEmail(), user1.getUsername(), token);
            return emailService.sendMemeMail(mail).equals("done");

        } else {
            Coach coach = (Coach) user;
            Optional<ResetPassword> old_reset_password = Optional.ofNullable(resetPasswordRepository.findByEmail(coach.getEmail()));
            old_reset_password.ifPresent(password -> resetPasswordRepository.deleteById(password.getId()));
            ResetPassword resetPassword = new ResetPassword();
            resetPassword.setEmail(coach.getEmail());
            resetPassword.setUsername(coach.getUsername());
            String token = RandomString.make(64);
            resetPassword.setToken(token);
            resetPasswordRepository.save(resetPassword);
            coach.setEnabled(false);
            EmailDetails mail = getEmailDetails(coach.getEmail(), coach.getUsername(), token);
            return emailService.sendMemeMail(mail).equals("done");
        }
    }

    private static EmailDetails getEmailDetails(String email, String name, String token) {
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Thank you for signing up with NutriBalance!<br>"
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
        String script = """
                <script type="text/javascript">
                    function handleLinkClick(event) {
                        event.preventDefault(); // Prevent the link from opening in a new tab
                        window.location.href = event.target.getAttribute("href"); // Navigate to the link's URL
                        return false;
                    }
                </script>
                """;
        content = script + content;
        EmailDetails mail = new EmailDetails();
        mail.setMsgBody(content);
        mail.setRecipient(email);
        mail.setSubject(subject);
        return mail;
    }


    //------------------------------user methods--------------------------------
    @Override
    public User saveuser(User user) {
        Optional<User> old_user_1 = userRepository.findByEmail(user.getEmail());
        if (old_user_1.isPresent()) {
            return null;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        boolean sent = sendVerificationMail(user, "user");
        if (!sent) {
            return null;
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        Optional<User> existingUserOpt = userRepository.findById(user.getUser_id());
        if (existingUserOpt.isEmpty()) {
            return null;
        }
        return userRepository.save(user);
    }

    @Override
    public User addImageToUser(String Email, MultipartFile image) {
        try {
            User user = userRepository.findByEmail(Email).orElse(null);
            if (user == null) {
                return null;
            }
            user.setImage(image.getBytes());
            userRepository.save(user);
            return user;
        } catch (Exception e) {
            throw new RuntimeException("Error while changing user image", e);
        }
    }

    @Override
    public User userSignIn(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
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
            Optional<User> user = userRepository.findByEmail(email);
            if (user.isPresent()) return user.get().getUsername();
        } else if (role.equals("coach")) {
            Optional<Coach> coach = coachRepository.findByEmail(email);
            if (coach.isPresent()) return coach.get().getUsername();
        }
        return null;
    }


    @Override
    public User subscribeToPlan(String planName, Long user_id) {
        Plan plan = planRepository.findByPlanName(planName);
        User user = userRepository.findById(user_id).orElse(null);
        if (plan != null && user != null) {
            Long coach_id = plan.getCoach().getCoach_id();

            Coach coach = coachRepository.findById(coach_id).orElse(null);
            assert coach != null;
            coach.setNo_users_subscribed(coach.getNo_users_subscribed() + 1);
            coachRepository.save(coach);
            user.setCoach(coach);
            user.setPlan(plan);
            addNotification(messageHandler.getMessage(user.getUsername(), NotificationType.NEW_SUBSCRIPTION), NotificationType.NEW_SUBSCRIPTION.ordinal(), coach);
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(Long id){
        List<Report> reports= reportRepository.findAll();
        List<Report> reports_delete=new ArrayList<>();
        for(Report report:reports){
            if(report.getUser().getUser_id()==id){
                reports_delete.add(report);
            }
        }
        reportRepository.deleteAll(reports_delete);
        List<Weight> weights= weightRepository.findAll();
        List<Weight> weights_delete=new ArrayList<>();
        for(Weight weight:weights){
            if(weight.getUser().getUser_id()==id){
                weights_delete.add(weight);
            }
        }
        weightRepository.deleteAll(weights_delete);

        List<Chat> chats= chatRepository.findAll();
        List<Chat> chats_delete=new ArrayList<>();
        for(Chat chat:chats){
            if(chat.getUser().longValue()==id){
                chats_delete.add(chat);
            }
        }
        chatRepository.deleteAll(chats_delete);
        userRepository.deleteById(id);
    }



    //------------------------------password methods--------------------------------

    @Override
    public boolean verify(String verificationCode) {
        ResetPassword resetPassword = resetPasswordRepository.findByToken(verificationCode);
        if (resetPassword == null) {
            return false;
        }
        User user = userRepository.findByEmail(resetPassword.getEmail()).orElse(null);

        String role = user == null ? "coach" : "user";
        if (role.equals("user")) {
            user.setEnabled(true);
            userRepository.save(user);
        } else {
            Coach coach = coachRepository.findByEmail(resetPassword.getEmail()).orElse(null);
            if (coach == null) {
                return false;
            }
            coach.setEnabled(true);
            EmailDetails details = getApprovalEmailDetails(coach);
            emailService.sendMemeMail(details);
            coachRepository.save(coach);
        }
        resetPasswordRepository.deleteById(resetPassword.getId());
        return true;
    }

    @Override
    public void sendForgetPasswordEmail(ResetPassword resetPassword) {
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
        emailService.sendMemeMail(mail);

        System.out.println("Email has been sent");

    }

    @Override
    public void createResetPassword(ResetPassword resetPassword) {
        Optional<ResetPassword> old_reset_password = Optional.ofNullable(resetPasswordRepository.findByEmail(resetPassword.getEmail()));
        old_reset_password.ifPresent(password -> resetPasswordRepository.deleteById(password.getId()));
        resetPasswordRepository.save(resetPassword);
    }

    @Override
    public ResetPassword getResetPassword(String email) {
        Optional<ResetPassword> resetPassword = Optional.ofNullable(resetPasswordRepository.findByEmail(email));
        return resetPassword.orElse(null);
    }

    @Override
    public void resetPassword(LoginRequest loginRequest, String role) {
        if (role.equals("user")) {
            Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
            if (user.isPresent()) {
                user.get().setPassword(passwordEncoder.encode(loginRequest.getPassword()));
                userRepository.save(user.get());
                resetPasswordRepository.deleteById(resetPasswordRepository.findByEmail(loginRequest.getEmail()).getId());
            }
        } else if (role.equals("coach")) {
            Optional<Coach> coach = coachRepository.findByEmail(loginRequest.getEmail());
            if (coach.isPresent()) {
                coach.get().setPassword(passwordEncoder.encode(loginRequest.getPassword()));
                coachRepository.save(coach.get());
                resetPasswordRepository.deleteById(resetPasswordRepository.findByEmail(loginRequest.getEmail()).getId());
            }
        }
    }



    @Override
    public String changePassword(String email, String oldPassword, String password, String role) {
        if (role.equals("user")) {
            User user = userRepository.findByEmail(email).orElse(null);
            if (user == null) {
                return "Email is not valid!";
            }
            if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
                return "Old Password is not valid!";
            }
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            return "success";
        } else if (role.equals("coach")) {
            Coach coach = coachRepository.findByEmail(email).orElse(null);
            if (coach == null) {
                return "Email is not valid!";
            }
            if (!passwordEncoder.matches(oldPassword, coach.getPassword())) {
                return "Old Password is not valid!";
            }
            coach.setPassword(passwordEncoder.encode(password));
            coachRepository.save(coach);
            return "success";
        }
        return "Email is not valid!";
    }






    //------------------------------food methods--------------------------------
    @Override
    public List<FoodCalorie> getFoodCalorie() {
        return foodCalorieRepository.findAll();
    }


    //------------------------------weight methods--------------------------------
    @Override
    public User addWeight(Long id, Double weight, Date date) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            Weight newWeight = new Weight();
            newWeight.setDate(date);
            newWeight.setUser(user);
            newWeight.setWeight(weight);
            weightRepository.save(newWeight);

            return user;
        }
        return null;
    }

    public List<Weight> getWeights(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return user.getWeights();
        }
        return null;
    }

    @Override
    public User deleteSubscription(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            Coach coach = user.getCoach();
            coach.setNo_users_subscribed(coach.getNo_users_subscribed() - 1);
            coachRepository.save(coach);
            addNotification(messageHandler.getMessage(user.getUsername(), NotificationType.DELETE_SUBSCRIPTION), NotificationType.DELETE_SUBSCRIPTION.ordinal(), coach);
            user.setCoach(null);
            user.setPlan(null);
            return userRepository.save(user);
        }
        return null;
    }




    //----------------------    Chat    ----------------------//
    @Override
    public Chat saveChat(ChatDto chatDto) {
        Long user_id = chatDto.getUser_id();
        Long coach_id = chatDto.getCoach_id();
        String message = chatDto.getMessage();
        String sent_by = chatDto.getSent_by();
        int seen = chatDto.getSeen();
        User user = userRepository.findById(user_id).orElse(null);
        Coach coach = coachRepository.findById(coach_id).orElse(null);
        if (user != null && coach != null) {
            Chat chat = new Chat();
            chat.setUser(user_id);
            chat.setCoach(coach_id);
            LocalDateTime currentDateTime = LocalDateTime.now();
            chat.setLocalDateTime(currentDateTime);
            chat.setMessage(message);
            chat.setSent_by(sent_by);
            chat.setSeen(seen);
            if ("user".equalsIgnoreCase(sent_by)) {
                addNotification(messageHandler.getMessage(user.getUsername(), NotificationType.COACH_MESSAGE), NotificationType.COACH_MESSAGE.ordinal(), coach);
            } else {
                addNotification(messageHandler.getMessage(coach.getUsername(), NotificationType.USER_MESSAGE), NotificationType.USER_MESSAGE.ordinal(), user);
            }
            chatRepository.save(chat);
            return chat;
        }
        return null;

    }

    @Override
    public List<Chat> getUserChats(Long user_id) {
        User user = userRepository.findById(user_id).orElse(null);
        if (user != null)
            return chatRepository.findByUserOrderByLocalDateTimeAsc(user_id);
        return null;
    }

    @Override
    public List<Chat> getCoachChats(Long coach_id) {
        Coach coach = coachRepository.findById(coach_id).orElse(null);
        if (coach != null)
            return chatRepository.findByCoachOrderByLocalDateTimeAsc(coach_id);
        return null;
    }

    @Transactional
    @Override
    public void deleteChatByUser(Long user_id) {
        User user = userRepository.findById(user_id).orElse(null);
        List<Chat> chats = chatRepository.findByUserOrderByLocalDateTimeAsc(user_id);
        if (user != null && !chats.isEmpty())
            chatRepository.deleteByUser(user_id);
    }
    @Override
    public int getUnseenChats(Long user_id, Long coach_id) {
        User user = userRepository.findById(user_id).orElse(null);
        Coach coach = coachRepository.findById(coach_id).orElse(null);
        int unseen = 0;
        if (user != null && coach != null) {
            List<Chat> chats = chatRepository.findByUserAndCoachOrderByLocalDateTimeAsc(user_id, coach_id);
            for (int i = chats.size() - 1; i >= 0; i--) {
                if (chats.get(i).getSeen() == 0) {
                    unseen++;
                } else {
                    break;
                }
            }
            return unseen;
        }

        return -1;
    }

    @Override
    public void setSeen(Long user_id, Long coach_id) {
        User user = userRepository.findById(user_id).orElse(null);
        Coach coach = coachRepository.findById(coach_id).orElse(null);
        if (user != null && coach != null) {
            List<Chat> chats = chatRepository.findByUserAndCoachOrderByLocalDateTimeAsc(user_id, coach_id);
            if (!chats.isEmpty()) {
                chats.get(chats.size() - 1).setSeen(1);
                chatRepository.save(chats.get(chats.size() - 1));
            }
        }
    }


    //------------------------------notification methods--------------------------------
    @Override
    public List<NotificationDto> getNotifications(Long id, String role) {
        List<Notification> notifications;
        if ("coach".equalsIgnoreCase(role)) {
            Coach coach = coachRepository.findById(id).orElse(null);
            assert coach != null;
            notifications = notificationRepository.findByCoach_Coach_id(coach.getCoach_id());
        } else if ("user".equalsIgnoreCase(role)) {
            User user = userRepository.findById(id).orElse(null);
            assert user != null;
            notifications = notificationRepository.findByUser_User_id(user.getUser_id());
        } else {
            throw new IllegalArgumentException("Invalid role: " + role);
        }
        return notifications.stream()
                .map(NotificationDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public String deleteNotification(Long notificationId) {
        try {
            notificationRepository.deleteById(notificationId);
            return "Notification deleted";
        } catch (Exception e) {
            return "Notification not found";
        }

    }

    public void addNotification(String message, int type, Object obj) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setType(type);
        notification.setDate(LocalDateTime.now());
        if (obj instanceof Coach) {
            notification.setCoach((Coach) obj);
        } else if (obj instanceof User) {
            notification.setUser((User) obj);
        } else {
            throw new IllegalArgumentException("Invalid object type: " + obj.getClass().getName());
        }
        notificationRepository.save(notification);
    }



    //------------------------------report methods--------------------------------
    @Override
    public Report addReport(Long user_id,Long coach_id,String message,String author){
        Report report=new Report();
        Optional<User> user= userRepository.findById(user_id);
        Optional<Coach> coach= coachRepository.findById(coach_id);
        if(user.isEmpty() || coach.isEmpty()){
            return null;
        }
        report.setUser(user.get());
        report.setCoach(coach.get());
        report.setMessage(message);
        report.setAuthor(author);
        return reportRepository.save(report);

    }
    @Override
    public List<Report> getReports(){
        return reportRepository.findAll();
    }

    @Override
    public void deleteReport(Long user_id,Long coach_id){
       ReportId reportId=new ReportId();
       reportId.setUser(user_id);
       reportId.setCoach(coach_id);
       reportRepository.deleteById(reportId);
    }
}




