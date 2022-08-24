/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.controller.admin;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.musicstore.entity.Promotion;
import vn.aptech.musicstore.service.PromotionService;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/admin/promotion")
public class PromotionController {

    @Autowired
    private PromotionService servicePromotion;

    @GetMapping
    public String index(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.getAttribute("user");
        session.setAttribute("user", session.getAttribute("user"));
        model.addAttribute("list", servicePromotion.findAll());
        model.addAttribute("name", "null");
//        LocalDate now = LocalDate.now();
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        model.addAttribute("nowEndDate", cal.getTime().getTime());
        model.addAttribute("nowStartDate", cal1.getTime().getTime());
        return "admin/promotion/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("promotion", new Promotion());
        model.addAttribute("action", "create");
        LocalDate now = LocalDate.now();
        model.addAttribute("now", now);
        return "admin/promotion/create";
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute("promotion") Promotion promotion) throws IOException {
        if (servicePromotion.findById(promotion.getId()).isEmpty()) {
            promotion.setCode(UUID.randomUUID().toString());
        } else {
//            promotion.setCode(UUID.randomUUID().toString());
            promotion.setCode(promotion.getCode());
        }
        servicePromotion.save(promotion);
        model.addAttribute("name", "null");
        model.addAttribute("mess", "Successfully");
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        model.addAttribute("nowEndDate", cal.getTime().getTime());
        model.addAttribute("nowStartDate", cal1.getTime().getTime());
        model.addAttribute("list", servicePromotion.findAll());
        return "/admin/promotion/index";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") int id) {
        model.addAttribute("promotion", servicePromotion.findById(id).orElseThrow());
        model.addAttribute("action", "update");
        return "admin/promotion/create";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        servicePromotion.delete(servicePromotion.findById(id).get());
        model.addAttribute("name", "null");
        model.addAttribute("mess", "Successfully");
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        model.addAttribute("nowEndDate", cal.getTime().getTime());
        model.addAttribute("nowStartDate", cal1.getTime().getTime());
        model.addAttribute("list", servicePromotion.findAll());
        return "/admin/promotion/index";
    }

}
