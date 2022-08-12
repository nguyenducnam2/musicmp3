/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.controller.client;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.entity.CartItem;
import vn.aptech.musicstore.entity.DownloadAllowed;
import vn.aptech.musicstore.entity.Order;
import vn.aptech.musicstore.entity.OrderDetail;
import vn.aptech.musicstore.entity.SongOrder;
import vn.aptech.musicstore.entity.SongOrderDetail;
import vn.aptech.musicstore.entity.model.OrderOnline;
import vn.aptech.musicstore.service.CartItemService;
import vn.aptech.musicstore.service.CartService;
import vn.aptech.musicstore.service.DownloadAllowedService;
import vn.aptech.musicstore.service.OrderDetailService;
import vn.aptech.musicstore.service.OrderService;
import vn.aptech.musicstore.service.PaypalService;
import vn.aptech.musicstore.service.SongOrderDetailService;
import vn.aptech.musicstore.service.SongOrderService;

/**
 *
 * @author Administrator
 */
@Controller
public class PaypalController {
    
    @Autowired
    PaypalService service;
    
    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";
    
    @Autowired
    OrderService orderService;
    
    @Autowired
    OrderDetailService orderDetailService;
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private CartItemService cartItemService;
    
    @Autowired
    private DownloadAllowedService downService;
    
    @Autowired
    private SongOrderService songOrderService;
    
    @Autowired
    private SongOrderDetailService songOrderDetailService;
    
    @GetMapping("/payment/{orderId}")
    public ModelAndView home(@PathVariable int orderId) {
        ModelAndView mv = new ModelAndView("public/payment");
        Order order = orderService.findOrderById(orderId);
        List<OrderDetail> listOrderItem = orderDetailService.findOrderDetailsByOrder(order);
        mv.addObject("order", order);
        mv.addObject("orderItems", listOrderItem);
        return mv;
    }
    
    @GetMapping("/pay/{orderId}")
    public String payment(@ModelAttribute("order") OrderOnline order, HttpServletRequest request, @PathVariable Double orderId) {
        try {
            Payment payment = service.createPayment(orderId, order.getCurrency(), order.getMethod(),
                    order.getIntent(), null, applicationUrl(request) + CANCEL_URL,
                    applicationUrl(request) + SUCCESS_URL);
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    HttpSession session = request.getSession();
                    session.setAttribute("OrderId", orderId);
                    System.out.println("succcccc" + orderId);
                    return "redirect:" + link.getHref();
                }
            }
            
        } catch (PayPalRESTException e) {
            System.out.println("exceeeg" + orderId);
            e.printStackTrace();
        }
        return "redirect:/";
    }
    
    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "redirect:/";
    }
    
    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, HttpServletRequest request, RedirectAttributes rd) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                HttpSession session = request.getSession();
                int id = (int) session.getAttribute("OrderId");
                orderService.updateIsPayment(1, id);
                return "redirect:/";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }
    
    private String applicationUrl(HttpServletRequest request) {
        return "http://"
                + request.getServerName()
                + ":"
                + request.getServerPort()
                + request.getContextPath();
    }

    // paypal for Song
    @GetMapping("/song/pay")
    public String paymentSong(HttpServletRequest request, @RequestParam("total") Double total, @RequestParam("cartId") int cartId) {
        try {
            Payment payment = service.createPayment(total, "USD", "paypal",
                    "sale", null, "http://localhost:8080/song/cancel",
                    "http://localhost:8080/song/success");
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    HttpSession session = request.getSession();
                    session.setAttribute("cartId", cartId);
                    session.setAttribute("total", total);
                    return "redirect:" + link.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
    
    @GetMapping("/song/success")
    public String successPaySong(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, HttpServletRequest request, RedirectAttributes rd) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                HttpSession session = request.getSession();
                int cartId = (int) session.getAttribute("cartId");
                Account acc = (Account) session.getAttribute("user");
                SongOrder order = new SongOrder();
                order.setPayment("Paypal");
                order.setStatus("Success");
                order.setTotal((Double) session.getAttribute("total"));
                order.setAccountId(acc.getId());
                order.setAccount(acc);
                order = songOrderService.save(order);
                for (CartItem item : cartItemService.findByCartId(cartId)) {
                    DownloadAllowed obj = new DownloadAllowed();
                    SongOrderDetail orderdetail = new SongOrderDetail();
                    orderdetail.setSongId(item.getSongId());
                    orderdetail.setSong(item.getSong());
                    orderdetail.setSongOrderId(order.getId());
                    orderdetail.setSongOrder(order);
                    obj.setAccountId(acc.getId());
                    obj.setAccount(acc);
                    obj.setSongId(item.getSong().getId());
                    obj.setSong(item.getSong());
                    songOrderDetailService.save(orderdetail);
                    downService.save(obj);
                    cartItemService.delete(item);
                }
                cartService.delete(cartService.findById(cartId).get());
                session.removeAttribute("cartId");
                session.removeAttribute("total");
                return "client/song/success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }
    
    @GetMapping("/song/cancel")
    public String cancelSongPay(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int cartId = (int) session.getAttribute("cartId");
        Account acc = (Account) session.getAttribute("user");
        SongOrder order = new SongOrder();
        order.setPayment("Paypal");
        order.setStatus("Canceled");
        order.setTotal((Double) session.getAttribute("total"));
        order.setAccountId(acc.getId());
        order.setAccount(acc);
        order = songOrderService.save(order);
        for (CartItem item : cartItemService.findByCartId(cartId)) {
            SongOrderDetail orderdetail = new SongOrderDetail();
            orderdetail.setSongId(item.getSongId());
            orderdetail.setSong(item.getSong());
            orderdetail.setSongOrderId(order.getId());
            orderdetail.setSongOrder(order);
            songOrderDetailService.save(orderdetail);
        }
        return "redirect:/";
    }
}
