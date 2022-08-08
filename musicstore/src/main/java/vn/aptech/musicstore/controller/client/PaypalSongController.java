/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.controller.client;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.aptech.musicstore.entity.Order;
import vn.aptech.musicstore.entity.OrderDetail;
import vn.aptech.musicstore.entity.model.OrderOnline;
import vn.aptech.musicstore.service.OrderDetailService;
import vn.aptech.musicstore.service.OrderService;
import vn.aptech.musicstore.service.PaypalService;

/**
 *
 * @author Administrator
 */
@Controller
public class PaypalSongController {
    @Autowired
    PaypalService service;
    
    public static final String SUCCESS_URL_SONG = "cart/success";
    public static final String CANCEL_URL_SONG = "cart/cancel";

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService orderDetailService;


    @GetMapping("/paymentSong/{orderId}")
    public ModelAndView home(@PathVariable int orderId) {
        ModelAndView mv = new ModelAndView("public/payment");
        Order order = orderService.findOrderById(orderId);
        List<OrderDetail> listOrderItem = orderDetailService.findOrderDetailsByOrder(order);
        mv.addObject("order", order);
        mv.addObject("orderItems", listOrderItem);
        return mv;
    }

    @PostMapping("/cart/{orderId}")
    public String payment(@ModelAttribute("order") OrderOnline order, HttpServletRequest request, @PathVariable int orderId) {
        try {
            Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
                    order.getIntent(), order.getDescription(), applicationUrl(request) + CANCEL_URL_SONG,
                    applicationUrl(request) + SUCCESS_URL_SONG);
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    HttpSession session = request.getSession();
                    session.setAttribute("OrderId", orderId);
                    return "redirect:" + link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping(value = CANCEL_URL_SONG)
    public String cancelPay() {
        return "redirect:/";
    }

    @GetMapping(value = SUCCESS_URL_SONG)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, HttpServletRequest request, RedirectAttributes rd) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                HttpSession session = request.getSession();
                int id = (int) session.getAttribute("OrderId");
                orderService.updateIsPayment(1, id);
                return "redirect:/public/user/order";
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
}
