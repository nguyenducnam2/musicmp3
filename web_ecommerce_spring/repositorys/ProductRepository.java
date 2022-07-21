package com.java.web_ecommerce_spring.repositorys;

import com.java.web_ecommerce_spring.domain.Category;
import com.java.web_ecommerce_spring.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Integer> {
    List<Product> findAll();
    void deleteById(int id);
    Product findProductByName(String name);
    Product findProductById(int id);
    List<Product> findAll(Sort sort);
    List<Product> findProductByDiscount(int discount, Sort sort);
    List<Product> findProductByCategory(Category category);
    Page<Product> findProductByCategory(Category category, Pageable pageable);
    Page<Product> findAll(Pageable pageable);
    Page<Product> findProductByNameLike(String name,Pageable pageable);
    List<Product> findProductByNameLike(String name);
    @Modifying
    @Transactional
    @Query(value = "Update product SET quantity = quantity - ? WHERE id = ?",nativeQuery = true)
    int updatepro(int quantity, int id);

    @Query(value = "SELECT product_id FROM order_detail WHERE order_id IN (SELECT id FROM orders WHERE status = 3) GROUP BY product_id ORDER BY SUM(discount) DESC LIMIT 10",nativeQuery = true)
    List<Integer> listID();

    @Query(value = "SELECT * FROM product WHERE id NOT IN (SELECT product_id FROM order_detail) LIMIT 10",nativeQuery = true)
    List<Product> listNotSell();

}
