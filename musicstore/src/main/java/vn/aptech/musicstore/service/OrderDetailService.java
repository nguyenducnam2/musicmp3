/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.musicstore.service;

import java.util.List;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import vn.aptech.musicstore.entity.OrderDetail;

@Service
public interface OrderDetailService {
    List<OrderDetail> findOrderDetailsByOrder(Order order);
    OrderDetail save(OrderDetail orderDetail);
}
