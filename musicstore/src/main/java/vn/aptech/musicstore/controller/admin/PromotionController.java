/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.controller.admin;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
        return "admin/account/index";
    }
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("promotion", new Promotion());
        model.addAttribute("action", "create");
        return "admin/promotion/create";
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute("promotion") Promotion promotion, @RequestParam("file") MultipartFile file) throws IOException {
      
            servicePromotion.save(promotion);
        return "redirect:/admin/promotion";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") int id) {
        model.addAttribute("promotion", servicePromotion.findById(id).orElseThrow());
        model.addAttribute("action", "update");
        return "admin/promotion/create";
    }

//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable("id") int id) {
//        servicePromotion.deleteById(id);
//        return "redirect:/admin/prôpromotion";
//    }

}
