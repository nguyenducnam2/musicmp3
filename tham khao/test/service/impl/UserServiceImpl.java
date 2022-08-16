/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.service.impl;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import vn.aptech.musicstore.entity.User;
//import vn.aptech.musicstore.entity.VerificationToken;
//import vn.aptech.musicstore.entity.model.UserModel;
//import vn.aptech.musicstore.repository.UserRepository;
//import vn.aptech.musicstore.repository.VerificationTokenRepository;
//import vn.aptech.musicstore.service.UserService;
//
///**
// *
// * @author Administrator
// */
//@Service
//public class UserServiceImpl implements UserService{
//
//    @Autowired
//    private UserRepository userRepository;
//    
//    @Autowired
//    private VerificationTokenRepository verificationTokenRepository;
//    
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    
//    @Override
//    public User registerUser(UserModel userModel) {
//        User user = new User();
//        user.setEmail(userModel.getEmail());
//        user.setFirstName(userModel.getFirstName());
//        user.setLastName(userModel.getLastName());
//        user.setRole("USER");
//        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
//        userRepository.save(user);
//        return user;
//    }
//
//    @Override
//    public void saveVerificationTokenForUser(String token, User user) {
//        VerificationToken verificationToken = new VerificationToken(token,user);
//        
//        verificationTokenRepository.save(verificationToken);
//    }
//    
//}
