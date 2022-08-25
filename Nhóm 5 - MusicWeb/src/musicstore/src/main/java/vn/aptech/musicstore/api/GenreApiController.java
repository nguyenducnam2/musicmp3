/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.aptech.musicstore.entity.Genre;
import vn.aptech.musicstore.entity.Song;
import vn.aptech.musicstore.service.GenreService;
import vn.aptech.musicstore.service.SongService;

/**
 *
 * @author namng
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/genre")
public class GenreApiController {

    @Autowired
    private GenreService service;

    @Autowired
    private SongService service_song;

    @GetMapping
    public List<Genre> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Genre> findById(@PathVariable("id") int id) {
        return service.findById(id);
    }
    
    @PostMapping("/getSongxById")
    public List<Song> getSongxById(@RequestParam("id")int id){
        return service_song.findByGenreId(id);
    }

    @PostMapping
    public String create(@RequestBody Genre genre) {
        service.save(genre);
        return "Created";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        service.deleteById(id);
        return "Deleted";
    }

    // http://localhost:8080/api/genre 
    // HTTP METHOD PUT
    // GET id,name VARIABLE SENT FROM FORM
    // POSTMAN TO TEST,SUCCESSFULLY WILL SHOW STRING "Updated"
    @PutMapping
    public String update(@RequestParam("id") int id, @RequestParam("name") String name) {
        Genre genre = new Genre();
        genre.setId(id);
        genre.setName(name);
        service.save(genre);
        return "Updated";
    }

    @GetMapping("/getSongById")
    public List<Song> getSongById(@RequestParam("id") int id) {
        List<Song> listsong = new ArrayList<>();
        List<Song> listall = service_song.findByGenreId(id);
        if (listall.size() > 0) {
            for (int i = 0; i < 10; i++) {
                if (listall.size() > 0) {
                    Song s = listall.get(getRandomIndex(0, listall.size() - 1));
                    listsong.add(s);
                    listall.remove(s);
                } else {
                    break;
                }
            }
        }
        return listsong;
    }

    private int getRandomIndex(int min, int max) {
        return min + new Random().nextInt(max - min + 1);
    }
}
