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
import org.springframework.core.io.ClassPathResource;
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
import vn.aptech.musicstore.service.AlbumService;
import vn.aptech.musicstore.service.ArtistService;
import vn.aptech.musicstore.service.GenreService;
import vn.aptech.musicstore.service.SongService;

/**
 *
 * @author namng
 */
@Controller
@RequestMapping("/admin/song")
public class SongController {
    
    @Value("${static.base.url}")
    private String base_url;

    @Autowired
    private SongService service;

    @Autowired
    private GenreService service_gen;

    @Autowired
    private ArtistService service_art;

    @Autowired
    private AlbumService service_alb;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("list", service.findAll());
        return "admin/song/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("song", new Song());
        model.addAttribute("listgenre", service_gen.findAll());
        model.addAttribute("listartist", service_art.findAll());
        model.addAttribute("listalbum", service_alb.findAll());
        return "admin/song/create";
    }

    @PostMapping("/save")
    public String save(@RequestParam("file") MultipartFile file,
            @ModelAttribute("song") Song s) throws IOException {
        if(!(file.isEmpty())){
        s.setMedia(file.getOriginalFilename());
        s.setGenre(service_gen.findById(s.getGenreId()).orElseThrow());
        s.setAlbum(service_alb.findById(s.getAlbumId()).orElseThrow());
        s.setView(0);
        s.setArtistId(s.getAlbum().getArtistId());
        s.setArtist(s.getAlbum().getArtist());
    //   String path_directory = "C:\\Users\\namng\\Documents\\NetBeansProjects\\musicstore\\src\\main\\resources\\static\\audio";
//        String path_directory = new ClassPathResource("static/image").getFile().getAbsolutePath();
      Files.copy(file.getInputStream(), Paths.get(base_url+"\\audio" + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        service.save(s);
        }else{
        s.setMedia(service.findById(s.getId()).orElseThrow().getMedia());
        s.setGenre(service_gen.findById(s.getGenreId()).orElseThrow());
        s.setAlbum(service_alb.findById(s.getAlbumId()).orElseThrow());
        s.setView(0);
        s.setArtistId(s.getAlbum().getArtistId());
        s.setArtist(s.getAlbum().getArtist());
        service.save(s);
        }
        return "redirect:/admin/song";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")int id){
        service.deleteById(id);
        return "redirect:/admin/song";
    }
    
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id")int id,Model model){
        model.addAttribute("song", service.findById(id).orElseThrow());
        model.addAttribute("listgenre", service_gen.findAll());
        model.addAttribute("listartist", service_art.findAll());
        model.addAttribute("listalbum", service_alb.findAll());
        return "admin/song/create";
    }
    
    @GetMapping("/search")
    public String search(Model model,@RequestParam("name")String name){
        model.addAttribute("list", service.findByName(name));
        return "admin/song/index";
    }
}
