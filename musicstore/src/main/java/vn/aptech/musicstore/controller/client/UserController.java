/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.controller.client;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.entity.Song;
import vn.aptech.musicstore.entity.model.AccountModel;
import vn.aptech.musicstore.entity.model.UserModel;
import vn.aptech.musicstore.repository.PasswordResetTokenRepository;
import vn.aptech.musicstore.service.AccountService;
import vn.aptech.musicstore.service.AlbumService;
import vn.aptech.musicstore.service.ArtistService;
import vn.aptech.musicstore.service.SongService;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    
    @Autowired
    private AccountService userService;
    
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    
    @Autowired
    private SongService service_song;
    
    @Autowired
    private AlbumService service_album;
    
    @Autowired
    private ArtistService service_artist;
    
    @Value("${static.base.url}")
    private String base_url;

//    @Autowired
//    private ApplicationEventPublisher publisher;
    @RequestMapping(method = RequestMethod.GET)
    public String index(Principal principal, Model model, HttpServletRequest request) {
        String username = principal.getName();
        Optional<Account> user = userService.findByUsername(username);
        HttpSession session = request.getSession();
        session.setAttribute("user", user.get());
        model.addAttribute("user", user.get());
        List<Song> listsong = new ArrayList<>();
        for (int i = service_song.findAll().size() - 1; i >= 0; i--) {
            listsong.add(service_song.findAll().get(i));
        }
        model.addAttribute("listsong", listsong);
        model.addAttribute("listsong_hot", service_song.findByOrderByViewDesc());
        model.addAttribute("listalbum", service_album.findTop12());
        model.addAttribute("listartist", service_artist.findTop12ByOrderByIdDesc());
        return "client/user/index";
    }

//    @GetMapping("/profile")
//    public String profile() {
//        return "client/user/profile";
//    }
    @GetMapping("/profile/{id}")
    public String profile(@PathVariable("id") Long id, Model model) {
         Optional<Account> a = userService.findById(id);
         UserModel dto = new UserModel();
        
        if(a.isPresent()){
            Account entity = a.get();
            BeanUtils.copyProperties(entity, dto);
            dto.setEnabled(true);
            dto.setPassword("");
            model.addAttribute("account", dto);
        }
        model.addAttribute("message","Account is not exist");
        return "client/user/profile";
    }
    
    @PostMapping("/processUpdate")
    public String processUpdate(@ModelAttribute("account") Account user, @RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        
        if (!(file.isEmpty())) {
            user.setImage(file.getOriginalFilename());
            Files.copy(file.getInputStream(), Paths.get(base_url + "\\webdata\\user" + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            userService.save(user);
        } else {
            user.setImage(userService.findById(user.getId()).orElseThrow().getImage());
            userService.save(user);
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        
        return "redirect:/user";
        
    }
}
