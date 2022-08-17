package com.java.web_ecommerce_spring.repositorys;

import com.java.web_ecommerce_spring.domain.Comment;
import com.java.web_ecommerce_spring.domain.Favorite;
import com.java.web_ecommerce_spring.domain.Product;
import com.java.web_ecommerce_spring.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends PagingAndSortingRepository<Favorite, Integer> {
    List<Favorite> findFavoriteByUser(User user);

    List<Favorite> findAll();

    Favorite save(Favorite favorite);

    void deleteById(int id);

    Favorite findFavoriteByUserAndProduct(User user, Product product);

}
