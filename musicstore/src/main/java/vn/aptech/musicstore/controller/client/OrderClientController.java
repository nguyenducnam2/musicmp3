/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.controller.client;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.entity.Order;
import vn.aptech.musicstore.entity.OrderDetail;
import vn.aptech.musicstore.service.OrderDetailService;
import vn.aptech.musicstore.service.OrderService;
import vn.aptech.musicstore.service.ProductService;

/**
 *
 * @author Dung
 */
@Controller
@RequestMapping
public class OrderClientController {
    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping("order")
    public String index(Model model, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.setAttribute("user", session.getAttribute("user"));
        model.addAttribute("user", session.getAttribute("user"));
        Account user = (Account) session.getAttribute("user");
        model.addAttribute("list", orderService.findOrderByUser(user));
        return "client/order/index";
    }

//    @PostMapping(value = "/order-edit")
//    public ModelAndView add(HttpServletRequest request){
//        ModelAndView mv = new ModelAndView("redirect:order");
//        int status = Integer.parseInt(request.getParameter("status")) ;
//        int id = Integer.parseInt(request.getParameter("id")) ;
//        if(status == 3){
//            Order obj = orderService.findOrderById(id);
//            obj.setIsPayment(1);
//            orderService.save(obj);
//            List<OrderDetail> list = orderDetailService.findOrderDetailsByOrder(obj);
//            for(OrderDetail orderDetail : list){
//                productService.update((int)orderDetail.getQuantity(),orderDetail.getProduct().getId());
//            }
//        }
//        orderService.update(status,id);
//
//        mv.addObject("msg","success");
//        return mv;
//    }

    @GetMapping(value = "/order/detail/{id}")
    public String detail(Model model, HttpServletRequest request, @PathVariable int id){
        HttpSession session = request.getSession();
        session.setAttribute("user", session.getAttribute("user"));
        model.addAttribute("user", session.getAttribute("user"));
        Order order=orderService.findOrderById(id);
        List<OrderDetail> list = orderDetailService.findOrderDetailsByOrder(order);
        model.addAttribute("order", order);
        model.addAttribute("list", list);
        return "client/order/detail";
    }
    
    @GetMapping("order/delete/{id}")
    public String delete(Model model, HttpServletRequest request, @PathVariable int id, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size)
    {
        orderService.deleteById(id);
        HttpSession session = request.getSession();
        session.setAttribute("user", session.getAttribute("user"));
        model.addAttribute("user", session.getAttribute("user"));
        Account user = (Account) session.getAttribute("user");
        model.addAttribute("list", orderService.findOrderByUser(user));
        return "client/order/index";
    }
}
