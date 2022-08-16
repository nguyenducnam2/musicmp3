package com.java.web_ecommerce_spring.serviceImpls;

import com.java.web_ecommerce_spring.domain.Order;
import com.java.web_ecommerce_spring.domain.OrderDetail;
import com.java.web_ecommerce_spring.repositorys.OrderDetailRepository;
import com.java.web_ecommerce_spring.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> findOrderDetailsByOrder(Order order){
        return  orderDetailRepository.findOrderDetailsByOrder(order);
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }
}
