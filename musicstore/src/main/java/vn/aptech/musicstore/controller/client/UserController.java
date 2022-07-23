/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.controller.client;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.entity.Song;
import vn.aptech.musicstore.service.AccountService;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/user")
public class UserController {
    
     @Autowired
    private AccountService serviceAccount;
     
    @RequestMapping(method = RequestMethod.GET)
    public String index(Principal principal, Model model, HttpServletRequest request){
           String username = principal.getName();
            Optional<Account> user = serviceAccount.findByUsername(username);

            HttpSession session = request.getSession();
            session.setAttribute("user", user.get());
            model.addAttribute("user", user.get());
        return "client/user/index";
    }
    
     @GetMapping("/profile")
    public String profile() {
        return "client/user/profile";
    }
    
}
