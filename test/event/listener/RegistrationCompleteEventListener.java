/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.event.listener;
//
//import lombok.extern.slf4j.Slf4j;
//import java.util.UUID;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import vn.aptech.musicstore.entity.User;
//import vn.aptech.musicstore.event.RegistrationCompleteEvent;
//import vn.aptech.musicstore.service.UserService;
//
///**
// *
// * @author Administrator
// */
//@Slf4j
//public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent>{
//
//    @Autowired
//    private UserService userService;
//    
//    
//    @Override
//    public void onApplicationEvent(RegistrationCompleteEvent event) {
//        // Create the Verification Token for the User with Link
//        User user = event.getUser();
//        String token = UUID.randomUUID().toString();
//        userService.saveVerificationTokenForUser(token,user);
//        // Send Mail to user
//        String url = event.getApplicationUrl()
//                + "verifyRegistration?token="
//                + token;
//        
//        //sendVerificationEmail();
//        log.info("Click the link to verify your account: {}",url);
//    }
//    
//}
