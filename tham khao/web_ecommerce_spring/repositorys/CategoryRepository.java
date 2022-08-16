package com.java.web_ecommerce_spring.repositorys;

import com.java.web_ecommerce_spring.domain.Category;
import com.java.web_ecommerce_spring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    void deleteById(int id);
    Category findCategoryByName(String name);
    Category findCategoryById(int id);
    @Query(value = "SELECT * from  categories  WHERE parent_id = 0 ",nativeQuery = true)
    List<Category> listCha();
    @Query(value = "SELECT * from  categories  WHERE parent_id != 0 ",nativeQuery = true)
    List<Category> listCon();
}
