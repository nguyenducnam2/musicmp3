/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import vn.aptech.musicstore.entity.News;
import vn.aptech.musicstore.service.NewsService;

/**
 *
 * @author Dung
 */
@Controller
@RequestMapping("/admin/news")
public class NewsController {

    @Value("${static.base.url}")
    private String base_url;

    @Autowired
    private NewsService service;

    @GetMapping
    public String index(Model model, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        model.addAttribute("list", service.getPage(pageNumber, size));
        model.addAttribute("service", service);
        model.addAttribute("title", "null");
        return "admin/news/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("news", new News());
        model.addAttribute("action", "create");
        return "admin/news/create";
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute("news") News news, @RequestParam("file") MultipartFile file) throws IOException {
        if (!(file.isEmpty())) {
            news.setImage(file.getOriginalFilename());
            Files.copy(file.getInputStream(), Paths.get(base_url + "\\webdata\\news" + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            news.setCreated_at(java.time.LocalDate.now().toString());
            service.save(news);
        } else {
            news.setImage(service.findById(news.getId()).orElseThrow().getImage());
            news.setCreated_at(java.time.LocalDate.now().toString());
            service.save(news);
        }
        return "redirect:/admin/news";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") int id) {
        model.addAttribute("news", service.findById(id).orElseThrow());
        model.addAttribute("action", "update");
        return "admin/news/create";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        service.deleteById(id);
        return "redirect:/admin/news";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam("title") String title, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "1000") int size) {
        model.addAttribute("list", service.getPage(pageNumber, size));
        model.addAttribute("service", service);
        model.addAttribute("title", title);
        return "admin/news/index";
    }
}
