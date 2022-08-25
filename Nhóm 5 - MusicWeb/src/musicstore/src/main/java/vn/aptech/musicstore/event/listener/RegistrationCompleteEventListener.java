/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.event.listener;

import lombok.extern.slf4j.Slf4j;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.event.RegistrationCompleteEvent;
import vn.aptech.musicstore.service.AccountService;

/**
 *
 * @author Administrator
 */
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent>{

    @Autowired
    private AccountService accService;
    
    
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // Create the Verification Token for the Account with Link
        log.info("sss");
        
        Account acc = event.getAcc();
        String token = UUID.randomUUID().toString();
        System.out.println("token"+token );
        
        accService.saveVerificationTokenForUser(token,acc);
        // Send Mail to acc
        String url = event.getApplicationUrl()
                + "verifyRegistration?token="
                + token;
        
                System.out.println("url"+url );

        //sendVerificationEmail();
        log.info("Click the link to verify your account: {}",url);
    }
    
}
