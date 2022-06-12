/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.controller.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.aptech.musicstore.entity.Song;
import vn.aptech.musicstore.service.AlbumService;
import vn.aptech.musicstore.service.ArtistService;
import vn.aptech.musicstore.service.SongService;

/**
 *
 * @author namng
 */
@Controller
public class HomeClientController {
    
    @Autowired
    private SongService service_song;
    
    @Autowired
    private AlbumService service_album;
    
    @Autowired
    private ArtistService service_artist;
    
    @GetMapping("")
    public String index(Model model){
        List<Song> listsong=new ArrayList<>();
        for(int i=service_song.findAll().size()-1;i>=0;i--){
            listsong.add(service_song.findAll().get(i));
        }
        model.addAttribute("listsong", listsong);    
        model.addAttribute("listsong_hot", service_song.findByOrderByViewDesc());
        model.addAttribute("listalbum", service_album.findAll());
        model.addAttribute("listartist", service_artist.findAll());
        return "client/index";
    }
    
}
