package com.java.web_ecommerce_spring.services;

import com.java.web_ecommerce_spring.domain.Category;
import com.java.web_ecommerce_spring.domain.Comment;
import com.java.web_ecommerce_spring.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    List<Comment> findCommentByProduct(Product product);

    List<Comment> findAll();

    Comment save(Comment comment);

    void delete(int id);
}
