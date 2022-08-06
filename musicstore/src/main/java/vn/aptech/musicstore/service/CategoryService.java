/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service;

import java.util.List;
import java.util.Optional;
import vn.aptech.musicstore.entity.Category;

/**
 *
 * @author pc
 */
public interface CategoryService {
    List<Category> findAll();
    Optional<Category> findById(int id);
    Category getCategoryById(int id);
    Category save(Category c);
        void deleteById(int id);
            List<Category> findByName(String name);

}
