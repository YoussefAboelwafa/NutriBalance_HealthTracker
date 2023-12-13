//package com.example.nutribalance.service;
//
//import static org.junit.jupiter.api.Assertions.assertSame;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.atLeast;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.example.nutribalance.Entities.Plan;
//import com.example.nutribalance.Entities.User;
//import com.example.nutribalance.Repositries.CoachRepositry;
//import com.example.nutribalance.Repositries.UserRepositry;
//import com.example.nutribalance.dto.SignUpRequest;
//
//import java.util.ArrayList;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ContextConfiguration(classes = {UserServiceImpl.class})
//@ExtendWith(SpringExtension.class)
//class UserServiceImplTest {
//    @MockBean
//    private CoachRepositry coachRepositry;
//
//    @MockBean
//    private PasswordEncoder passwordEncoder;
//
//    @MockBean
//    private UserRepositry userRepositry;
//
//    @Autowired
//    private UserServiceImpl userServiceImpl;
//
//    /**
//     * Method under test: {@link UserServiceImpl#registerNewUser(SignUpRequest)}
//     */
//    @Test
//    void testRegisterNewUser() throws Exception {
//        User user = new User();
//        user.setCoaches(new ArrayList<>());
//        user.setCoaches_reports(new ArrayList<>());
//        user.setContact_number("42");
//        user.setEmail("jane.doe@example.org");
//        user.setPassword("iloveyou");
//        user.setPlan(new Plan());
//        user.setUser_id(1L);
//        user.setUsername("janedoe");
//        user.setWeights(new ArrayList<>());
//        when(userRepositry.save(Mockito.<User>any())).thenReturn(user);
//        doNothing().when(userRepositry).flush();
//        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
//        User actualRegisterNewUserResult = userServiceImpl.registerNewUser(new SignUpRequest("Name", "jane.doe@example.org",
//                "iloveyou", "6625550144", "https://example.org/example", "Provider User ID", "Role"));
//        verify(userRepositry).flush();
//        verify(userRepositry).save(Mockito.<User>any());
//        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
//        assertSame(user, actualRegisterNewUserResult);
//    }
//
//    /**
//     * Method under test:  {@link UserServiceImpl#registerNewUser(SignUpRequest)}
//     */
//    @Test
//    void testRegisterNewUser2() throws Exception {
//        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenThrow(new IllegalStateException("USER"));
//        assertThrows(IllegalStateException.class, () -> userServiceImpl.registerNewUser(new SignUpRequest("Name",
//                "jane.doe@example.org", "iloveyou", "6625550144", "https://example.org/example", "Provider User ID", "Role")));
//        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
//    }
//
//    /**
//     * Method under test:  {@link UserServiceImpl#registerNewUser(SignUpRequest)}
//     */
//    @Test
//    void testRegisterNewUser3() throws Exception {
//        when(userRepositry.existsByEmail(Mockito.<String>any())).thenReturn(true);
//        assertThrows(Exception.class, () -> userServiceImpl.registerNewUser(new SignUpRequest("Name",
//                "jane.doe@example.org", "iloveyou", "6625550144", "https://example.org/example", "Provider User ID", "USER")));
//        verify(userRepositry).existsByEmail(Mockito.<String>any());
//    }
//
//    /**
//     * Method under test: {@link UserServiceImpl#registerNewUser(SignUpRequest)}
//     */
//    @Test
//    void testRegisterNewUser4() throws Exception {
//        User user = new User();
//        user.setCoaches(new ArrayList<>());
//        user.setCoaches_reports(new ArrayList<>());
//        user.setContact_number("42");
//        user.setEmail("jane.doe@example.org");
//        user.setPassword("iloveyou");
//        user.setPlan(new Plan());
//        user.setUser_id(1L);
//        user.setUsername("janedoe");
//        user.setWeights(new ArrayList<>());
//        when(userRepositry.existsByEmail(Mockito.<String>any())).thenReturn(false);
//        when(userRepositry.save(Mockito.<User>any())).thenReturn(user);
//        doNothing().when(userRepositry).flush();
//        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
//        User actualRegisterNewUserResult = userServiceImpl.registerNewUser(new SignUpRequest("Name", "jane.doe@example.org",
//                "iloveyou", "6625550144", "https://example.org/example", "Provider User ID", "USER"));
//        verify(userRepositry).existsByEmail(Mockito.<String>any());
//        verify(userRepositry).flush();
//        verify(userRepositry).save(Mockito.<User>any());
//        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
//        assertSame(user, actualRegisterNewUserResult);
//    }
//
//    /**
//     * Method under test: {@link UserServiceImpl#registerNewUser(SignUpRequest)}
//     */
//    @Test
//    void testRegisterNewUser5() throws Exception {
//        assertThrows(Exception.class, () -> userServiceImpl.registerNewUser(null));
//    }
//
//    /**
//     * Method under test: {@link UserServiceImpl#registerNewUser(SignUpRequest)}
//     */
//    @Test
//    void testRegisterNewUser6() throws Exception {
//        User user = new User();
//        user.setCoaches(new ArrayList<>());
//        user.setCoaches_reports(new ArrayList<>());
//        user.setContact_number("42");
//        user.setEmail("jane.doe@example.org");
//        user.setPassword("iloveyou");
//        user.setPlan(new Plan());
//        user.setUser_id(1L);
//        user.setUsername("janedoe");
//        user.setWeights(new ArrayList<>());
//        when(userRepositry.save(Mockito.<User>any())).thenReturn(user);
//        doNothing().when(userRepositry).flush();
//        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
//        SignUpRequest signUpRequest = mock(SignUpRequest.class);
//        when(signUpRequest.getEmail()).thenReturn("jane.doe@example.org");
//        when(signUpRequest.getName()).thenReturn("Name");
//        when(signUpRequest.getPassword()).thenReturn("iloveyou");
//        when(signUpRequest.getPhoneNumber()).thenReturn("6625550144");
//        when(signUpRequest.getRole()).thenReturn("Role");
//        User actualRegisterNewUserResult = userServiceImpl.registerNewUser(signUpRequest);
//        verify(signUpRequest).getEmail();
//        verify(signUpRequest).getName();
//        verify(signUpRequest).getPassword();
//        verify(signUpRequest).getPhoneNumber();
//        verify(signUpRequest, atLeast(1)).getRole();
//        verify(userRepositry).flush();
//        verify(userRepositry).save(Mockito.<User>any());
//        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
//        assertSame(user, actualRegisterNewUserResult);
//    }
//
//    /**
//     * Method under test: {@link UserServiceImpl#findUserByEmail(String)}
//     */
//    @Test
//    void testFindUserByEmail() {
//        User user = new User();
//        user.setCoaches(new ArrayList<>());
//        user.setCoaches_reports(new ArrayList<>());
//        user.setContact_number("42");
//        user.setEmail("jane.doe@example.org");
//        user.setPassword("iloveyou");
//        user.setPlan(new Plan());
//        user.setUser_id(1L);
//        user.setUsername("janedoe");
//        user.setWeights(new ArrayList<>());
//        Optional<User> ofResult = Optional.of(user);
//        when(userRepositry.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
//        User actualFindUserByEmailResult = userServiceImpl.findUserByEmail("jane.doe@example.org");
//        verify(userRepositry).findByEmail(Mockito.<String>any());
//        assertSame(user, actualFindUserByEmailResult);
//    }
//
//    /**
//     * Method under test:  {@link UserServiceImpl#findUserByEmail(String)}
//     */
//    @Test
//    void testFindUserByEmail2() {
//        when(userRepositry.findByEmail(Mockito.<String>any())).thenThrow(new IllegalStateException("foo"));
//        assertThrows(IllegalStateException.class, () -> userServiceImpl.findUserByEmail("jane.doe@example.org"));
//        verify(userRepositry).findByEmail(Mockito.<String>any());
//    }
//
//    /**
//     * Method under test: {@link UserServiceImpl#findUserById(Long)}
//     */
//    @Test
//    void testFindUserById() {
//        User user = new User();
//        user.setCoaches(new ArrayList<>());
//        user.setCoaches_reports(new ArrayList<>());
//        user.setContact_number("42");
//        user.setEmail("jane.doe@example.org");
//        user.setPassword("iloveyou");
//        user.setPlan(new Plan());
//        user.setUser_id(1L);
//        user.setUsername("janedoe");
//        user.setWeights(new ArrayList<>());
//        Optional<User> ofResult = Optional.of(user);
//        when(userRepositry.findById(Mockito.<Long>any())).thenReturn(ofResult);
//        Optional<User> actualFindUserByIdResult = userServiceImpl.findUserById(1L);
//        verify(userRepositry).findById(Mockito.<Long>any());
//        assertTrue(actualFindUserByIdResult.isPresent());
//        assertSame(ofResult, actualFindUserByIdResult);
//    }
//
//    /**
//     * Method under test:  {@link UserServiceImpl#findUserById(Long)}
//     */
//    @Test
//    void testFindUserById2() {
//        when(userRepositry.findById(Mockito.<Long>any())).thenThrow(new IllegalStateException("foo"));
//        assertThrows(IllegalStateException.class, () -> userServiceImpl.findUserById(1L));
//        verify(userRepositry).findById(Mockito.<Long>any());
//    }
//}
