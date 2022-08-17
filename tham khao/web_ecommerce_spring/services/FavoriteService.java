package com.java.web_ecommerce_spring.services;

import com.java.web_ecommerce_spring.domain.Comment;
import com.java.web_ecommerce_spring.domain.Favorite;
import com.java.web_ecommerce_spring.domain.Product;
import com.java.web_ecommerce_spring.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FavoriteService {
    List<Favorite> findFavoriteByUser(User user);

    List<Favorite> findAll();

    Favorite save(Favorite favorite);

    void deleteById(int id);

    Favorite findFavoriteByUserAndProduct(User user, Product product);
}
