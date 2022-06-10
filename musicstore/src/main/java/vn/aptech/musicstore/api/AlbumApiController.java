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
import vn.aptech.musicstore.entity.Album;
import vn.aptech.musicstore.entity.Song;
import vn.aptech.musicstore.service.AlbumService;
import vn.aptech.musicstore.service.ArtistService;
import vn.aptech.musicstore.service.SongService;

/**
 *
 * @author namng
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/album")
public class AlbumApiController {
    
    @Value("${static.base.url}")
    private String base_url;


    @Autowired
    private AlbumService service;

    @Autowired
    private ArtistService service_art;
    
    @Autowired
    private SongService service_song;

    @GetMapping
    public List<Album> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Album> findById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping
    public String create(@RequestParam("name") String name,
            @RequestParam("release_date") String date,
            @RequestParam("artistId") int artistId,
            @RequestParam("file") MultipartFile file) throws IOException {
        Album alb = new Album();
        alb.setName(name);
        alb.setArtistId(artistId);
        alb.setArtist(service_art.findById(artistId).orElseThrow());
        alb.setReleaseDate(date);
        alb.setImage(file.getOriginalFilename());
//        String path_directory = "C:\\Users\\namng\\Documents\\NetBeansProjects\\musicstore\\src\\main\\resources\\static\\image";
//        String path_directory = new ClassPathResource("static/image").getFile().getAbsolutePath();
        Files.copy(file.getInputStream(), Paths.get(base_url+"\\album" + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        service.save(alb);
        return "sucess";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") int id) {
        service.deleteById(id);
        return "deleted";
    }

    @PutMapping
    public String update(@RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("release_date") String date,
            @RequestParam("artistId") int artistId,
            @RequestParam("file") MultipartFile file) throws IOException {
        if (!(file.isEmpty())) {
            Album alb = new Album();
            alb.setId(id);
            alb.setName(name);
            alb.setArtistId(artistId);
            alb.setArtist(service_art.findById(artistId).orElseThrow());
            alb.setReleaseDate(date);
            alb.setImage(file.getOriginalFilename());
//            String path_directory = "C:\\Users\\namng\\Documents\\NetBeansProjects\\musicstore\\src\\main\\resources\\static\\image";
            //        String path_directory = new ClassPathResource("static/image").getFile().getAbsolutePath();
            Files.copy(file.getInputStream(), Paths.get(base_url+"\\album" + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            for(Song s:service_song.findByAlbumId(id)){
                s.setArtistId(artistId);
                s.setArtist(service_art.findById(artistId).orElseThrow());
            }
            service.save(alb);
        } else {
            Album alb = new Album();
            alb.setId(id);
            alb.setName(name);
            alb.setArtistId(artistId);
            alb.setArtist(service_art.findById(artistId).orElseThrow());
            alb.setReleaseDate(date);
            alb.setImage(service.findById(id).orElseThrow().getImage());   
            for(Song s:service_song.findByAlbumId(id)){
                s.setArtistId(artistId);
                s.setArtist(service_art.findById(artistId).orElseThrow());
            }
            service.save(alb);
        }
        return "okla";
    }
}
