package com.java.web_ecommerce_spring.controllers.admin;

import com.java.web_ecommerce_spring.constans.CommonConstants;
import com.java.web_ecommerce_spring.domain.Category;
import com.java.web_ecommerce_spring.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Locale;
import java.util.Objects;

@Controller
@RequestMapping("admin")
public class AdminCategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    MessageSource messageSource;

    @GetMapping(value = "/category")
    public ModelAndView index(Principal principal){
        ModelAndView mv = new ModelAndView("admin/category");
        mv.addObject("categorys" ,categoryService.getAll());
        mv.addObject("categorycha" ,categoryService.listCha());
        String userName = principal.getName();
        mv.addObject("userName",userName);
        return mv;
    }

    @PostMapping(value = "/category/add")
    public ModelAndView index(@ModelAttribute("category") Category category, RedirectAttributes rd){
        ModelAndView mv = new ModelAndView("redirect:/admin/category");
        Category objCheck = categoryService.getCategoryByName(category.getName());
        if (Objects.isNull(objCheck)) {
            Category obj = categoryService.save(category);
            if (!Objects.isNull(obj)) {
                rd.addFlashAttribute(CommonConstants.MSG, "1");
            } else {
                rd.addFlashAttribute(CommonConstants.MSG, "2");
            }
        } else {
            rd.addFlashAttribute(CommonConstants.MSG, "2");
        }
        return mv;
    }

    @GetMapping("/category/delete/{id}")
    public ModelAndView delete(@PathVariable int id,RedirectAttributes rd)
    {
        ModelAndView mv = new ModelAndView("redirect:/admin/category");
        categoryService.delete(id);
        rd.addFlashAttribute(CommonConstants.MSG, "1");
        return mv;
    }

    @PostMapping("/category/update/{categoryId}")
    public ModelAndView delete(@PathVariable int categoryId,RedirectAttributes rd,@ModelAttribute("category") Category category)
    {
        ModelAndView mv = new ModelAndView("redirect:/admin/category");

        String nameCategoryCurrent = categoryService.getCategoryById(categoryId).getName();
        if (nameCategoryCurrent.equalsIgnoreCase(category.getName())) {
            categoryService.save(category);
            rd.addFlashAttribute(CommonConstants.MSG, "1");
        } else {
            Category checkExistName = categoryService.getCategoryByName(category.getName());
            if (Objects.isNull(checkExistName)){
                categoryService.save(category);
                rd.addFlashAttribute(CommonConstants.MSG, "1");
            } else {
                rd.addFlashAttribute(CommonConstants.MSG, "2");
            }
        }

        return mv;
    }
}
