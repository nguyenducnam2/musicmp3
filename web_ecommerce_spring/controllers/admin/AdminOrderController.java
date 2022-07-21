package com.java.web_ecommerce_spring.controllers.admin;

import com.java.web_ecommerce_spring.domain.*;
import com.java.web_ecommerce_spring.serviceImpls.OrderDetailServiceImpl;
import com.java.web_ecommerce_spring.serviceImpls.OrderServiceImpl;
import com.java.web_ecommerce_spring.serviceImpls.ProductServiceImpl;
import com.java.web_ecommerce_spring.serviceImpls.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin")
public class AdminOrderController {
    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    OrderDetailServiceImpl orderDetailService;

    @GetMapping({ "/order"})
    public ModelAndView index(String msg, Principal principal)
    {
        List<Order> list = orderService.findAll();
        ModelAndView mv = new ModelAndView("admin/order");
        mv.addObject("msg",msg);
        mv.addObject("list",list);
        String userName = principal.getName();
        mv.addObject("userName",userName);
        return mv;
    }

    @PostMapping(value = "/order-edit")
    public ModelAndView add(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("redirect:order");
        int status = Integer.parseInt(request.getParameter("status")) ;
        int id = Integer.parseInt(request.getParameter("id")) ;
        if(status == 3){
            Order obj = orderService.findOrderById(id);
            obj.setIsPayment(1);
            orderService.save(obj);
            List<OrderDetail> list = orderDetailService.findOrderDetailsByOrder(obj);
            for(OrderDetail orderDetail : list){
                productService.updatepro((int)orderDetail.getDiscount(),orderDetail.getProduct().getId());
            }
        }
        orderService.update(status,id);

        mv.addObject("msg","success");
        return mv;
    }

    @GetMapping(value = "/order/detail/{id}")
    public ModelAndView detail(@PathVariable int id){
        ModelAndView mv = new ModelAndView("admin/order-detail");
        Order obj = orderService.findOrderById(id);
        List<OrderDetail> list = orderDetailService.findOrderDetailsByOrder(obj);
        mv.addObject("order",obj);
        mv.addObject("list",list);
        return mv;
    }
}
