/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.api;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import vn.aptech.musicstore.entity.User;
//import vn.aptech.musicstore.entity.model.UserModel;
//import vn.aptech.musicstore.event.RegistrationCompleteEvent;
//import vn.aptech.musicstore.service.UserService;
//
///**
// *
// * @author Administrator
// */
//@RestController
//public class RegistrationController {
//    
//    @Autowired
//    private UserService userService;
//    
//    @Autowired
//    private ApplicationEventPublisher publisher;
//    
//    @PostMapping("/register")
//    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request){
//        User user =userService.registerUser(userModel);
//        publisher.publishEvent(new RegistrationCompleteEvent(
//                user,
//                applicationUrl(request)
//        ));
//        return "Success";
//    }
//
//    private String applicationUrl(HttpServletRequest request) {
//        return "http://" +
//                request.getServerName()+
//                ":" +
//                request.getServerPort()+
//                request.getContextPath();
//    }
//}
