/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.aptech.musicstore.entity.Product;

/**
 *
 * @author pc
 */
public interface ProductRepository extends JpaRepository<Product,Integer> {
     @Query("SELECT o FROM Product o WHERE o.name LIKE CONCAT('%',:name,'%')")
            List<Product> findByName(String name);

}
