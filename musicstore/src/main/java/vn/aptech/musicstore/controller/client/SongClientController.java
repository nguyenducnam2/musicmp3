/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.controller.client;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.aptech.musicstore.entity.Playlist;
import vn.aptech.musicstore.entity.Playlistitem;
import vn.aptech.musicstore.entity.Song;
import vn.aptech.musicstore.service.AccountService;
import vn.aptech.musicstore.service.CartItemService;
import vn.aptech.musicstore.service.CartService;
import vn.aptech.musicstore.service.CommentService;
import vn.aptech.musicstore.service.PlaylistService;
import vn.aptech.musicstore.service.PlaylistitemService;
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

    @Autowired
    private CommentService service_cmt;

    @Autowired
    private AccountService serviceAccount;

    @Autowired
    private PlaylistService service_pl;

    @Autowired
    private PlaylistitemService service_plitem;
    
    @Autowired
    private CartService service_cart;
    
    @Autowired
    private CartItemService service_ci;

    @GetMapping("/{id}")
    public String mediaPlayer(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (!(session.getAttribute("mess") == null)) {
            Song s = service.findById(id).orElseThrow();
            s.setView(s.getView() + 1);
            service.save(s);
            List<Song> anotherlist = service.findByAlbumId(s.getAlbumId());
            Song swap = anotherlist.get(0);
            int index_to_swap = anotherlist.indexOf(s);
            anotherlist.set(0, s);
            anotherlist.set(index_to_swap, swap);
            model.addAttribute("song", s);
            model.addAttribute("anotherlist", anotherlist);
            model.addAttribute("listcomments", service_cmt.findBySongId(s.getId()));
            model.addAttribute("listcmtall", service_cmt.findAll());
            session.setAttribute("user", session.getAttribute("user"));
            model.addAttribute("user", session.getAttribute("user"));
            model.addAttribute("service_pl", service_pl);
            model.addAttribute("mess", session.getAttribute("mess").toString());
            session.removeAttribute("mess");
            return "client/song/mediaplayer";
        }
        Song s = service.findById(id).orElseThrow();
        s.setView(s.getView() + 1);
        service.save(s);
        List<Song> anotherlist = service.findByAlbumId(s.getAlbumId());
        Song swap = anotherlist.get(0);
        int index_to_swap = anotherlist.indexOf(s);
        anotherlist.set(0, s);
        anotherlist.set(index_to_swap, swap);
        model.addAttribute("song", s);
        model.addAttribute("anotherlist", anotherlist);
        model.addAttribute("listcomments", service_cmt.findBySongId(s.getId()));
        model.addAttribute("listcmtall", service_cmt.findAll());
        session.setAttribute("user", session.getAttribute("user"));
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("service_pl", service_pl);
        return "client/song/mediaplayer";
    }

    @GetMapping("/video/{id}")
    public String videoPlayer(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        Song s = service.findById(id).orElseThrow();
        s.setView(s.getView() + 1);
        service.save(s);
        model.addAttribute("song", s);
        List<Song> anotherlist = new ArrayList<>();
        try {
            for (int i = 0; i < service.findByAlbumId(s.getAlbumId()).size(); i++) {
                if (!(service.findByAlbumId(s.getAlbumId()).get(i).getVideo().isEmpty())) {
                    anotherlist.add(service.findByAlbumId(s.getAlbumId()).get(i));
                }
            }
        } catch (Exception e) {

        }
        model.addAttribute("anotherlist", anotherlist);
        model.addAttribute("listsub", service_sub.findBySongId(id));
        model.addAttribute("listcomments", service_cmt.findBySongId(s.getId()));
        HttpSession session = request.getSession();
        session.setAttribute("user", session.getAttribute("user"));
        model.addAttribute("user", session.getAttribute("user"));
        return "client/song/video";
    }

    @GetMapping("/playlist/add")
    public String addPlaylist(Model model, @RequestParam(name = "playlistId", required = false, defaultValue = "0") int playlistId,
            @RequestParam("name") String name, @RequestParam("accountId") Long accountId,
            @RequestParam("songId") Integer songId, HttpServletRequest request) {
        if (!name.isEmpty()) {
            Playlist playlist = new Playlist();
            playlist.setName(name);
            playlist.setAccountId(accountId);
            playlist.setAccount(serviceAccount.findById(accountId).orElseThrow());
            service_pl.save(playlist);
            Playlistitem plitem = new Playlistitem();
            plitem.setSongId(songId);
            plitem.setSong(service.findById(songId).orElseThrow());
            plitem.setPlaylistId(playlist.getId());
            for (Playlist pl : service_pl.findAll()) {
                if (pl.getName().equals(playlist.getName()) && (pl.getAccount() == playlist.getAccount())) {
                    plitem.setPlaylist(pl);
                }
            }
            service_plitem.save(plitem);
            HttpSession session = request.getSession();
            session.setAttribute("mess", "Successfully");
            return "redirect:/song/" + songId;
        } else {
            Playlistitem plitem = new Playlistitem();
            plitem.setSongId(songId);
            plitem.setSong(service.findById(songId).orElseThrow());
            plitem.setPlaylistId(playlistId);
            plitem.setPlaylist(service_pl.findById(playlistId).orElseThrow());
            service_plitem.save(plitem);
            HttpSession session = request.getSession();
            session.setAttribute("mess", "Successfully");
            return "redirect:/song/" + songId;
        }
    }

    @GetMapping("/playlist")
    public String myPlaylist(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user", session.getAttribute("user"));
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("service_pl", service_pl);
        model.addAttribute("service_plitem", service_plitem);
        return "client/playlist/index";
    }

    @GetMapping("/playlist/{id}")
    public String myPlaylistPlay(Model model, HttpServletRequest request,
            @PathVariable("id") int playlistId) {
        Song s = service.findById(service_plitem.findByPlaylistId(playlistId).get(0).getSong().getId()).orElseThrow();
        s.setView(s.getView() + 1);
        service.save(s);
        List<Song> anotherlist = new ArrayList<>();
        for (int i = 0; i < service_plitem.findByPlaylistId(playlistId).size(); i++) {
            anotherlist.add(service_plitem.findByPlaylistId(playlistId).get(i).getSong());
        }
        Song swap = anotherlist.get(0);
        int index_to_swap = anotherlist.indexOf(s);
        anotherlist.set(0, s);
        anotherlist.set(index_to_swap, swap);
        model.addAttribute("song", s);
        model.addAttribute("playlistId", playlistId);
        model.addAttribute("anotherlist", anotherlist);
        model.addAttribute("listcomments", service_cmt.findBySongId(s.getId()));
        model.addAttribute("listcmtall", service_cmt.findAll());
        HttpSession session = request.getSession();
        session.setAttribute("user", session.getAttribute("user"));
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("service_pl", service_pl);
        model.addAttribute("service_plitem", service_plitem);
        model.addAttribute("playlistname", service_pl.findById(playlistId).orElseThrow().getName());
        return "client/song/mediaplayer";
    }

    @GetMapping("/playlist/delete/{id}")
    public String deletePlaylist(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        for (Playlistitem item : service_plitem.findAll()) {
            if (item.getPlaylistId() == id) {
                service_plitem.delete(item);
            }
        }
        service_pl.delete(service_pl.findById(id).get());
        return "redirect:/song/playlist";
    }

    @GetMapping("/cart")
    public String cart(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user", session.getAttribute("user"));
        model.addAttribute("user", session.getAttribute("user"));
        return "client/song/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model, HttpServletRequest request,@RequestParam("cartId")int cartId,@RequestParam("subTotal")int subTotal) {
        HttpSession session = request.getSession();
        session.setAttribute("user", session.getAttribute("user"));
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("cart", service_cart.findById(cartId).get());
        model.addAttribute("items", service_ci.findByCartId(cartId));
        model.addAttribute("subTotal", subTotal);
        return "client/song/checkout";
    }

}
