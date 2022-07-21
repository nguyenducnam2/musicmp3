package com.java.web_ecommerce_spring.controllers.admin;

import com.java.web_ecommerce_spring.utils.EncrytedPasswordUtils;
import com.java.web_ecommerce_spring.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.security.Principal;

@Controller
public class AdminAuthenticationController {

    EncrytedPasswordUtils a = new EncrytedPasswordUtils();

    @GetMapping("/auth/login")
    public ModelAndView login(@RequestParam(required = false,name = "error") String error){
        ModelAndView mv = new ModelAndView("admin/login");
        if("error".equalsIgnoreCase(error)){
            mv.addObject("error","Đăng nhập thất bại");
        }
        System.out.println(a.encrytePassword("123456789"));
        return mv;
    }
    @PostMapping("/auth/login")
    public ModelAndView login1(@RequestParam(required = false,name = "error") String error){
        ModelAndView mv = new ModelAndView("redirect:/admin/home");
        return mv;
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public ModelAndView logoutSuccessfulPage() {
        ModelAndView mv = new ModelAndView("admin/login");
        return mv;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Chào " + principal.getName() //
                    + "<br> Bạn không có quyền truy cập vào trang này!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }
}
