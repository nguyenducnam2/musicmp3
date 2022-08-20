/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.aptech.musicstore.entity.Promotion;
import vn.aptech.musicstore.entity.PromotionCode;
import vn.aptech.musicstore.service.PromotionCodeService;
import vn.aptech.musicstore.service.PromotionService;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/promotion")
public class PromotionApiController {

    @Autowired
    private PromotionService service;

    @Autowired
    private PromotionCodeService serviceCodeService;

    @GetMapping
    public List<Promotion> findAll() {
        return service.findAll();
    }

    @GetMapping("/findByCode")
    public PromotionCode findByCode(@RequestParam("code") String code) {
        Optional<PromotionCode> checkCode = serviceCodeService.findByCode(code);
        return checkCode.get();
    }
}
