/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.aptech.musicstore.entity.Account;
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
    public PromotionCode findByCode(@RequestParam("code") String code, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("codePromotion", code);
        Account user = (Account) session.getAttribute("user");
        Optional<PromotionCode> checkCode = serviceCodeService.findByCode(code);
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
//        return checkCode.get();
        if (Objects.equals(user.getId(), checkCode.get().getAcc().getId())) {
            if ((checkCode.get().getPromotion().getStartDate().getTime() - cal1.getTime().getTime()) < 0) {
                if ((checkCode.get().getPromotion().getEndDate().getTime() - cal.getTime().getTime()) >= 0) {
                    if (checkCode.get().getUseTimes() < checkCode.get().getPromotion().getUseTimes()) {
                        return checkCode.get();
                    }
                }
            }
        }
        return null;
    }
}
