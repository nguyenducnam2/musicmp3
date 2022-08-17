package com.java.web_ecommerce_spring.controllers.admin;

import com.java.web_ecommerce_spring.constans.CommonConstants;
import com.java.web_ecommerce_spring.domain.Category;
import com.java.web_ecommerce_spring.domain.DiscountCode;
import com.java.web_ecommerce_spring.domain.Product;
import com.java.web_ecommerce_spring.services.DiscountCodeService;
import com.java.web_ecommerce_spring.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("admin")
public class AdminDiscountCodeController {
    @Autowired
    DiscountCodeService discountCodeService;

    @Autowired
    MessageSource messageSource;

    @GetMapping(value = "/discount-code")
    public ModelAndView index(Principal principal){
        ModelAndView mv = new ModelAndView("admin/discount-code");
        Sort sort = Sort.by("id").descending();
        mv.addObject("discountCodes" ,discountCodeService.findAll(sort));
        return mv;
    }

    @PostMapping("/discount-code/add")
    public ModelAndView addItem(@ModelAttribute("discountCode") DiscountCode discountCode,
                                HttpServletRequest request, Model model, RedirectAttributes rd) throws IOException {
        ModelAndView mv = new ModelAndView("redirect:/admin/discount-code");

        DiscountCode existItems = discountCodeService.findDiscountCodeByCode(discountCode.getCode());
        if (Objects.isNull(existItems))
        {
            DiscountCode itemsObj = discountCodeService.save(discountCode);
            if (Objects.nonNull(itemsObj))
            {
                rd.addFlashAttribute(CommonConstants.MSG, "1");
            } else {
                rd.addFlashAttribute(CommonConstants.MSG, "2");
            }
        } else {
            rd.addFlashAttribute(CommonConstants.MSG, "2");
        }
        return mv;
    }

    @GetMapping("/discount-code/delete/{id}")
    public ModelAndView delete(@PathVariable int id,RedirectAttributes rd)
    {
        ModelAndView mv = new ModelAndView("redirect:/admin/discount-code");
        discountCodeService.delete(id);
        rd.addFlashAttribute(CommonConstants.MSG, "1");
        return mv;
    }

    @PostMapping("/discount-code/update/{productId}")
    public ModelAndView updateStore(@ModelAttribute("discountCode") DiscountCode discountCode, @PathVariable int productId,
                                    HttpServletRequest request, Model model, RedirectAttributes rd) throws IOException {
        ModelAndView mv = new ModelAndView("redirect:/admin/discount-code");
        String nameItemCurrent = discountCodeService.findDiscountCodeById(productId).getCode();
        discountCode.setId(productId);
        if (nameItemCurrent.equalsIgnoreCase(discountCode.getCode())) {
            DiscountCode itemsObj = discountCodeService.save(discountCode);
            if (Objects.nonNull(itemsObj))
            {
                rd.addFlashAttribute(CommonConstants.MSG, "1");
                return mv;
            } else {
                rd.addFlashAttribute(CommonConstants.MSG, "2");
                return mv;
            }
        } else {
            DiscountCode checkExist = discountCodeService.findDiscountCodeByCode(discountCode.getCode());
            if (Objects.isNull(checkExist)) {
                DiscountCode itemsObj = discountCodeService.save(discountCode);
                if (Objects.nonNull(itemsObj))
                {
                    rd.addFlashAttribute(CommonConstants.MSG, "1");
                    return mv;
                } else {
                    rd.addFlashAttribute(CommonConstants.MSG, "2");
                    return mv;
                }
            } else {
                rd.addFlashAttribute(CommonConstants.MSG, "2");
                return mv;
            }

        }
    }
}
