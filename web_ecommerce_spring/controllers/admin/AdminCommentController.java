package com.java.web_ecommerce_spring.controllers.admin;

import com.java.web_ecommerce_spring.constans.CommonConstants;
import com.java.web_ecommerce_spring.domain.Comment;
import com.java.web_ecommerce_spring.domain.Role;
import com.java.web_ecommerce_spring.domain.User;
import com.java.web_ecommerce_spring.serviceImpls.CommentServiceImpl;
import com.java.web_ecommerce_spring.serviceImpls.UserServiceImpl;
import com.java.web_ecommerce_spring.services.RoleService;
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
public class AdminCommentController {
    @Autowired
    CommentServiceImpl commentService;

    @GetMapping({ "/comment"})
    public ModelAndView index(String msg, Principal principal)
    {
        List<Comment> list = commentService.findAll();
        ModelAndView mv = new ModelAndView("admin/comment");
        mv.addObject("msg",msg);
        mv.addObject("list",list);
        String userName = principal.getName();
        mv.addObject("userName",userName);
        return mv;
    }

    @GetMapping("/comment/delete/{id}")
    public ModelAndView delete(@PathVariable int id, RedirectAttributes rd)
    {
        ModelAndView mv = new ModelAndView("redirect:/admin/comment");
        commentService.delete(id);
        mv.addObject("msg","1");
        return mv;
    }

}
