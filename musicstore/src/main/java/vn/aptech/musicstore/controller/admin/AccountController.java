/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.controller.admin;

import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.entity.model.AccountDto;
import vn.aptech.musicstore.service.AccountService;

/**
 *
 * @author Thanh Sang
 */
@Controller
@RequestMapping("/admin/account")
public class AccountController {

    @Autowired
    private AccountService serviceAccount;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("list", serviceAccount.findAll());
        return "admin/account/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("account", new Account());
        return "admin/account/create";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("account", serviceAccount.findById(id));
        return "admin/account/update";
    }
    @GetMapping("/updateProfile/{username}")
    public String updateProfile(@PathVariable("username") String username, Model model) {
        Optional<Account> a = serviceAccount.findByUsername(username);
        AccountDto dto = new AccountDto();
        
        if(a.isPresent()){
            Account entity = a.get();
            BeanUtils.copyProperties(entity, dto);
            dto.setIsEdit(true);
            dto.setPassword("");
            model.addAttribute("account", dto);
        }
        model.addAttribute("message","Account is not exist");
        return "admin/profile";
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute Account acc) {
        Optional<Account> a = serviceAccount.findByUsername(acc.getUsername());
        if (a.isEmpty()) {
            serviceAccount.save(acc);
            return "redirect:/admin/account";
        } else {
//            model.addAttribute("", serviceAccount)
            return "redirect:/admin/account";

        }
    }

    @PostMapping("/processUpdate")
    public String processUpdate(Model model, @ModelAttribute Account acc) {
        serviceAccount.save(acc);
        return "redirect:/admin/account";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        serviceAccount.deleteById(id);
        return "redirect:/admin/account";
    }
}
