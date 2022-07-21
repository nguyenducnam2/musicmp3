package com.java.web_ecommerce_spring.controllers.customer;

import com.java.web_ecommerce_spring.domain.Product;
import com.java.web_ecommerce_spring.repositorys.ProductRepository;
import com.java.web_ecommerce_spring.services.CategoryService;
import com.java.web_ecommerce_spring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/public")
public class HomeController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @ModelAttribute
    public void addModel(Model model)
    {
        model.addAttribute("categories",categoryService.getAll());
    }

    @GetMapping(value = "/home")
    public ModelAndView index(Principal principal){
        ModelAndView mv = new ModelAndView("public/home");
        Sort sort = Sort.by("viewCount").descending();
        Sort sort1 = Sort.by("createdAt").descending();
        List<Product>  productTops = productService.getTopProduct(sort).stream().limit(10).collect(Collectors.toList());
        List<Product>  productTopCreates = productService.getTopProduct(sort1).stream().limit(5).collect(Collectors.toList());
        List<Product>  productDiscounts = productService.getProductByDiscount(25,sort).stream().limit(10).collect(Collectors.toList());
        mv.addObject("topProduct",productTops);
        mv.addObject("productDiscounts",productDiscounts);
        mv.addObject("productTopCreates",productTopCreates);

        return mv;
    }
}
