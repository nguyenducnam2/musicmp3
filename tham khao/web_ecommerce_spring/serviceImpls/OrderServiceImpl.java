package com.java.web_ecommerce_spring.serviceImpls;

import com.java.web_ecommerce_spring.domain.Order;
import com.java.web_ecommerce_spring.domain.OrderDetail;
import com.java.web_ecommerce_spring.domain.User;
import com.java.web_ecommerce_spring.repositorys.OrderRepository;
import com.java.web_ecommerce_spring.repositorys.UserRepository;
import com.java.web_ecommerce_spring.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public int update(int status , int id) {
        return orderRepository.update(status,id);
    }

    @Override
    public int updateIsPayment(int isPayment, int id) {
        return orderRepository.updateIsPayment(isPayment,id);
    }

    @Override
    public Order findOrderById(int id) {
        return orderRepository.findOrderById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderDetail> listOd(int order_id) {
        return orderRepository.listOd(order_id);
    }

    @Override
    public List<Order> getAmount(int month) {
        return orderRepository.getAmount(month);
    }
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findOrderByUser(User user){
        return  orderRepository.findOrderByUser(user);
    }

}
