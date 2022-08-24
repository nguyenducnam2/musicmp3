/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.aptech.musicstore.entity.Category;
import vn.aptech.musicstore.entity.Product;
import vn.aptech.musicstore.pagination.Paged;

/**
 *
 * @author pc
 */
public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(int id);
    Product save(Product p);
    void deleteById(int id);
    List<Product> findByName(String name);
    List<Product> getProductByCategory(Category category);
    


          
    List<Product> findProductByNameLike(String name);
    Paged<Product> getPage(int pageNumber, int size);
            
            
            


}
