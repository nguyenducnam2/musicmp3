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
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import vn.aptech.musicstore.entity.Album;
import vn.aptech.musicstore.entity.Song;
import vn.aptech.musicstore.service.AlbumService;
import vn.aptech.musicstore.service.ArtistService;
import vn.aptech.musicstore.service.SongService;

/**
 *
 * @author namng
 */
@Controller
@RequestMapping("/admin/album")
public class AlbumController {
    
    @Value("${static.base.url}")
    private String base_url;

    @Autowired
    private AlbumService service;

    @Autowired
    private ArtistService service_art;

    @Autowired
    private SongService service_song;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("list", service.findAll());
        return "admin/album/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("album", new Album());
        model.addAttribute("listartist", service_art.findAll());
        return "admin/album/create";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("album", service.findById(id));
        model.addAttribute("listartist", service_art.findAll());
        return "admin/album/update";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("album") Album album, @RequestParam("file") MultipartFile file) throws IOException {
        album.setImage(file.getOriginalFilename());
        album.setArtist(service_art.findById(album.getArtistId()).orElseThrow());
//        String path_directory = "C:\\Users\\namng\\Documents\\NetBeansProjects\\musicstore\\src\\main\\resources\\static\\image";
//        String path_directory = new ClassPathResource("static/image").getFile().getAbsolutePath();
        Files.copy(file.getInputStream(), Paths.get(base_url+"\\album" + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        service.save(album);
        return "redirect:/admin/album";
    }

    @PostMapping("/processupdate")
    public String processUpdate(@ModelAttribute("album") Album album, @RequestParam("file") MultipartFile file) throws IOException {
        for (Song s : service_song.findByAlbumId(album.getId())) {
            s.setArtistId(album.getArtistId());
            s.setArtist(service_art.findById(s.getArtistId()).orElseThrow());
        }
        if (!(file.isEmpty())) {
            album.setImage(file.getOriginalFilename());
            album.setArtist(service_art.findById(album.getArtistId()).orElseThrow());
            String path_directory = "C:\\Users\\namng\\Documents\\NetBeansProjects\\musicstore\\src\\main\\resources\\static\\image";
//        String path_directory = new ClassPathResource("static/image").getFile().getAbsolutePath();
            Files.copy(file.getInputStream(), Paths.get(base_url+"\\album" + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            service.save(album);
        } else {
            album.setImage(service.findById(album.getId()).orElseThrow().getImage());
            album.setArtist(service_art.findById(album.getArtistId()).orElseThrow());
            service.save(album);
        }
        return "redirect:/admin/album";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        service.deleteById(id);
        return "redirect:/admin/album";
    }
    
    @GetMapping("/search")
    public String search(Model model,@RequestParam("name")String name){
        model.addAttribute("list", service.findByNameCustom(name));
        return "admin/album/index";
    }

}
