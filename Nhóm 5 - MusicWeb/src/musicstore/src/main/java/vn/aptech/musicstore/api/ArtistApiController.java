/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.aptech.musicstore.entity.Artist;
import vn.aptech.musicstore.entity.Song;
import vn.aptech.musicstore.service.ArtistService;
import vn.aptech.musicstore.service.SongService;

/**
 *
 * @author namng
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/artist")
public class ArtistApiController {

    @Value("${static.base.url}")
    private String base_url;

    @Autowired
    private ArtistService service;
    
    @Autowired
    private SongService service_song;

    @GetMapping
    public List<Artist> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Artist> findById(@PathVariable int id) {
        return service.findById(id);
    }
    
    @PostMapping("/getSongById")
    public List<Song> getSongById(@RequestParam("id")int id){
        return service_song.findByArtistId(id);
    }

    @PostMapping
    public String create(@RequestParam("name") String name,
            @RequestParam("des") String des,
            @RequestParam("file") MultipartFile file, @RequestParam("country") String country, @RequestParam("debut") String debut) throws IOException {
        Artist art = new Artist();
        art.setName(name);
        art.setDescription(des);
        art.setDebut(debut);
        art.setCountry(country);
        art.setImage(file.getOriginalFilename());
//        String path_directory = "C:\\Users\\namng\\Documents\\NetBeansProjects\\musicstore\\src\\main\\resources\\static\\image";
//        String path_directory = new ClassPathResource("static/image").getFile().getAbsolutePath();
        Files.copy(file.getInputStream(), Paths.get(base_url + "\\artist" + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        service.save(art);
        return "created";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.deleteById(id);
        return "deleted";
    }

    @PutMapping
    public String update(@RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("des") String des,
            @RequestParam("file") MultipartFile file, @RequestParam("country") String country, @RequestParam("debut") String debut) throws IOException {
        if (!(file.isEmpty())) {
            Artist art = new Artist(id, name, des, file.getOriginalFilename(),debut,country);
            service.save(art);
//            String path_directory = "C:\\Users\\namng\\Documents\\NetBeansProjects\\musicstore\\src\\main\\resources\\static\\image";
//          String path_directory = new ClassPathResource("static/image").getFile().getAbsolutePath();
            Files.copy(file.getInputStream(), Paths.get(base_url + "\\artist" + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        } else {
            Artist art2 = service.findById(id).orElseThrow();
            Artist art = new Artist(id, name, des, art2.getImage(),debut,country);
            service.save(art);
        }
        return "updated";
    }
}
