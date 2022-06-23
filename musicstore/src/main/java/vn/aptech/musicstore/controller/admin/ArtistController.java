/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import vn.aptech.musicstore.entity.Artist;
import vn.aptech.musicstore.service.ArtistService;

/**
 *
 * @author namng
 */
@Controller
@RequestMapping("/admin/artist")
public class ArtistController {
    
    @Value("${static.base.url}")
    private String base_url;

    @Autowired
    private ArtistService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("list", service.findAll());
        return "admin/artist/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("artist", new Artist());
        return "admin/artist/create";
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute("artist") Artist artist, @RequestParam("file") MultipartFile file) throws IOException {
        if (!(file.isEmpty())) {
            artist.setImage(file.getOriginalFilename());
//            String path_directory = "C:\\Users\\namng\\Documents\\NetBeansProjects\\musicstore\\src\\main\\resources\\static\\image";
//        String path_directory = new ClassPathResource("static/image").getFile().getAbsolutePath();
            Files.copy(file.getInputStream(), Paths.get(base_url+"\\webdata\\artist" + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            service.save(artist);
        } else {
            artist.setImage(service.findById(artist.getId()).orElseThrow().getImage());
            service.save(artist);
        }
        return "redirect:/admin/artist";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") int id) {
        model.addAttribute("artist", service.findById(id).orElseThrow());
        return "admin/artist/create";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")int id){
        service.deleteById(id);
        return "redirect:/admin/artist";
    }
    
    @GetMapping("/search")
    public String search(Model model,@RequestParam("name")String name){
        model.addAttribute("list", service.findByNameCustom(name));
        return "admin/artist/index";
    }
}
