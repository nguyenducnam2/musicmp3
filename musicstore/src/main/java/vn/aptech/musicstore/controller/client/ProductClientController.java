/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.controller.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.aptech.musicstore.entity.Category;
import vn.aptech.musicstore.entity.Product;
import vn.aptech.musicstore.service.CategoryService;
import vn.aptech.musicstore.service.ProductService;

/**
 *
 * @author pc
 */
@Controller
public class ProductClientController {
    
    @Autowired
    private ProductService service;
    
    @Autowired
    private CategoryService cateservice;
            
    @GetMapping("/product")
    public String index(Model model) {
        model.addAttribute("product",service.findAll());
        model.addAttribute("categories",cateservice.findAll());
        return "client/product/index";
    }
    
     @GetMapping("/product/{id}")
    public String details(Model model, @PathVariable("id") int id) {
        model.addAttribute("product", service.findById(id).orElseThrow());
        return "client/product/index";
    }
   
     @GetMapping("/product/detail/{id}")
    public String getdetails(Model model, @PathVariable("id") int id) {
        model.addAttribute("product", service.findById(id).orElseThrow());
        return "client/product/detail";
    }
    
    @GetMapping(value = "/product/category/{id}")
    public String getProductByCategory( @PathVariable("id") int categoryId,Model model){
        List<Product> product=new ArrayList<>();
        for(Product item:service.findAll()){
            if(item.getCategoryId()==cateservice.findById(categoryId).orElseThrow()){
                product.add(item);
            }
        }
        model.addAttribute("product",product);
        model.addAttribute("categories",cateservice.findAll());
        return "client/product/index";
    }
    
   
    
 
}
