/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.controller.client;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.UUID;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.entity.CartItem;
import vn.aptech.musicstore.entity.DownloadAllowed;
import vn.aptech.musicstore.entity.Order;
import vn.aptech.musicstore.entity.OrderDetail;
import vn.aptech.musicstore.entity.Product;
import vn.aptech.musicstore.entity.SongOrder;
import vn.aptech.musicstore.entity.SongOrderDetail;
import vn.aptech.musicstore.entity.UpgradeVipOrderDetails;
import vn.aptech.musicstore.service.CartItemService;
import vn.aptech.musicstore.service.CartService;
import vn.aptech.musicstore.service.DownloadAllowedService;
import vn.aptech.musicstore.service.OrderDetailService;
import vn.aptech.musicstore.service.OrderService;
import vn.aptech.musicstore.service.PaypalService;
import vn.aptech.musicstore.service.ShoppingCartService;
import vn.aptech.musicstore.service.SongOrderDetailService;
import vn.aptech.musicstore.service.SongOrderService;
import vn.aptech.musicstore.service.UpgradeVipOrderDetailsService;
import vn.aptech.musicstore.service.impl.AccountServiceImpl;

/**
 *
 * @author Administrator
 */
@Controller
public class PaypalController {

    @Autowired
    PaypalService service;

//    public static final String SUCCESS_URL = "cart/success";
//    public static final String CANCEL_URL = "cart/cancel";
    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private DownloadAllowedService downService;

    @Autowired
    private SongOrderService songOrderService;

    @Autowired
    private SongOrderDetailService songOrderDetailService;

    @Autowired
    private UpgradeVipOrderDetailsService upgradeVipOrderDetailsService;

//    @GetMapping("/payment/{orderId}")
//    public ModelAndView home(@PathVariable int orderId) {
//        ModelAndView mv = new ModelAndView("public/payment");
//        Order order = orderService.findOrderById(orderId);
//        List<OrderDetail> listOrderItem = orderDetailService.findOrderDetailsByOrder(order);
//        mv.addObject("order", order);
//        mv.addObject("orderItems", listOrderItem);
//        return mv;
//    }
    @GetMapping("/cart/pay")
    public String payment(HttpServletRequest request, @RequestParam("amount") Double amount, @RequestParam("orderId") int orderId) {
        try {
            Payment payment = service.createPayment(amount, "USD", "paypal",
                    "sale", null, "http://localhost:8080/cart/cancel",
                    "http://localhost:8080/cart/success");
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    HttpSession session = request.getSession();
                    session.setAttribute("orderId", orderId);
                    session.setAttribute("amount", amount);
                    return "redirect:" + link.getHref();
                }
            }

        } catch (PayPalRESTException e) {
            System.out.println("exceeeg" + orderId);
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/cart/cancel")
    public String cancelPay() {
        return "redirect:/";
    }

    @GetMapping("/cart/success")
    public String successPay(HttpServletRequest request) {
//        try {
//            Payment payment = service.executePayment(paymentId, payerId);
//            System.out.println(payment.toJSON());
//            if (payment.getState().equals("approved")) {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        Order order = new Order();
        List<Product> newList = shoppingCartService.getProducts().stream().collect(toList());
        order.setAddress(user.getAddress());
        order.setPhoneNumber(user.getPhone());
        order.setUser(user);
        order.setIsPayment(0);
        order.setStatus(1);
        order.setDescription("Paypal");
        order.setOrderDate(java.time.LocalDate.now().toString());
        order.setAmount((float) shoppingCartService.getAmount());
        orderService.save(order);
        for (int i = 0; i < newList.size(); i++) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(newList.get(i));
            detail.setQuantity(newList.get(i).getQuantity());
            detail.setUnitPrice(newList.get(i).getPrice());
            orderDetailService.save(detail);
        }
        shoppingCartService.clear();
        return "client/cart/success";
//            }
//        } catch (PayPalRESTException e) {
//            System.out.println(e.getMessage());
//        }
//        return "redirect:/";
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

    @GetMapping("/user/pay")
    public String paymentUpgradeToVip(HttpServletRequest request, @RequestParam("total") Double total, @RequestParam("duration") int duration) {
        try {
            Payment payment = service.createPayment(total, "USD", "paypal",
                    "sale", null, "http://localhost:8080/user/cancel",
                    "http://localhost:8080/user/success");
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    HttpSession session = request.getSession();
                    session.setAttribute("duration", duration);
                    session.setAttribute("total2", total);
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

    @GetMapping("/user/success")
    public String successPayVip(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, HttpServletRequest request, RedirectAttributes rd) throws PayPalRESTException {
        Payment payment = service.executePayment(paymentId, payerId);
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("user");
        int duration = (int) session.getAttribute("duration");
        try {
            if (payment.getState().equals("approved")) {
                SongOrder order = new SongOrder();
                order.setPayment("Paypal");
                order.setStatus("Success");
                order.setTotal((Double) session.getAttribute("total2"));
                order.setAccountId(acc.getId());
                order.setAccount(acc);
                songOrderService.save(order);

                UpgradeVipOrderDetails orderdetail = new UpgradeVipOrderDetails();
                orderdetail.setDuration(duration);
                orderdetail.setTotal((Double) session.getAttribute("total2"));
                orderdetail.setUserId(acc.getId());
                orderdetail.setAcc(acc);
                upgradeVipOrderDetailsService.save(orderdetail);

                String token = UUID.randomUUID().toString();
                Optional<Account> user = userService.findByUsername(acc.getUsername());
                userService.createVipTokenForUser(user.get(), token, duration * 24 * 60);
                rd.addFlashAttribute("mess", "Successfully");
                session.removeAttribute("duration");
                session.removeAttribute("total2");
                session.setAttribute("user", user.get());
//                session.setAttribute("duration", orderdetail.getDuration());
                return "redirect:/user/profile/" + user.get().getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        rd.addFlashAttribute("mess", "Failed");
        return "redirect:/user/profile/" + acc.getId();
    }

    @GetMapping("/user/cancel")
    public String cancelPayVip(HttpServletRequest request, RedirectAttributes rd) {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("user");
        int duration = (int) session.getAttribute("duration");

        SongOrder order = new SongOrder();
        order.setPayment("Paypal");
        order.setStatus("Canceled");
        order.setTotal((Double) session.getAttribute("total2"));
        order.setAccountId(acc.getId());
        order.setAccount(acc);
        songOrderService.save(order);

        UpgradeVipOrderDetails orderdetail = new UpgradeVipOrderDetails();
        orderdetail.setDuration(duration);
        orderdetail.setTotal((Double) session.getAttribute("total2"));
        orderdetail.setUserId(acc.getId());
        orderdetail.setAcc(acc);
        upgradeVipOrderDetailsService.save(orderdetail);

        session.removeAttribute("duration");
        session.removeAttribute("total2");
        rd.addFlashAttribute("mess", "Failed");
        return "redirect:/user/profile/" + acc.getId();
    }

    @Autowired
    private AccountServiceImpl userService;
}
