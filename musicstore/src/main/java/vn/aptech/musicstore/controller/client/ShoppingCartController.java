/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.controller.client;

import java.util.Collection;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.aptech.musicstore.entity.Product;
import vn.aptech.musicstore.service.OrderDetailService;
import vn.aptech.musicstore.service.OrderService;
import vn.aptech.musicstore.service.ProductService;
import vn.aptech.musicstore.service.ShoppingCartService;

/**
 *
 * @author Dung
 */
@Controller
@RequestMapping("cart")
public class ShoppingCartController {

    @Autowired
    private ProductService service;

    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService detailService;

    @GetMapping("index")
    public String index(Model model, HttpServletRequest request) {
        Collection<Product> products = cartService.getProducts();
        HttpSession session = request.getSession();
        session.setAttribute("user", session.getAttribute("user"));
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("products", products);
        model.addAttribute("amount", cartService.getAmount());
        model.addAttribute("count", cartService.getCount());
        return "client/cart/index";
    }

    @GetMapping("add/{id}")
    public String add(Model model, HttpServletRequest request, @PathVariable("id") Integer id) {
        Optional<Product> product = service.findById(id);
        HttpSession session = request.getSession();
        session.setAttribute("user", session.getAttribute("user"));
        model.addAttribute("user", session.getAttribute("user"));
        Product products = product.get();
        if (products != null) {
            products.setQuantity(1);
            cartService.add(products);
        }
        return "redirect:/cart/index";
    }

    @GetMapping("remove/{id}")
    public String remove(Model model, HttpServletRequest request, @PathVariable("id") Integer id) {
        HttpSession session = request.getSession();
        session.setAttribute("user", session.getAttribute("user"));
        model.addAttribute("user", session.getAttribute("user"));
        cartService.remove(id);
        return "redirect:/cart/index";
    }

    @PostMapping("update")
    public String update(Model model, HttpServletRequest request, @RequestParam("id") Integer id, @RequestParam("quantity") int quantity) {
        HttpSession session = request.getSession();
        session.setAttribute("user", session.getAttribute("user"));
        model.addAttribute("user", session.getAttribute("user"));
        Optional<Product> product = service.findById(id);
        Product products = product.get();
        if(quantity > products.getQuantity()){
            quantity = products.getQuantity();
        }
        cartService.update(id, quantity);
        return "redirect:/cart/index";
    }

    @GetMapping("clear")
    public String clear(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user", session.getAttribute("user"));
        model.addAttribute("user", session.getAttribute("user"));
        cartService.clear();
        return "redirect:/cart/index";
    }

    @GetMapping("checkout")
    public ModelAndView checkout(Model model, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("client/cart/checkout");
        //Collection<Product> products = cartService.getProducts();
        HttpSession session = request.getSession();
        //Order order = (Order) session.getAttribute("order");
        session.setAttribute("user", session.getAttribute("user"));
        model.addAttribute("user", session.getAttribute("user"));
//        model.addAttribute("products", products);
//        model.addAttribute("amount", cartService.getAmount());
//        model.addAttribute("count", cartService.getCount());
        
        return mv;
    }
    
//    @PostMapping("/save")
//    public String save(Model model, HttpServletRequest request, @ModelAttribute("order") Order order) {
//        HttpSession session = request.getSession();
//        session.setAttribute("user", session.getAttribute("user"));
//        model.addAttribute("user", session.getAttribute("user"));
//        Account user=(Account) session.getAttribute("user");
//        order.setUser(user);
//        order.setOrderDate(java.time.LocalDate.now().toString());
//        order.setAmount((float) cartService.getAmount());
//        order.setStatus(0);
//        order.setIsPayment(0);
//        orderService.save(order);
//        return "redirect:/order/index";
//    }
}