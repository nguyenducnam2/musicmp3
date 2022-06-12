/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.aptech.musicstore.entity.Song;
import vn.aptech.musicstore.service.SongService;

/**
 *
 * @author namng
 */
@Controller
public class SongClientController {
    
    @Autowired
    private SongService service;
    
    @GetMapping("/song/{id}")
    public String mediaPlayer(@PathVariable("id")int id,Model model){
        Song s=service.findById(id).orElseThrow();
        s.setView(s.getView()+1);
        service.save(s);
        model.addAttribute("song", s);
        return "client/song/mediaplayer";
    }
}
