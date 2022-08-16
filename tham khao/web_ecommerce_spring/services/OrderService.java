package com.java.web_ecommerce_spring.services;

import com.java.web_ecommerce_spring.domain.Order;
import com.java.web_ecommerce_spring.domain.OrderDetail;
import com.java.web_ecommerce_spring.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<Order> findAll();
    Order findOrderById(int id);
    int update(int status, int id);
    int updateIsPayment(int isPayment, int id);
    List<OrderDetail> listOd(int order_id);
    List<Order> getAmount(int month);
    Order save(Order order);
    List<Order> findOrderByUser(User user);
}
