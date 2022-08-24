/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.musicstore.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.entity.Order;
import vn.aptech.musicstore.entity.OrderDetail;


@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findAll();
    Order findOrderById(int id);
    @Modifying
    @Transactional
    @Query(value = "Update orders SET  status = ? WHERE id = ?",nativeQuery = true)
    int update(int status, int id);

    @Modifying
    @Transactional
    @Query(value = "Update orders SET  is_payment = ? WHERE id = ?",nativeQuery = true)
    int updateIsPayment(int isPayment, int id);

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM order_detail WHERE order_id = ?",nativeQuery = true)
    List<OrderDetail> listOd(int order_id);

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM orders WHERE is_payment = 1 AND MONTH(order_date) = ?",nativeQuery = true)
    List<Order> getAmount(int month);

    List<Order> findOrderByUser(Account user);

}