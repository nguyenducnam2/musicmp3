/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.service;

import java.util.List;
import org.springframework.stereotype.Service;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.entity.Order;
import vn.aptech.musicstore.entity.OrderDetail;
import vn.aptech.musicstore.pagination.Paged;

@Service
public interface OrderService {
    List<Order> findAll();
    Order findOrderById(int id);
    int update(int status, int id);
    int updateIsPayment(int isPayment, int id);
    List<OrderDetail> listOd(int order_id);
    List<Order> getAmount(int month);
    Order save(Order order);
    List<Order> findOrderByUser(Account user);
    Paged<Order> getPage(int pageNumber, int size);
    void deleteById(int id);
}
