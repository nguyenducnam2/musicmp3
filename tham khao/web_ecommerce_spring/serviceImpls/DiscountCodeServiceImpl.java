package com.java.web_ecommerce_spring.serviceImpls;

import com.java.web_ecommerce_spring.domain.DiscountCode;
import com.java.web_ecommerce_spring.repositorys.DiscountCodeRepository;
import com.java.web_ecommerce_spring.services.DiscountCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountCodeServiceImpl implements DiscountCodeService {

    @Autowired
    DiscountCodeRepository discountCodeRepository;

    @Override
    public DiscountCode save(DiscountCode discountCode) {
        return discountCodeRepository.save(discountCode);
    }

    @Override
    public DiscountCode findDiscountCodeById(int id) {
        return discountCodeRepository.findDiscountCodeById(id);
    }

    @Override
    public List<DiscountCode> findAll(Sort sort) {
        return discountCodeRepository.findAll(sort);
    }

    @Override
    public void delete(int id) {
        discountCodeRepository.deleteById(id);
    }

    @Override
    public DiscountCode findDiscountCodeByCode(String code) {
        return discountCodeRepository.findDiscountCodeByCode(code);
    }


}
