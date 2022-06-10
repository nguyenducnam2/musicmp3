/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.api;

import java.util.List;
import java.util.Optional;
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
import vn.aptech.musicstore.service.GenreService;

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

    @GetMapping
    public List<Genre> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Genre> findById(@PathVariable("id") int id) {
        return service.findById(id);
    }

    @PostMapping
    public String create(@RequestBody Genre genre){
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
    public String update(@RequestParam("id")int id,@RequestParam("name")String name) { 
        Genre genre=new Genre();
        genre.setId(id);
        genre.setName(name);
        service.save(genre);
        return "Updated";
    }
}
