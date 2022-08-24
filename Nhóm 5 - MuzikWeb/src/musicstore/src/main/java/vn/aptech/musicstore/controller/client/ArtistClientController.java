/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.controller.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/artist")
public class ArtistClientController {

    @Autowired
    private ArtistService service;

    @Autowired
    private SongService service_song;

    @Autowired
    private AlbumService service_alb;

    @GetMapping("/{id}")
    public String index(Model model, HttpServletRequest request, @PathVariable("id") int id) {
        HttpSession session = request.getSession();
        session.setAttribute("user", session.getAttribute("user"));
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("artist", service.findById(id).get());
        List<Song> listall = service_song.findByArtistId(id);
        List<Album> listall2 = service_alb.findByArtistid(id);
        List<Song> listsong = new ArrayList<>();
        List<Album> listalbum = new ArrayList<>();
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
        if (listall2.size() > 0) {
            for (int i = 0; i < 10; i++) {
                if (listall2.size() > 0) {
                    Album s = listall2.get(getRandomIndex(0, listall2.size() - 1));
                    listalbum.add(s);
                    listall2.remove(s);
                } else {
                    break;
                }
            }
        }
        model.addAttribute("listsong", listsong);
        model.addAttribute("listalbum", listalbum);
        return "client/artist/index";
    }

    public int getRandomIndex(int min, int max) {
        Random rd = new Random();
        return min + rd.nextInt(max - min + 1);
    }
}
