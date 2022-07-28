/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import vn.aptech.musicstore.entity.Artist;
import vn.aptech.musicstore.entity.Product;
import vn.aptech.musicstore.service.BrandService;
import vn.aptech.musicstore.service.CategoryService;
import vn.aptech.musicstore.service.ProductService;

/**
 *
 * @author pc
 */
@Controller
@RequestMapping("/admin/product")
public class ProductController {
    
    @Value("${static.base.url}")
    private String base_url;


    @Autowired
    private ProductService service;
    @Autowired
    private CategoryService cate_service;
    @Autowired
    private BrandService brand_service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("list", service.findAll());
        return "admin/product/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("cateproduct", cate_service.findAll());
                model.addAttribute("cateproduct", cate_service.findAll());
        model.addAttribute("brandproduct", brand_service.findAll());

           model.addAttribute("action", "create");

        return "admin/product/create";
    }
 @PostMapping("/save")
    public String save(Model model, @ModelAttribute("product") Product product, @RequestParam("file") MultipartFile file) throws IOException {
        if (!(file.isEmpty())) {
            product.setImage(file.getOriginalFilename());
//            String path_directory = "C:\\Users\\namng\\Documents\\NetBeansProjects\\musicstore\\src\\main\\resources\\static\\image";
//        String path_directory = new ClassPathResource("static/image").getFile().getAbsolutePath();
            Files.copy(file.getInputStream(), Paths.get(base_url+"\\webdata\\product" + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            service.save(product);
        } else {
            product.setImage(service.findById(product.getId()).orElseThrow().getImage());
            service.save(product);
        }
        return "redirect:/admin/product";
    }
     @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")int id){
        service.deleteById(id);
        return "redirect:/admin/product";
    }
    
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id")int id,Model model){
        model.addAttribute("product", service.findById(id));
        model.addAttribute("cateproduct",cate_service.findAll());
        model.addAttribute("brandproduct", brand_service.findAll());
           model.addAttribute("action", "update");
        return "admin/product/create";
    }
      
     @GetMapping("/search")
    public String search(Model model,@RequestParam("name")String name){
        model.addAttribute("list", service.findByName(name));
        return "admin/product/index";
    }
}
