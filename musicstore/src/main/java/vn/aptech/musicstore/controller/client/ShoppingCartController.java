///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package vn.aptech.musicstore.controller.client;
//
//import static java.nio.file.Files.list;
//import java.util.Collection;
//import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import vn.aptech.musicstore.entity.Product;
//import vn.aptech.musicstore.service.ProductService;
//import vn.aptech.musicstore.service.ShoppingCartService;
//
///**
// *
// * @author Dung
// */
//@Controller
//@RequestMapping("cart")
//public class ShoppingCartController {
//
//    @Autowired
//    ProductService service;
//
//    @Autowired
//    ShoppingCartService cartService;
//
//    @GetMapping("index")
//    public String index(Model model) {
//        Collection<Product> products = cartService.getProducts();
//        model.addAttribute("products", products);
//        model.addAttribute("amount", cartService.getAmount());
//        model.addAttribute("count", cartService.getCount());
//        return "client/cart/index";
//    }
//
//    @GetMapping("add/{id}")
//    public String add(@PathVariable("id") Integer id) {
//        Optional<Product> product = service.findById(id);
//        Product products = product.get();
//        if (products != null) {
////            products.setQuantity(1);
//            cartService.add(products);
//        }
//        return "redirect:/cart/index";
//    }
//
//    @GetMapping("remove/{id}")
//    public String remove(@PathVariable("id") Integer id) {
//        cartService.remove(id);
//        return "redirect:/cart/index";
//    }
//
//    @PostMapping("update")
//    public String update(@RequestParam("id") Integer id, @RequestParam("quantity") int quantity) {
//        cartService.update(id, quantity);
//        return "redirect:/cart/index";
//    }
//
//    @GetMapping("clear")
//    public String clear() {
//        cartService.clear();
//        return "redirect:/cart/index";
//    }
//
////    @Autowired
////    ProductService productService;
////
//    @Autowired
//    CategoryService categoryService;
//
//    @Autowired
//    OrderService orderService;
//
//    @Autowired
//    OrderDetailService orderDetailService;
//
//    @Autowired
//    MessageSource messageSource;
//
//
//    Middleware middleware = new Middleware();
//
//    @GetMapping(value = "/index")
//    public ModelAndView index(){
//        ModelAndView mv = new ModelAndView("client/cart/index");
//        mv.addObject("products", productService.findAll());
//        return mv;
//    }
//
//    @GetMapping(value = "/delete/{productId}")
//    public ModelAndView delete(@PathVariable int productId,HttpServletRequest request){
//        ModelAndView mv = new ModelAndView("redirect:/cart/index");
//        Optional<Product> product = productService.findById(productId);
//        Product products=product.get();
//        HttpSession session = request.getSession();
//        OrderItem order = (OrderItem) session.getAttribute("order");
//        List<ItemModel> listItem = order.getItemModels();
//        int total = 0;
//        for (int i = 0 ; i< listItem.size() ;i++){
//            if (listItem.get(i).getProduct().getId() == productId) {
//                listItem.remove(i);
//            }
//        }
//        for (int i = 0 ; i< listItem.size() ;i++){
//            total += listItem.get(i).getProduct().getPrice()* listItem.get(i).getQuantity();
//        }
//        order.setItemModels(listItem);
//        order.setTotal(total + 30000);
//        session.setAttribute("order", order);
//        return mv;
//    }
//
//    @PostMapping(value = "/add")
//    public @ResponseBody int add(HttpServletRequest request){
//        int quantity = 1;
//        int numberCart = 0;
//        int total = 0;
//        HttpSession session = request.getSession();
//        String productId = request.getParameter("productId");
//        Optional<Product> product = productService.findById(Integer.parseInt(productId));
//        Product products=product.get();
//        quantity = Integer.parseInt(request.getParameter("quantity"));
//        if (quantity > products.getQuantity()){
//            return  0;
//        }
//        OrderItem orderItem = new OrderItem();
//        if (productId != null){
//            if (session.getAttribute("order") == null) {
//                List<ItemModel> listItem = new ArrayList<>();
//                ItemModel item = new ItemModel(products, quantity);
//                listItem.add(item);
//                orderItem.setItemModels(listItem);
//                session.setAttribute("order", orderItem);
//            } else {
//                orderItem = (OrderItem) session.getAttribute("order");
//                List<ItemModel> listItem = orderItem.getItemModels();
//                boolean check = false;
//                for (ItemModel item:listItem) {
//                    if(Objects.equals(item.getProduct().getId(), products.getId())) {
//                        item.setQuantity(item.getQuantity()+quantity);
//                        check = true;
//                    }
//                }
//                if (check == false ) {
//                    ItemModel item = new ItemModel(products, quantity);
//                    listItem.add(item);
//                    orderItem.setItemModels(listItem);
//                    session.setAttribute("order", orderItem);
//                }
//            }
//        }
//        OrderItem order1 = (OrderItem) session.getAttribute("order");
//        for(ItemModel itemModel:order1.getItemModels()){
//            numberCart+=1;
//            total += itemModel.getProduct().getPrice() * itemModel.getQuantity();
//        }
//        orderItem.setTotal(total + 30000);
//        session.setAttribute("order", orderItem);
//        return numberCart;
//    }
//
//    @PostMapping("/update-number")
//    public @ResponseBody ChangeNumber updateNumber(HttpServletRequest request){
//        DecimalFormat formatter = new DecimalFormat("###,###,###");
//        int productId = Integer.parseInt(request.getParameter("productId"));
//        int quantity = Integer.parseInt(request.getParameter("quantity"));
//        Optional<Product> product = productService.findById(productId);
//        Product products = product.get();
//        int total = 0;
//        HttpSession session = request.getSession();
//        OrderItem orderItem = (OrderItem) session.getAttribute("order");
//        List<ItemModel> listItem = orderItem.getItemModels();
//        for (int i = 0 ; i<listItem.size() ; i++){
//            if(listItem.get(i).getProduct().getId() == productId){
//                listItem.get(i).setQuantity(quantity);
//            }
//        }
//        for (int i = 0 ; i< listItem.size() ;i++){
//            total += listItem.get(i).getProduct().getPrice() * listItem.get(i).getQuantity();
//        }
//        orderItem.setItemModels(listItem);
//        orderItem.setTotal(total + 30000);
//        String giacu = formatter.format(products.getPrice() * quantity)+" ₫";
//        String giamoi = formatter.format(products.getPrice() * quantity)+" ₫";
//        String tongtien = formatter.format(total)+" ₫";
//        String tamTinh = formatter.format(total + 30000)+" ₫";
//        ChangeNumber changeNumber = new ChangeNumber(giacu,giamoi,tongtien,tamTinh);
//        return changeNumber;
//    }
//
//    @PostMapping("/order")
//    public ModelAndView order(HttpServletRequest request, RedirectAttributes rd){
//        ModelAndView mv = new ModelAndView("redirect:/cart/index");
//        String address = request.getParameter("address");
//        String phoneNumber = request.getParameter("phoneNumber");
//        String note = request.getParameter("note");
//        HttpSession session = request.getSession();
//        User checkAuth = middleware.middlewareUser(request);
//        if (checkAuth == null){
//            rd.addFlashAttribute("user_login", messageSource.getMessage("user_login", null, Locale.getDefault()));
//        } else {
//            OrderItem orderItem = (OrderItem) session.getAttribute("order");
//            Order order = new Order();
//            order.setAddress(address);
//            order.setDescription(note);
//            order.setAmount(orderItem.getTotal());
//            order.setPhoneNumber(phoneNumber);
//            order.setStatus(1);
//            //order.setUser(checkAuth);
//            order.setIsPayment(0);
//            order.setOrderDate(java.time.LocalDate.now().toString());
//            orderService.save(order);
//
//            for (int i = 0 ; i < orderItem.getItemModels().size() ; i++){
//                OrderDetail orderDetail = new OrderDetail();
//                orderDetail.setOrder(order);
//                orderDetail.setProduct(orderItem.getItemModels().get(i).getProduct());
//                orderDetail.setDiscount(orderItem.getItemModels().get(i).getQuantity());
//                orderDetail.setUnitPrice(orderItem.getItemModels().get(i).getProduct().getPrice());
//                orderDetailService.save(orderDetail);
//            }
//            rd.addFlashAttribute("order_success", messageSource.getMessage("order_success", null, Locale.getDefault()));
//            session.setAttribute("order", null);
//        }
//        return mv;
}
