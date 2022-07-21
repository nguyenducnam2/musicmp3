package com.java.web_ecommerce_spring.controllers.admin;

import com.java.web_ecommerce_spring.domain.Role;
import com.java.web_ecommerce_spring.domain.User;
import com.java.web_ecommerce_spring.serviceImpls.UserServiceImpl;
import com.java.web_ecommerce_spring.services.RoleService;
import com.java.web_ecommerce_spring.utils.EncrytedPasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminUserController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    RoleService roleService;

    @GetMapping({ "/employee"})
    public ModelAndView index(String msg, Principal principal)
    {
        List<User> list = userService.listEmployee();
        ModelAndView mv = new ModelAndView("admin/employee");
        mv.addObject("msg",msg);
        mv.addObject("list",list);
        String userName = principal.getName();
        mv.addObject("userName",userName);
        return mv;
    }

    @GetMapping({ "/customer"})
    public ModelAndView indexcus(String msg,Principal principal)
    {
        List<User> list = userService.listCustomer();
        ModelAndView mv = new ModelAndView("admin/customer");
        mv.addObject("msg",msg);
        mv.addObject("list",list);
        String userName = principal.getName();
        mv.addObject("userName",userName);
        return mv;
    }

    @GetMapping("/customer/block/{id}")
    public ModelAndView block(@PathVariable int id, RedirectAttributes rd)
    {
        ModelAndView mv = new ModelAndView("redirect:/admin/customer");
        User user = userService.findUserById(id);
        user.setEnable(0);
        userService.save(user);
        mv.addObject("msg","1");
        return mv;
    }

    @GetMapping("/customer/open/{id}")
    public ModelAndView open(@PathVariable int id, RedirectAttributes rd)
    {
        ModelAndView mv = new ModelAndView("redirect:/admin/customer");
        User user = userService.findUserById(id);
        user.setEnable(1);
        userService.save(user);
        mv.addObject("msg","1");
        return mv;
    }

    @PostMapping(value = "/employee-add")
    public ModelAndView add(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("redirect:employee");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = "$2a$10$gOE9r2m8cY7iD0DQIim.UOx3qlbbMQsHrpJAF4Dmopu20vMGCAG0O";
        String phoneNumber = request.getParameter("phoneNumber");
        String userName = request.getParameter("userName");
        Role role = roleService.findRoleById(1);
        User user = new User();
        user.setFullName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setUserName(userName);
        user.setRole(role);
        user.setEnable(1);
        userService.save(user);
        mv.addObject("msg","success");
        return mv;
    }
}
