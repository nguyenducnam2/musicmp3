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
import vn.aptech.musicstore.entity.Brand;
import vn.aptech.musicstore.service.BrandService;

/**
 *
 * @author pc
 */
@Controller
@RequestMapping("/admin/brand")
public class BrandController {
    
    @Autowired
    private BrandService service;
    
    @GetMapping
    public String index(Model model){
        model.addAttribute("list", service.findAll());
        return "admin/brand/index";
    }
    
     @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("brand", new Brand());
        model.addAttribute("action", "create");
        return "admin/brand/create";
        
    }
    
    @PostMapping("/save")
    public String save(Model model,@ModelAttribute Brand brand){
        service.save(brand);
        return "redirect:/admin/brand";
    } 
    
     @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")int id){
        service.deleteById(id);
        return "redirect:/admin/brand";
    }
    
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id")int id,Model model){
        model.addAttribute("brand", service.findById(id));
        model.addAttribute("action", "update");
        return "admin/brand/create";
    }
    
     @GetMapping("/search")
    public String search(Model model,@RequestParam("name")String name){
        model.addAttribute("list", service.findByName(name));
        return "admin/brand/index";
    }
    
    
    
}
