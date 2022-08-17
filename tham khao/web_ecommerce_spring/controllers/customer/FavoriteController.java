package com.java.web_ecommerce_spring.controllers.customer;

import com.java.web_ecommerce_spring.constans.CommonConstants;
import com.java.web_ecommerce_spring.domain.*;
import com.java.web_ecommerce_spring.services.CategoryService;
import com.java.web_ecommerce_spring.services.FavoriteService;
import com.java.web_ecommerce_spring.services.ProductService;
import com.java.web_ecommerce_spring.utils.Middleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("public")
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @Autowired
    ProductService productService;

    @Autowired
    MessageSource messageSource;

    Middleware middleware = new Middleware();

    @GetMapping(value = "/favorite")
    public ModelAndView index(HttpServletRequest request){
        User user = middleware.middlewareUser(request);
        List<Favorite> list = favoriteService.findFavoriteByUser(user);
        ModelAndView mv = new ModelAndView("public/favorite");
        mv.addObject("list",list);
        return mv;
    }

    @GetMapping("/favorite/add/{id}")
    public ModelAndView delete(@PathVariable int id,RedirectAttributes rd,HttpServletRequest request)
    {
        String url = "redirect:/public/product/detail/" + id;
        ModelAndView mv = new ModelAndView(url);
        User user = middleware.middlewareUser(request);
        Product product = productService.getProductById(id);
        Favorite check = favoriteService.findFavoriteByUserAndProduct(user,product);
        if (check != null){
            rd.addFlashAttribute("failcheck","failcheck");
        }
        else{
            Favorite favorite = new Favorite();
            favorite.setProduct(product);
            favorite.setUser(user);
            favorite.setCreatedAt(java.time.LocalDate.now().toString());
            favoriteService.save(favorite);
            rd.addFlashAttribute("msgsc","msgsc");
        }
        return mv;
    }

    @GetMapping("/favorite/delete/{id}")
    public ModelAndView delete(@PathVariable int id,RedirectAttributes rd)
    {
        ModelAndView mv = new ModelAndView("redirect:/public/favorite");
        favoriteService.deleteById(id);
        rd.addFlashAttribute(CommonConstants.SUCCESS, "SUCCESS");
        return mv;
    }
}
