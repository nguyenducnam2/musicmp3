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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.musicstore.entity.Genre;
import vn.aptech.musicstore.service.GenreService;

/**
 *
 * @author namng
 */
@Controller
@RequestMapping("/admin/genre")
public class GenreController {
    
    @Autowired
    private GenreService service;
    
    @GetMapping
    public String index(Model model){
        model.addAttribute("list", service.findAll());
        return "admin/genre/index";
    }
    
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("genre", new Genre());
        return "admin/genre/create";
    }
    
    @PostMapping("/save")
    public String save(Model model,@ModelAttribute Genre genre){
        service.save(genre);
        return "redirect:/admin/genre";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")int id){
        service.deleteById(id);
        return "redirect:/admin/genre";
    }
    
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id")int id,Model model){
        model.addAttribute("genre", service.findById(id));
        return "admin/genre/create";
    }
    
    
}
