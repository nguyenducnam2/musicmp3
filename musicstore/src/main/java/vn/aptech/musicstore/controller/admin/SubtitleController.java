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
import java.util.ArrayList;
import java.util.List;
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
import vn.aptech.musicstore.entity.Song;
import vn.aptech.musicstore.entity.Subtitle;
import vn.aptech.musicstore.service.SongService;
import vn.aptech.musicstore.service.SubtitleService;

/**
 *
 * @author namng
 */
@Controller
@RequestMapping("/admin/subtitle")
public class SubtitleController {

    @Value("${static.base.url}")
    private String base_url;

    @Autowired
    private SubtitleService service;

    @Autowired
    private SongService service_song;

    @GetMapping
    public String index(Model model,@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        List<Song> listsong = new ArrayList<>();
        for (int i = 0; i < service_song.findAll().size(); i++) {
            try {
                if (!(service_song.findAll().get(i).getVideo().isEmpty())) {
                    listsong.add(service_song.findAll().get(i));
                }
            } catch (Exception e) {

            }
        }
        model.addAttribute("posts", service_song.getPage(pageNumber, size));
        model.addAttribute("listsong", listsong);
        model.addAttribute("service_sub", service);
        return "admin/subtitle/index";
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable("id") int id) {
        model.addAttribute("song", service_song.findById(id).orElseThrow());
        model.addAttribute("listsub", service.findBySongId(id));
        return "admin/subtitle/details";
    }

    @GetMapping("/create/{id}")
    public String create(Model model, @PathVariable("id") int id) {
        Subtitle subtitle = new Subtitle();
        subtitle.setSongId(id);
        model.addAttribute("subtitle", subtitle);
        return "admin/subtitle/create";
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute("subtitle") Subtitle subtitle, @RequestParam("file") MultipartFile file) throws IOException {
        if (!(file.isEmpty())) {
            subtitle.setSong(service_song.findById(subtitle.getSongId()).orElseThrow());
            subtitle.setVtt(file.getOriginalFilename());
            Files.copy(file.getInputStream(), Paths.get(base_url + "\\webdata\\sub\\" + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            service.save(subtitle);
        } else {
            subtitle.setSong(service_song.findById(subtitle.getSongId()).orElseThrow());
            subtitle.setVtt(service.findById(subtitle.getId()).orElseThrow().getVtt());
            service.save(subtitle);
        }
        return details(model, subtitle.getSongId());
    }
    
    @GetMapping("/delete/{id}")
    public String delete(Model model,@PathVariable("id")int id) throws IOException{
        int songId=service.findById(id).orElseThrow().getSongId();
      //  Files.delete(Paths.get(base_url + "\\webdata\\sub" + File.separator + service.findById(id).orElseThrow().getVtt()));
        service.deleteById(id);
        return "redirect:/admin/subtitle/details/"+String.valueOf(songId);
    }
}
