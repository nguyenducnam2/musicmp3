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
import org.springframework.core.io.ClassPathResource;
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
import vn.aptech.musicstore.entity.Song;
import vn.aptech.musicstore.service.AlbumService;
import vn.aptech.musicstore.service.ArtistService;
import vn.aptech.musicstore.service.GenreService;
import vn.aptech.musicstore.service.SongService;

/**
 *
 * @author namng
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/song")
public class SongApiController {

    @Value("${static.base.url}")
    private String base_url;

    @Autowired
    private SongService service;

    @Autowired
    private ArtistService service_art;

    @Autowired
    private GenreService service_gen;

    @Autowired
    private AlbumService service_alb;

    @GetMapping
    public List<Song> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Song> findById(@PathVariable("id") int id) {
        return service.findById(id);
    }
//    
//    @PostMapping
//    public void create(@RequestBody Song s){
//        service.save(s);
//    }
//

    @PostMapping
    public void create(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file,
            @RequestParam("file2") MultipartFile file2,
            @RequestParam("lyric") String lyric,
            @RequestParam("albumId") int albumId,
            @RequestParam("artistId") int artistId,
            @RequestParam("genreId") int genreId) throws IOException {
        Song s = new Song();
        s.setName(name);
        s.setMedia(file.getOriginalFilename());
        s.setLyric(lyric);
        if (!(file2.isEmpty())) {
            s.setVideo(file2.getOriginalFilename());
            Files.copy(file2.getInputStream(), Paths.get(base_url + "\\webdata\\video" + File.separator + file2.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        }
        s.setAlbumId(albumId);
        s.setArtistId(artistId);
        s.setGenreId(genreId);
        s.setArtist(service_art.findById(artistId).orElseThrow());
        s.setGenre(service_gen.findById(genreId).orElseThrow());
        s.setAlbum(service_alb.findById(albumId).orElseThrow());
        s.setView(0);
//        String path_directory = "C:\\Users\\namng\\Documents\\NetBeansProjects\\musicstore\\src\\main\\resources\\static\\image";
//        String path_directory = new ClassPathResource("static/image").getFile().getAbsolutePath();
        Files.copy(file.getInputStream(), Paths.get(base_url + "\\webdata\\audio" + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        service.save(s);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id) {
        service.deleteById(id);
    }

    @PutMapping
    public void update(@RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("file") MultipartFile file,
            @RequestParam("file2") MultipartFile file2,
            @RequestParam("lyric") String lyric,
            @RequestParam("albumId") int albumId,
            @RequestParam("artistId") int artistId,
            @RequestParam("genreId") int genreId) throws IOException {
        if (!(file.isEmpty())) {
            Song s = new Song();
            s.setId(id);
            s.setName(name);
            s.setMedia(file.getOriginalFilename());
            if (!(file2.isEmpty())) {
                s.setVideo(file2.getOriginalFilename());
                Files.copy(file2.getInputStream(), Paths.get(base_url + "\\webdata\\video" + File.separator + file2.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            } else {
                s.setVideo(service.findById(id).orElseThrow().getVideo());
            }
            s.setLyric(lyric);
            s.setView(service.findById(id).orElseThrow().getView());
            s.setAlbumId(albumId);
            s.setArtistId(artistId);
            s.setGenreId(genreId);
            s.setArtist(service_art.findById(artistId).orElseThrow());
            s.setGenre(service_gen.findById(genreId).orElseThrow());
            s.setAlbum(service_alb.findById(albumId).orElseThrow());
//            String path_directory = "C:\\Users\\namng\\Documents\\NetBeansProjects\\musicstore\\src\\main\\resources\\static\\image";
//        String path_directory = new ClassPathResource("static/image").getFile().getAbsolutePath();
            Files.copy(file.getInputStream(), Paths.get(base_url + "\\webdata\\audio" + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            service.save(s);
        } else {
            if (file2.isEmpty()) {
                Song s = new Song();
                s.setId(id);
                s.setName(name);
                s.setMedia(service.findById(id).orElseThrow().getMedia());
                s.setVideo(service.findById(id).orElseThrow().getVideo());
                s.setView(service.findById(id).orElseThrow().getView());
                s.setAlbumId(albumId);
                s.setArtistId(artistId);
                s.setGenreId(genreId);
                s.setArtist(service_art.findById(artistId).orElseThrow());
                s.setGenre(service_gen.findById(genreId).orElseThrow());
                s.setAlbum(service_alb.findById(albumId).orElseThrow());
                service.save(s);
            } else {
                Song s = new Song();
                s.setId(id);
                s.setName(name);
                s.setMedia(service.findById(id).orElseThrow().getMedia());
                s.setVideo(file2.getOriginalFilename());
                Files.copy(file2.getInputStream(), Paths.get(base_url + "\\webdata\\video" + File.separator + file2.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
                s.setView(service.findById(id).orElseThrow().getView());
                s.setAlbumId(albumId);
                s.setArtistId(artistId);
                s.setGenreId(genreId);
                s.setArtist(service_art.findById(artistId).orElseThrow());
                s.setGenre(service_gen.findById(genreId).orElseThrow());
                s.setAlbum(service_alb.findById(albumId).orElseThrow());
                service.save(s);
            }
        }
    }
}
