package com.java.web_ecommerce_spring.controllers.customer;

import com.java.web_ecommerce_spring.constans.CommonConstants;
import com.java.web_ecommerce_spring.domain.*;
import com.java.web_ecommerce_spring.serviceImpls.OrderDetailServiceImpl;
import com.java.web_ecommerce_spring.serviceImpls.OrderServiceImpl;
import com.java.web_ecommerce_spring.services.CategoryService;
import com.java.web_ecommerce_spring.services.RoleService;
import com.java.web_ecommerce_spring.services.UserService;
import com.java.web_ecommerce_spring.utils.EncrytedPasswordUtils;
import com.java.web_ecommerce_spring.utils.FileUtil;
import com.java.web_ecommerce_spring.utils.MailUtil;
import com.java.web_ecommerce_spring.utils.Middleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("public/user")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    OrderDetailServiceImpl orderDetailService;

    @Autowired
    public JavaMailSenderImpl javaMailSenderImpl;

    @Autowired
    RoleService roleService;

    Middleware middleware = new Middleware();

    @PostMapping("/register")
    public @ResponseBody String register(HttpServletRequest request){
        String fullName = request.getParameter("fullName");
        String userName = request.getParameter("userName");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        System.out.println(password);
        System.out.println(rePassword);
        if (!password.equalsIgnoreCase(rePassword)){
            return "false_repass";
        }else {
            User objCheckEmail = userService.findUserByEmail(email);
            if (Objects.nonNull(objCheckEmail)){
                return "exist";
            } else {
                User user = new User();
                Role role = roleService.findRoleById(2);
                String passwordMd5 = EncrytedPasswordUtils.md5(password);
                user.setEmail(email);
                user.setEnable(1);
                user.setFullName(fullName);
                user.setPassword(passwordMd5);
                user.setPhoneNumber(phoneNumber);
                user.setRole(role);
                userService.save(user);
                return "true";
            }
        }
    }

    @PostMapping("/login")
    public @ResponseBody String login(HttpServletRequest request){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordMd5 = EncrytedPasswordUtils.md5(password);
        User user = userService.findUserByEmailAndPassword(email,passwordMd5);
        if (!Objects.nonNull(user)){
            return "false_user";
        } else {
//            if (user.getEnable() == 0){
//                return  "false_verify";
//            } else {
//                HttpSession session = request.getSession();
//                session.setAttribute(CommonConstants.SESSION_USER, user);
//                return  "true";
//            }
            HttpSession session = request.getSession();
            session.setAttribute(CommonConstants.SESSION_USER, user);
            return  "true";
        }
    }

    @PostMapping("/changePassword")
    public ModelAndView changePassword(HttpServletRequest request,RedirectAttributes rd){
        ModelAndView mv = new ModelAndView("redirect:profile");
        String oldpassword = request.getParameter("password");
        String passwordMd5 = EncrytedPasswordUtils.md5(oldpassword);
        String newpassword = request.getParameter("newpassword");
        String newpass = EncrytedPasswordUtils.md5(newpassword);
        User user = middleware.middlewareUser(request);
        if(user.getPassword().equals(passwordMd5)){
            user.setPassword(newpass);
            userService.save(user);
            rd.addFlashAttribute(CommonConstants.SUCCESS, "SUCCESS");
        }
        else{
            rd.addFlashAttribute(CommonConstants.FAIL, "FAIL");
        }
        return mv;
    }

    @PostMapping("/updateProfile")
    public ModelAndView updateProfile(HttpServletRequest request,RedirectAttributes rd){
        ModelAndView mv = new ModelAndView("redirect:profile");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        User user = middleware.middlewareUser(request);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        userService.save(user);
        rd.addFlashAttribute(CommonConstants.SUCCESS, "SUCCESS");
        return mv;
    }

    @GetMapping("/verify/{email}")
    public ModelAndView verify(@PathVariable String email, RedirectAttributes rd ){
        User user = userService.findUserByEmail(email);
        user.setEnable(1);
        userService.save(user);
        ModelAndView mv = new ModelAndView("redirect:/public/home");
        rd.addFlashAttribute(CommonConstants.VERIFY, "verify_success");
        return mv;
    }

    @GetMapping(value = "/logout")
    public ModelAndView getLogout(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("redirect:/public/home");
        HttpSession session = request.getSession(false);
        session.invalidate();
        return mv;
    }

    @GetMapping({ "/order"})
    public ModelAndView order(HttpServletRequest request)
    {
        User user = middleware.middlewareUser(request);
        List<Order> list = orderService.findOrderByUser(user);
        ModelAndView mv = new ModelAndView("public/order");
        mv.addObject("categories",categoryService.getAll());
        mv.addObject("list",list);
        return mv;
    }

    @GetMapping({ "/profile"})
    public ModelAndView profile(HttpServletRequest request)
    {
        User user = middleware.middlewareUser(request);
        ModelAndView mv = new ModelAndView("public/profile");
        mv.addObject("categories",categoryService.getAll());
        mv.addObject("user",user);
        return mv;
    }

    @GetMapping("/order/cancel/{id}")
    public ModelAndView delete(@PathVariable int id,RedirectAttributes rd,HttpServletRequest request)
    {
        ModelAndView mv = new ModelAndView("redirect:/public/user/order");
        int status = 0 ;
        orderService.update(status,id);
        rd.addFlashAttribute(CommonConstants.SUCCESS, "SUCCESS");
        return mv;
    }

    @GetMapping(value = "/order/detail/{id}")
    public ModelAndView detail(@PathVariable int id){
        ModelAndView mv = new ModelAndView("public/order-detail");
        Order obj = orderService.findOrderById(id);
        List<OrderDetail> list = orderDetailService.findOrderDetailsByOrder(obj);
        mv.addObject("order",obj);
        mv.addObject("categories",categoryService.getAll());
        mv.addObject("list",list);
        return mv;
    }
}
