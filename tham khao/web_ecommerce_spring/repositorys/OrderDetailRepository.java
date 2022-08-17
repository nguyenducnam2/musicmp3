package com.java.web_ecommerce_spring.repositorys;

import com.java.web_ecommerce_spring.domain.Order;
import com.java.web_ecommerce_spring.domain.OrderDetail;
import com.java.web_ecommerce_spring.domain.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
    List<OrderDetail> findOrderDetailsByOrder(Order order);
    List<OrderDetail> findAll(Sort sort);
}
