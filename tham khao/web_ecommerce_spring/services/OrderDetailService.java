package com.java.web_ecommerce_spring.services;

import com.java.web_ecommerce_spring.domain.Order;
import com.java.web_ecommerce_spring.domain.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderDetailService {
    List<OrderDetail> findOrderDetailsByOrder(Order order);
    OrderDetail save(OrderDetail orderDetail);
}
