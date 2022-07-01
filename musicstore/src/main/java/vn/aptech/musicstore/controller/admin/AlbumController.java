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
    public String index(Model model, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        model.addAttribute("list", service.getPage(pageNumber, size));
        model.addAttribute("service", service);
        model.addAttribute("name", "null");
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
        Files.copy(file.getInputStream(), Paths.get(base_url + "\\webdata\\album" + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
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
            Files.copy(file.getInputStream(), Paths.get(base_url + "\\webdata\\album" + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            service.save(album);
        } else {
            album.setImage(service.findById(album.getId()).orElseThrow().getImage());
            album.setArtist(service_art.findById(album.getArtistId()).orElseThrow());
            service.save(album);
        }
        return "redirect:/admin/album";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) throws IOException {
        Files.delete(Paths.get(base_url + "\\webdata\\album" + File.separator + service.findById(id).orElseThrow().getImage()));
        service.deleteById(id);
        return "redirect:/admin/album";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam("name") String name, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "1000") int size) {
        model.addAttribute("list", service.getPage(pageNumber, size));
        model.addAttribute("service", service);
        model.addAttribute("name", name);
        return "admin/album/index";
    }

}
