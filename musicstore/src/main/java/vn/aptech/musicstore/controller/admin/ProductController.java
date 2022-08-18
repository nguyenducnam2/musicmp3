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
    public String index(Model model, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
         model.addAttribute("list", service.getPage(pageNumber, size));
        model.addAttribute("service", service);
        model.addAttribute("name", "null");

        return "admin/product/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("cateproduct", cate_service.findAll());
        model.addAttribute("brandproduct", brand_service.findAll());

           model.addAttribute("action", "create");

        return "admin/product/create";
    }
 @PostMapping("/save")
    public String save(Model model, @ModelAttribute("product") Product product, @RequestParam("file") MultipartFile file
    , @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) throws IOException {
       
         try {
              if (!(file.isEmpty())) {
            product.setImage(file.getOriginalFilename());
            product.setCreated(java.time.LocalDate.now());
//            String path_directory = "C:\\Users\\namng\\Documents\\NetBeansProjects\\musicstore\\src\\main\\resources\\static\\image";
//        String path_directory = new ClassPathResource("static/image").getFile().getAbsolutePath();
            Files.copy(file.getInputStream(), Paths.get(base_url+"\\webdata\\product" + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            service.save(product);
        } else {
            product.setImage(service.findById(product.getId()).orElseThrow().getImage());
            service.save(product);
        }
          
        } catch (Exception e) {
            model.addAttribute("listpage", service.getPage(pageNumber, size));
            model.addAttribute("service", service);
            model.addAttribute("name", "null");
            model.addAttribute("mess", "Failed");
            return "redirect:/admin/product";
        }
        model.addAttribute("listpage", service.getPage(pageNumber, size));
        model.addAttribute("service", service);
        model.addAttribute("name", "null");
        model.addAttribute("mess", "Successfully");
        return "redirect:/admin/product";
    }
     @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")int id, Model model, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size){
         try {
            service.deleteById(id);
        } catch (Exception e) {
            model.addAttribute("list", service.getPage(pageNumber, size));
            model.addAttribute("service", service);
            model.addAttribute("name", "null");
            model.addAttribute("mess", "Failed");
            return "admin/product/index";
        }
        model.addAttribute("list", service.getPage(pageNumber, size));
        model.addAttribute("service", service);
        model.addAttribute("name", "null");
        model.addAttribute("mess", "Successfully");
        
        return "/admin/product/index";
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
    public String search(Model model,@RequestParam("name")String name, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "1000") int size){
       model.addAttribute("list", service.getPage(pageNumber, size));
        model.addAttribute("name", name);
        model.addAttribute("service", service);
        return "admin/product/index";
    }
}
