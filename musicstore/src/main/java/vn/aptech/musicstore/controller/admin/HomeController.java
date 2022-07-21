/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.controller.admin;

import java.security.Principal;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.service.AccountService;

/**
 *
 * @author namng
 */
@Controller
@RequestMapping("/admin")
public class HomeController {

    @Autowired
    private AccountService serviceAccount;

//    @GetMapping("/{username}")
//    public String index(@PathVariable("username") String username, Model model) {
//        Optional<Account> a = serviceAccount.findByUsername(username);
//        model.addAttribute("user", a.get());
//        return "admin/index";
//    }
    
//    @GetMapping
//    public String index() {
//        return "admin/index";
//    }
//    
    @GetMapping
    public String index(Principal principal, Model model, HttpServletRequest request) {
        String username = principal.getName();
        Optional<Account> user = serviceAccount.findByUsername(username);
        
        HttpSession session =request.getSession();
        session.setAttribute("user", user.get());
//        System.out.println("session: " + session);
//        session  = request.setAttribute("user", user.get().getId());
        model.addAttribute("user",user.get());
        return "admin/index";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        return "admin/login";
    }

    @GetMapping(value = "/403")
    public String error403(Model model) {
        return "error/403";
    }
}
