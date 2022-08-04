/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.entity.Order;
import vn.aptech.musicstore.entity.OrderDetail;
import vn.aptech.musicstore.repository.OrderRepository;
import vn.aptech.musicstore.service.OrderService;


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
    public List<Order> findOrderByUser(Account user){
        return  orderRepository.findOrderByUser(user);
    }

}