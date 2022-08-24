/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.aptech.musicstore.entity.Category;

/**
 *
 * @author pc
 */
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query("SELECT o FROM Category o WHERE o.name LIKE CONCAT('%',:name,'%')")
    List<Category> findByName(@Param("name") String name);
    Category findCategoryById(int id);
    
   
}
