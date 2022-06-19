/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.service.AccountService;

/**
 *
 * @author Thanh Sang
 */

@Controller
@RequestMapping("/admin/account")
public class AccountController {
    
    
    
    @Autowired
    private AccountService service;

//    @GetMapping
//    public String index(Model model) {
//        model.addAttribute("list", service.findAll());
//        return "admin/account/index";
//    }

    @GetMapping
    public String index(){
        return "admin/account/index";
    }
    
    
    @GetMapping("/create")
    public String create(Model model) {
//        model.addAttribute("account", new Account());
//        model.addAttribute("listaccount", service.findAll());
        return "admin/account/create";
    }
}