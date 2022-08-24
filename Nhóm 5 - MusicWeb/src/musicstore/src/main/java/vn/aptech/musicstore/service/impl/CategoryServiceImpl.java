/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.musicstore.entity.Category;
import vn.aptech.musicstore.repository.CategoryRepository;
import vn.aptech.musicstore.service.CategoryService;

/**
 *
 * @author pc
 */
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository repo;

    @Override
    public List<Category> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Category> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public Category save(Category c) {
        return repo.save(c);
    }

    @Override
    public void deleteById(int id) {
        repo.deleteById(id);
    }

    @Override
    public List<Category> findByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public Category getCategoryById(int id) {
        return repo.findCategoryById(id);
    }

    
   
}
