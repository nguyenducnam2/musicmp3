/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.aptech.musicstore.entity.Playlist;
import vn.aptech.musicstore.entity.Playlistitem;
import vn.aptech.musicstore.service.AccountService;
import vn.aptech.musicstore.service.AlbumService;
import vn.aptech.musicstore.service.ArtistService;
import vn.aptech.musicstore.service.GenreService;
import vn.aptech.musicstore.service.PlaylistService;
import vn.aptech.musicstore.service.PlaylistitemService;
import vn.aptech.musicstore.service.SongService;

/**
 *
 * @author namng
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/playlist")
public class PlaylistApiController {

    @Value("${static.base.url}")
    private String base_url;

    @Autowired
    private SongService service_song;

    @Autowired
    private ArtistService service_art;

    @Autowired
    private GenreService service_gen;

    @Autowired
    private AlbumService service_alb;

    @Autowired
    private PlaylistitemService service_plitem;

    @Autowired
    private PlaylistService service;

    @Autowired
    private AccountService serviceAccount;

    @GetMapping
    public List<Playlist> findAll() {
        return service.findAll();
    }

    // add song to playlist or create new playlist
    @PostMapping("/addItem")
    public List<Playlist> addItem(@RequestParam(name = "playlistId", required = false, defaultValue = "0") int playlistId,
            @RequestParam("name") String name, @RequestParam("accountId") Long accountId,
            @RequestParam("songId") Integer songId) {
        if (!name.isEmpty()) {
            Playlist playlist = new Playlist();
            playlist.setName(name);
            playlist.setAccountId(accountId);
            playlist.setAccount(serviceAccount.findById(accountId).orElseThrow());
            service.save(playlist);
            Playlistitem plitem = new Playlistitem();
            plitem.setSongId(songId);
            plitem.setSong(service_song.findById(songId).orElseThrow());
            for (Playlist pl : service.findAll()) {
                if (pl.getName().equals(playlist.getName()) && (pl.getAccount() == playlist.getAccount())) {
                    plitem.setPlaylistId(pl.getId());
                    plitem.setPlaylist(pl);
                }
            }
            service_plitem.save(plitem);
        } else {
            Playlistitem plitem = new Playlistitem();
            plitem.setSongId(songId);
            plitem.setSong(service_song.findById(songId).orElseThrow());
            plitem.setPlaylistId(playlistId);
            plitem.setPlaylist(service.findById(playlistId).orElseThrow());
            service_plitem.save(plitem);
        }
        return service.findAll();
    }

    // get all playlist of account
    @GetMapping("/findByAccountId")
    public List<Playlist> findByAccountId(@RequestParam("accountId") Long accountId) {
        return service.findByAccountId(accountId);
    }

    // get all item of playlist
    @GetMapping("/findItemsOfPlaylistByPlaylistId")
    public List<Playlistitem> findItemsOfPlaylistByPlaylistId(@RequestParam("playlistId") int playlistId) {
        return service_plitem.findByPlaylistId(playlistId);
    }

    // remove item of playlist by id
    @GetMapping("/removePlaylistitemById")
    public List<Playlist> removePlaylistitemById(@RequestParam("playlistitemId") int id, @RequestParam("songId") int songId) {
        for (Playlistitem item : service_plitem.findAll()) {
            if (item.getId() == id && item.getSongId() == songId) {
                service_plitem.delete(item);
            }
        }
        return service.findAll();
    }

}
