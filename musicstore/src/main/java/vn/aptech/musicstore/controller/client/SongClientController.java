/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.controller.client;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.musicstore.entity.Song;
import vn.aptech.musicstore.service.SongService;
import vn.aptech.musicstore.service.SubtitleService;

/**
 *
 * @author namng
 */
@Controller
@RequestMapping("/song")
public class SongClientController {
    
    @Autowired
    private SongService service;
    
    @Autowired
    private SubtitleService service_sub;
    
    @GetMapping("/{id}")
    public String mediaPlayer(@PathVariable("id")int id,Model model){
        Song s=service.findById(id).orElseThrow();
        s.setView(s.getView()+1); 
        service.save(s);
        List<Song> anotherlist=service.findByAlbumId(s.getAlbumId());
        Song swap=anotherlist.get(0);
        int index_to_swap=anotherlist.indexOf(s);
        anotherlist.set(0, s);
        anotherlist.set(index_to_swap,swap);
        model.addAttribute("song", s);
        model.addAttribute("anotherlist", anotherlist);
        return "client/song/mediaplayer";
    }
    
    @GetMapping("/video/{id}")
    public String videoPlayer(@PathVariable("id")int id,Model model){
        Song s=service.findById(id).orElseThrow();
        s.setView(s.getView()+1);
        service.save(s);
        model.addAttribute("song", s);
        List<Song> anotherlist=new ArrayList<>();
        try{
            for(int i=0;i<service.findByAlbumId(s.getAlbumId()).size();i++){
            if(!(service.findByAlbumId(s.getAlbumId()).get(i).getVideo().isEmpty())){
                anotherlist.add(service.findByAlbumId(s.getAlbumId()).get(i));
            }
        }
        }catch(Exception e){
            
        } 
        model.addAttribute("anotherlist", anotherlist);
        model.addAttribute("listsub", service_sub.findBySongId(id));
        return "client/song/video";
    }
}
