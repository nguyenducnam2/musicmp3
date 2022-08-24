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
import org.springframework.web.bind.annotation.RequestParam;
import vn.aptech.musicstore.entity.Category;
import vn.aptech.musicstore.service.CategoryService;

/**
 *
 * @author pc
 */
@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    
    @Autowired
    private CategoryService service;
    
    @GetMapping
    public String index(Model model){
        model.addAttribute("list", service.findAll());
        return "admin/category/index";
    }
    
     @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("category", new Category());
        model.addAttribute("action", "create");
        return "admin/category/create";
        
    }
    
    @PostMapping("/save")
    public String save(Model model,@ModelAttribute Category category){
        service.save(category);
        return "redirect:/admin/category";
    } 
    
     @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")int id){
        service.deleteById(id);
        return "redirect:/admin/category";
    }
    
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id")int id,Model model){
        model.addAttribute("category", service.findById(id));
        model.addAttribute("action", "update");
        return "admin/category/create";
    }
    
     @GetMapping("/search")
    public String search(Model model,@RequestParam("name")String name){
        model.addAttribute("list", service.findByName(name));
        return "admin/category/index";
    }
    
    
    
}
