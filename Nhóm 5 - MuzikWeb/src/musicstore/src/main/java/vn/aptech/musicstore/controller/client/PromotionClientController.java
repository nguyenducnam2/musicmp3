/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.controller.client;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.entity.Promotion;
import vn.aptech.musicstore.entity.PromotionCode;
import vn.aptech.musicstore.service.AccountService;
import vn.aptech.musicstore.service.PromotionCodeService;
import vn.aptech.musicstore.service.PromotionService;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("promotion")
public class PromotionClientController {

    @Autowired
    private AccountService userService;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private PromotionCodeService promotionCodeService;

    @GetMapping()
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("list", promotionService.findAll());
        model.addAttribute("name", "null");
//        LocalDate now = LocalDate.now();
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        model.addAttribute("nowEndDate", cal.getTime().getTime());
        model.addAttribute("nowStartDate", cal1.getTime().getTime());
        return "client/promotion/index";
    }

    @GetMapping("/getCode/{promotionId}/{userId}")
    public String getCode(@PathVariable("promotionId") int promotionId, @PathVariable("userId") Long userId, Model model, HttpServletRequest request) {
        Optional<Promotion> p = promotionService.findById(promotionId);
        Optional<Account> u = userService.findById(userId);

        if (promotionCodeService.findByCodeAndUserId(p.get().getCode(), u.get().getId()).isEmpty()) {
            PromotionCode getCode = new PromotionCode();
            getCode.setCode(p.get().getCode());
            getCode.setUseTimes(0);
            getCode.setPromotionId(promotionId);
            getCode.setUserId(userId);
            getCode.setAcc(u.get());
            getCode.setPromotion(p.get());
            promotionCodeService.save(getCode);
            model.addAttribute("mess", "Successfully");
        } else {
            model.addAttribute("mess", "Failed");
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", u.get());
        session.setAttribute("codePromotion", p.get().getCode());
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        model.addAttribute("nowEndDate", cal.getTime().getTime());
        model.addAttribute("nowStartDate", cal1.getTime().getTime());
        model.addAttribute("list", promotionCodeService.findAll());
        model.addAttribute("name", "null");
        return "client/promotion/your-code-promotion";
    }

    @GetMapping("/yourCode")
    public String yourCode(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        Account user = (Account) session.getAttribute("user");
        List<PromotionCode> yourCode = promotionCodeService.findByUserId(user.getId());
        if (!yourCode.isEmpty()) {
            Calendar cal = Calendar.getInstance();
            Calendar cal1 = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            model.addAttribute("nowEndDate", cal.getTime().getTime());
            model.addAttribute("nowStartDate", cal1.getTime().getTime());
            model.addAttribute("list", yourCode);
            model.addAttribute("name", "null");
            return "client/promotion/your-code-promotion";
        }
        return "client/promotion/index";
    }
}
