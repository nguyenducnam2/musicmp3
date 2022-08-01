/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.api;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.aptech.musicstore.entity.Comment;
import vn.aptech.musicstore.service.AccountService;
import vn.aptech.musicstore.service.CommentService;
import vn.aptech.musicstore.service.SongService;

/**
 *
 * @author namng
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comment")
public class CommentApiController {

    @Value("${static.base.url}")
    private String base_url;

    @Autowired
    private CommentService service;

    @Autowired
    private AccountService serviceAccount;
    
    @Autowired
    private SongService service_song;

    @GetMapping
    public List<Comment> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Comment> findById(@PathVariable int id) {
        return service.findById(id);
    }

    @GetMapping("/findBySongId")
    public List<Comment> findBySongId(@RequestParam("songId") int songId) {
        return service.findBySongId(songId);
    }

    @PostMapping("/add")
    public List<Comment> addComment(Model model, @RequestParam("content") String content,
            @RequestParam("accountId") String accountId,
            @RequestParam("songId") Integer songId, HttpServletRequest request) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setAccountId(Long.parseLong(accountId));
        comment.setAccount(serviceAccount.findById(Long.parseLong(accountId)).orElseThrow());
        comment.setSongId(songId);
        comment.setSong(service_song.findById(songId).orElseThrow());
        service.save(comment);
        return service.findAll();
    }
}
