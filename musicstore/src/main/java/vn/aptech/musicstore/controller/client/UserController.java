/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.controller.client;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.aptech.musicstore.entity.Song;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/user/**")
public class UserController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        return "client/user/index";
    }
}
