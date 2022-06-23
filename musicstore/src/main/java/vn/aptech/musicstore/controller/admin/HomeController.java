/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author namng
 */
@Controller
@RequestMapping("/admin")
public class HomeController {
    
    @GetMapping
    public String index(){
        return "admin/index";
    }
    
    @GetMapping(value="/login")
    public String login(Model model){
        return "login";
    }
    @GetMapping(value="/403")
    public String error403(Model model){
        return "error/403";
    }    
}
