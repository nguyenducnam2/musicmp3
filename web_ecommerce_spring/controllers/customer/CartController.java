package com.java.web_ecommerce_spring.controllers.customer;

import com.java.web_ecommerce_spring.domain.Order;
import com.java.web_ecommerce_spring.domain.OrderDetail;
import com.java.web_ecommerce_spring.domain.Product;
import com.java.web_ecommerce_spring.domain.User;
import com.java.web_ecommerce_spring.models.ChangeNumber;
import com.java.web_ecommerce_spring.models.ItemModel;
import com.java.web_ecommerce_spring.models.OrderItem;
import com.java.web_ecommerce_spring.services.CategoryService;
import com.java.web_ecommerce_spring.services.OrderDetailService;
import com.java.web_ecommerce_spring.services.OrderService;
import com.java.web_ecommerce_spring.services.ProductService;
import com.java.web_ecommerce_spring.utils.Middleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/public/cart")
public class CartController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    MessageSource messageSource;


    Middleware middleware = new Middleware();

    @GetMapping(value = "/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("public/cart");
        mv.addObject("categories",categoryService.getAll());
        return mv;
    }

    @GetMapping(value = "/delete/{productId}")
    public ModelAndView delete(@PathVariable int productId,HttpServletRequest request){
        ModelAndView mv = new ModelAndView("redirect:/public/cart/index");
        Product product = productService.getProductById(productId);
        HttpSession session = request.getSession();
        OrderItem order = (OrderItem) session.getAttribute("order");
        List<ItemModel> listItem = order.getItemModels();
        int total = 0;
        for (int i = 0 ; i< listItem.size() ;i++){
            if (listItem.get(i).getProduct().getId() == productId) {
                listItem.remove(i);
            }
        }
        for (int i = 0 ; i< listItem.size() ;i++){
            total += (listItem.get(i).getProduct().getPrice() - (listItem.get(i).getProduct().getPrice() * listItem.get(i).getProduct().getDiscount()/ 100) )* listItem.get(i).getQuantity();
        }
        order.setItemModels(listItem);
        order.setTotal(total + 30000);
        session.setAttribute("order", order);
        return mv;
    }

    @PostMapping(value = "/add")
    public @ResponseBody int add(HttpServletRequest request){
        int quantity = 1;
        int numberCart = 0;
        int total = 0;
        HttpSession session = request.getSession();
        String productId = request.getParameter("productId");
        Product product = productService.getProductById(Integer.parseInt(productId));
        quantity = Integer.parseInt(request.getParameter("quantity"));
        if (quantity > product.getQuantity()){
            return  0;
        }
        OrderItem orderItem = new OrderItem();
        if (productId != null){
            if (session.getAttribute("order") == null) {
                List<ItemModel> listItem = new ArrayList<>();
                ItemModel item = new ItemModel(product,quantity);
                listItem.add(item);
                orderItem.setItemModels(listItem);
                session.setAttribute("order", orderItem);
            } else {
                orderItem = (OrderItem) session.getAttribute("order");
                List<ItemModel> listItem = orderItem.getItemModels();
                boolean check = false;
                for (ItemModel item:listItem) {
                    if(item.getProduct().getId()== product.getId()) {
                        item.setQuantity(item.getQuantity()+quantity);
                        check = true;
                    }
                }
                if (check == false ) {
                    ItemModel item = new ItemModel(product,quantity);
                    listItem.add(item);
                    orderItem.setItemModels(listItem);
                    session.setAttribute("order", orderItem);
                }
            }
        }
        OrderItem order1 = (OrderItem) session.getAttribute("order");
        for(ItemModel itemModel:order1.getItemModels()){
            numberCart+=1;
            total +=( itemModel.getProduct().getPrice() - (itemModel.getProduct().getPrice() * itemModel.getProduct().getDiscount()/ 100)) * itemModel.getQuantity();
        }
        orderItem.setTotal(total + 30000);
        session.setAttribute("order", orderItem);
        return numberCart;
    }

    @PostMapping("/update-number")
    public @ResponseBody ChangeNumber updateNumber(HttpServletRequest request){
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Product product = productService.getProductById(productId);
        int total = 0;
        HttpSession session = request.getSession();
        OrderItem orderItem = (OrderItem) session.getAttribute("order");
        List<ItemModel> listItem = orderItem.getItemModels();
        for (int i = 0 ; i<listItem.size() ; i++){
            if(listItem.get(i).getProduct().getId() == productId){
                listItem.get(i).setQuantity(quantity);
            }
        }
        for (int i = 0 ; i< listItem.size() ;i++){
            total += (listItem.get(i).getProduct().getPrice() - (listItem.get(i).getProduct().getPrice() * listItem.get(i).getProduct().getDiscount()/ 100)) * listItem.get(i).getQuantity();
        }
        orderItem.setItemModels(listItem);
        orderItem.setTotal(total + 30000);
        String giacu = formatter.format(product.getPrice()*quantity)+" ₫";
        String giamoi = formatter.format((product.getPrice() - (product.getPrice()* product.getDiscount()/100))*quantity)+" ₫";
        String tongtien = formatter.format(total)+" ₫";
        String tamTinh = formatter.format(total + 30000)+" ₫";
        ChangeNumber changeNumber = new ChangeNumber(giacu,giamoi,tongtien,tamTinh);
        return changeNumber;
    }

    @PostMapping("/order")
    public ModelAndView order(HttpServletRequest request, RedirectAttributes rd){
        ModelAndView mv = new ModelAndView("redirect:/public/cart/index");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String note = request.getParameter("note");
        HttpSession session = request.getSession();
        User checkAuth = middleware.middlewareUser(request);
        if (checkAuth == null){
            rd.addFlashAttribute("user_login", messageSource.getMessage("user_login", null, Locale.getDefault()));
        } else {
            OrderItem orderItem = (OrderItem) session.getAttribute("order");
            Order order = new Order();
            order.setAddress(address);
            order.setDescription(note);
            order.setAmount(orderItem.getTotal());
            order.setPhoneNumber(phoneNumber);
            order.setStatus(1);
            order.setUser(checkAuth);
            order.setIsPayment(0);
            order.setOrderDate(java.time.LocalDate.now().toString());
            orderService.save(order);

            for (int i =0 ; i< orderItem.getItemModels().size() ; i++){
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order);
                orderDetail.setProduct(orderItem.getItemModels().get(i).getProduct());
                orderDetail.setDiscount(orderItem.getItemModels().get(i).getQuantity());
                orderDetail.setUnitPrice((orderItem.getItemModels().get(i).getProduct().getPrice() - (orderItem.getItemModels().get(i).getProduct().getPrice() * orderItem.getItemModels().get(i).getProduct().getDiscount()/ 100)));
                orderDetailService.save(orderDetail);
            }
            rd.addFlashAttribute("order_success", messageSource.getMessage("order_success", null, Locale.getDefault()));
            session.setAttribute("order", null);
        }
        return mv;
    }
}
