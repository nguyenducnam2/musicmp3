/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.musicstore.service.BlockCommentService;

/**
 *
 * @author namng
 */
@Controller
@RequestMapping("/admin/blockcomment")
public class BlockCommentController {
    
    @Autowired
    private BlockCommentService service;
    
    @GetMapping
    public String index(Model model){
        model.addAttribute("list", service.findAll());
        return "admin/blockcomment/index";
    }
}
