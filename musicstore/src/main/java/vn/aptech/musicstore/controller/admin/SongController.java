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
import vn.aptech.musicstore.entity.Comment;
import vn.aptech.musicstore.entity.Song;
import vn.aptech.musicstore.service.AlbumService;
import vn.aptech.musicstore.service.ArtistService;
import vn.aptech.musicstore.service.CommentService;
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

    @Autowired
    private CommentService service_cmt;

    @GetMapping
    public String index(Model model, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "mess", required = false) String mess) {
        model.addAttribute("list", service.getPage(pageNumber, size));
        model.addAttribute("service", service);
        model.addAttribute("name", "null");
        model.addAttribute("mess", mess);
        return "admin/song/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("song", new Song());
        model.addAttribute("listgenre", service_gen.findAll());
        model.addAttribute("listartist", service_art.findAll());
        model.addAttribute("listalbum", service_alb.findAll());
        model.addAttribute("status", "Add Song");
        return "admin/song/create";
    }

    @PostMapping("/save")
    public String save(@RequestParam("file") MultipartFile file,
            @RequestParam("file2") MultipartFile file2,
            @ModelAttribute("song") Song s, Model model, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) throws IOException {
        try {

            if (!(file.isEmpty())) {
                s.setMedia(file.getOriginalFilename());
                if (!(file2.isEmpty())) {
                    s.setVideo(file2.getOriginalFilename());
                    Files.copy(file2.getInputStream(), Paths.get(base_url + "\\webdata\\video" + File.separator + file2.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
                } else {
                    try {
                        if (service.existsById(s.getId()) == true) {
                            s.setVideo(service.findById(s.getId()).orElseThrow().getVideo());
                        }
                    } catch (Exception e) {

                    }
                }
                s.setGenre(service_gen.findById(s.getGenreId()).orElseThrow());
                s.setAlbum(service_alb.findById(s.getAlbumId()).orElseThrow());
                s.setView(0);
                s.setArtistId(s.getAlbum().getArtistId());
                s.setArtist(s.getAlbum().getArtist());
                Files.copy(file.getInputStream(), Paths.get(base_url + "\\webdata\\audio" + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
                service.save(s);
            } else {
                if (file2.isEmpty()) {
                    s.setMedia(service.findById(s.getId()).orElseThrow().getMedia());
                    s.setVideo(service.findById(s.getId()).orElseThrow().getVideo());
                    s.setGenre(service_gen.findById(s.getGenreId()).orElseThrow());
                    s.setAlbum(service_alb.findById(s.getAlbumId()).orElseThrow());
                    s.setView(0);
                    s.setArtistId(s.getAlbum().getArtistId());
                    s.setArtist(s.getAlbum().getArtist());
                    service.save(s);
                } else {
                    s.setMedia(service.findById(s.getId()).orElseThrow().getMedia());
                    s.setVideo(file2.getOriginalFilename());
                    s.setGenre(service_gen.findById(s.getGenreId()).orElseThrow());
                    s.setAlbum(service_alb.findById(s.getAlbumId()).orElseThrow());
                    s.setView(0);
                    s.setArtistId(s.getAlbum().getArtistId());
                    s.setArtist(s.getAlbum().getArtist());
                    Files.copy(file2.getInputStream(), Paths.get(base_url + "\\webdata\\video" + File.separator + file2.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
                    service.save(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/admin/song?mess=Failed";
        }
        return "redirect:/admin/song?mess=Successfully";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size, Model model) throws IOException {
        try {
            for (Comment cmt : service_cmt.findAll()) {
                if (cmt.getSong().getId() == id) {
                    service_cmt.deleteBySongId(id);
                }
            }
            service.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/admin/song?mess=Failed";
        }
        return "redirect:/admin/song?mess=Successfully";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("song", service.findById(id).orElseThrow());
        model.addAttribute("listgenre", service_gen.findAll());
        model.addAttribute("listartist", service_art.findAll());
        model.addAttribute("listalbum", service_alb.findAll());
        model.addAttribute("status", "update");
        return "admin/song/create";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam("name") String name, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "1000") int size) {
        model.addAttribute("list", service.getPage(pageNumber, size));
        model.addAttribute("name", name);
        model.addAttribute("service", service);
        return "admin/song/index";
    }
}
