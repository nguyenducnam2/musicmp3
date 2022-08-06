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
import vn.aptech.musicstore.entity.Product;
import vn.aptech.musicstore.entity.WareHouse;
import vn.aptech.musicstore.service.ProductService;
import vn.aptech.musicstore.service.WarehouseService;

/**
 *
 * @author pc
 */
@Controller
@RequestMapping("/admin/warehouse")
public class WarehouseController {
    
     @Autowired
    private WarehouseService service;
     
     @Autowired
     private ProductService service_p;
    
    @GetMapping
    public String index(Model model){
        model.addAttribute("ware", service.findAll());

        return "admin/warehouse/index";
    }
    
     @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("ware", new WareHouse());
                model.addAttribute("product", service_p.findAll());

        model.addAttribute("action", "create");
        return "admin/warehouse/create";
        
    }
    
    @PostMapping("/save")
    public String save(Model model,@ModelAttribute WareHouse warehouse){
        service.save(warehouse);
        return "redirect:/admin/warehouse";
    } 
    
     @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")int id){
        service.deleteById(id);
        return "redirect:/admin/warehouse";
    }
    
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id")int id,Model model){
        model.addAttribute("ware", service.findById(id));
                model.addAttribute("product", service_p.findAll());

        model.addAttribute("action", "update");
        return "admin/warehouse/create";
    }
    
//     @GetMapping("/search")
//    public String search(Model model,@RequestParam("name")String name){
//        model.addAttribute("ware", service.findByName(name));
//        return "admin/category/index";
//    }
    
}
