package com.java.web_ecommerce_spring.services;

import com.java.web_ecommerce_spring.domain.DiscountCode;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DiscountCodeService {
    DiscountCode save(DiscountCode discountCode);
    DiscountCode findDiscountCodeById(int id);
    List<DiscountCode> findAll(Sort sort);
    void delete(int id);
    DiscountCode findDiscountCodeByCode(String code);
}
