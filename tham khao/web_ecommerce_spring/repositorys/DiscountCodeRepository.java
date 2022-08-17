package com.java.web_ecommerce_spring.repositorys;

import com.java.web_ecommerce_spring.domain.DiscountCode;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Integer> {
    DiscountCode findDiscountCodeById(int id);
    List<DiscountCode> findAll(Sort sort);
    DiscountCode findDiscountCodeByCode(String code);
    void deleteById(int id);
}
