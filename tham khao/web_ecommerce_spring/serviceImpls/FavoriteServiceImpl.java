package com.java.web_ecommerce_spring.serviceImpls;

import com.java.web_ecommerce_spring.domain.Comment;
import com.java.web_ecommerce_spring.domain.Favorite;
import com.java.web_ecommerce_spring.domain.Product;
import com.java.web_ecommerce_spring.domain.User;
import com.java.web_ecommerce_spring.repositorys.CommentRepository;
import com.java.web_ecommerce_spring.repositorys.FavoriteRepository;
import com.java.web_ecommerce_spring.services.CommentService;
import com.java.web_ecommerce_spring.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    FavoriteRepository favoriteRepository;

    @Override
    public List<Favorite> findFavoriteByUser(User user) {
        return favoriteRepository.findFavoriteByUser(user);
    }

    @Override
    public List<Favorite> findAll() {
        return favoriteRepository.findAll();
    }

    @Override
    public Favorite save(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    @Override
    public void deleteById(int id) {
        favoriteRepository.deleteById(id);
    }

    @Override
    public Favorite findFavoriteByUserAndProduct(User user, Product product) {
        return favoriteRepository.findFavoriteByUserAndProduct(user,product);
    }

}