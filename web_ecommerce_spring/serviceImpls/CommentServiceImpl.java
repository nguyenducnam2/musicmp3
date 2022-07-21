package com.java.web_ecommerce_spring.serviceImpls;

import com.java.web_ecommerce_spring.domain.Comment;
import com.java.web_ecommerce_spring.domain.Product;
import com.java.web_ecommerce_spring.repositorys.CommentRepository;
import com.java.web_ecommerce_spring.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Comment> findCommentByProduct(Product product) {
        return commentRepository.findCommentByProduct(product);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void delete(int id) {
        commentRepository.deleteById(id);
    }

}