package com.java.web_ecommerce_spring.controllers.admin;

import com.java.web_ecommerce_spring.domain.Order;
import com.java.web_ecommerce_spring.domain.Product;
import com.java.web_ecommerce_spring.domain.User;
import com.java.web_ecommerce_spring.serviceImpls.OrderServiceImpl;
import com.java.web_ecommerce_spring.serviceImpls.ProductServiceImpl;
import com.java.web_ecommerce_spring.serviceImpls.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class AdminHomeController {
    @Autowired
    ProductServiceImpl productService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    OrderServiceImpl orderService
;
    @GetMapping(value = "/admin/home")
    public ModelAndView index(Principal principal){
        ModelAndView mv = new ModelAndView("admin/index");
        String userName = principal.getName();
        List<Product> listC = productService.getAll();
        List<User> listU = userService.listCustomer();
        List<Order> listB = orderService.findAll();
        float sum = 0;
        for (Order order : listB) {
	if(order.getIsPayment() == 1){
            sum = sum + order.getAmount();
	}
        }
        //thongke
        List<Order> list1 = orderService.getAmount(1);
        float t1 = 0;
        for (Order order : list1) {
            t1 = t1 + order.getAmount();
        }
        List<Order> list2 = orderService.getAmount(2);
        float t2 = 0;
        for (Order order : list2) {
            t2 = t2 + order.getAmount();
        }
        List<Order> list3 = orderService.getAmount(3);
        float t3 = 0;
        for (Order order : list3) {
            t3 = t3 + order.getAmount();
        }
        List<Order> list4 = orderService.getAmount(4);
        float t4 = 0;
        for (Order order : list4) {
            t4 = t4 + order.getAmount();
        }
        List<Order> list5 = orderService.getAmount(5);
        float t5 = 0;
        for (Order order : list5) {
            t5 = t5 + order.getAmount();
        }
        List<Order> list6 = orderService.getAmount(6);
        float t6 = 0;
        for (Order order : list6) {
            t6 = t6 + order.getAmount();
        }
        List<Order> list7 = orderService.getAmount(7);
        float t7 = 0;
        for (Order order : list7) {
            t7 = t7 + order.getAmount();
        }
        List<Order> list8 = orderService.getAmount(8);
        float t8 = 0;
        for (Order order : list8) {
            t8 = t8 + order.getAmount();
        }
        List<Order> list9 = orderService.getAmount(9);
        float t9 = 0;
        for (Order order : list9) {
            t9 = t9 + order.getAmount();
        }
        List<Order> list10 = orderService.getAmount(10);
        float t10 = 0;
        for (Order order : list10) {
            t10 = t10 + order.getAmount();
        }
        List<Order> list11 = orderService.getAmount(11);
        float t11 = 0;
        for (Order order : list11) {
            t11 = t11 + order.getAmount();
        }
        List<Order> list12 = orderService.getAmount(12);
        float t12 = 0;
        for (Order order : list12) {
            t12 = t12 + order.getAmount();
        }
        int countC = listC.size();
        int countU = listU.size();
        int countB = listB.size();
        int m1 = (int)t1;
        int m2 = (int)t2;
        int m3 = (int)t3;
        int m4 = (int)t4;
        int m5 = (int)t5;
        int m6 = (int)t6;
        int m7 = (int)t7;
        int m8 = (int)t8;
        int m9 = (int)t9;
        int m10 = (int)t10;
        int m11 = (int)t11;
        int m12 = (int)t12;
        mv.addObject("m1", m1);
        mv.addObject("m2", m2);
        mv.addObject("m3", m3);
        mv.addObject("m4", m4);
        mv.addObject("m5", m5);
        mv.addObject("m6", m6);
        mv.addObject("m7", m7);
        mv.addObject("m8", m8);
        mv.addObject("m9", m9);
        mv.addObject("m10", m10);
        mv.addObject("m11", m11);
        mv.addObject("m12", m12);
        mv.addObject("countC",countC);
        mv.addObject("countU",countU);
        mv.addObject("countB",countB);
        mv.addObject("sum",sum);
        mv.addObject("userName",userName);
        return mv;
    }

}
