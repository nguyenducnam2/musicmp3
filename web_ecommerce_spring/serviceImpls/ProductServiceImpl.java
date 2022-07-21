package com.java.web_ecommerce_spring.serviceImpls;

import com.java.web_ecommerce_spring.domain.Category;
import com.java.web_ecommerce_spring.domain.Product;
import com.java.web_ecommerce_spring.repositorys.ProductRepository;
import com.java.web_ecommerce_spring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findProductByName(name);
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findProductById(id);
    }

    @Override
    public List<Product> getTopProduct(Sort sort) {
        return productRepository.findAll(sort);
    }

    @Override
    public List<Product> getProductByDiscount(int discount,Sort sort) {
        return productRepository.findProductByDiscount(discount,sort);
    }

    @Override
    public List<Product> getProductByCategory(Category category) {
        return productRepository.findProductByCategory(category);
    }

    @Override
    public Page<Product> findProductByCategory(Category category, Pageable pageable) {
        return productRepository.findProductByCategory(category,pageable);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findProductByNameLike(String name, Pageable pageable) {
        return productRepository.findProductByNameLike(name,pageable);
    }

    @Override
    public int updatepro(int quantity, int id) {
        return productRepository.updatepro(quantity,id);
    }

    @Override
    public List<Integer> listID() {
        return productRepository.listID();
    }

    @Override
    public  List<Product> listNotSell() {
        return productRepository.listNotSell();
    }

    @Override
    public List<Product> findProductByNameLike(String name) {
        return productRepository.findProductByNameLike(name);
    }
}
