/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping
    public String index(Model model) {
        model.addAttribute("list", service.findAll());
        return "admin/account/index";
    }
   
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("account", new Account());
        return "admin/account/create";
    }
    
     @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("account", service.findById(id));
        return "admin/account/update";
    }
    
    @PostMapping("/save")
    public String save(Model model,@ModelAttribute Account acc){
        service.save(acc);
        return "redirect:/admin/account";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")int id){
        service.deleteById(id);
        return "redirect:/admin/account";
    }
}