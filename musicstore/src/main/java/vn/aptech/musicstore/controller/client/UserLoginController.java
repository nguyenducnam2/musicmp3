/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Administrator
 */
//@Controller
//@RequestMapping("/user-panel")
//public class UserLoginController {
//
//    @RequestMapping(method = RequestMethod.GET)
//    public String index() {
//        return "redirect:/user/login";
//    }
//
//    @RequestMapping(value = "login", method = RequestMethod.GET)
//    public String login(
//            @RequestParam(value = "error", required = false) String error,
//            @RequestParam(value = "logout", required = false) String logout,
//            ModelMap modelMap) {
//        if (error != null) {
//            modelMap.put("msg", "Invalid username or password");
//        }
//        if (logout != null) {
//            modelMap.put("msg", "Invalid username or password");
//        }
//        return "user/login";
//    }
//
//    @RequestMapping(value = "logout", method = RequestMethod.GET)
//    public String logout() {
//        return "redirect:/user/login?logout";
//    }
//
//    @RequestMapping(value = "accessDenied", method = RequestMethod.GET)
//    public String accessDenied() {
//        return "user/accessDenied";
//    }
//}
