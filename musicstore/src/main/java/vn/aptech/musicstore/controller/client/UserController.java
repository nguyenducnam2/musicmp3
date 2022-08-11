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
import vn.aptech.musicstore.entity.model.PasswordModel;
import vn.aptech.musicstore.entity.model.UserModel;
import vn.aptech.musicstore.service.AccountService;
import vn.aptech.musicstore.service.AlbumService;
import vn.aptech.musicstore.service.ArtistService;
import vn.aptech.musicstore.service.CartItemService;
import vn.aptech.musicstore.service.CartService;
import vn.aptech.musicstore.service.CommentService;
import vn.aptech.musicstore.service.GenreService;
import vn.aptech.musicstore.service.PlaylistService;
import vn.aptech.musicstore.service.PlaylistitemService;
import vn.aptech.musicstore.service.SongService;
import vn.aptech.musicstore.service.SubtitleService;

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
    private SongService service_song;

    @Autowired
    private AlbumService service_album;

    @Autowired
    private ArtistService service_artist;
    
     

    @Autowired
    private SubtitleService service_sub;

    @Autowired
    private GenreService service_gen;

    @Autowired
    private CommentService service_cmt;

    @Autowired
    private PlaylistService service_pl;

    @Autowired
    private PlaylistitemService service_plitem;

    @Autowired
    private CartService service_cart;

    @Autowired
    private CartItemService service_ci;

    

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
        return "client/index";
    }

//    @GetMapping("/profile")
//    public String profile() {
//        return "client/user/profile";
//    }
    @GetMapping("/profile/{id}")
    public String profile(@PathVariable("id") Long id, Model model) {
        Optional<Account> a = userService.findById(id);
        UserModel userM = new UserModel();
        if (a.isPresent()) {
            Account entity = a.get();
            BeanUtils.copyProperties(entity, userM);
            userM.setEnabled(true);
            userM.setPassword("");
            model.addAttribute("account", userM);
        }

        PasswordModel passwordModel = new PasswordModel();
        passwordModel.setEmail(a.get().getEmail());
        model.addAttribute("passwordModel", passwordModel);

        return "client/user/profile";
    }

    @PostMapping("/changePassword")
    public String changePassword(Model model ,@ModelAttribute("passwordModel") PasswordModel passwordModel,HttpServletRequest request) {
        Account user = userService.findAccountByEmail(passwordModel.getEmail());

        if (!userService.checkIfValidOldPassword(user, passwordModel.getOldPassword())) {
            return "redirect:/login";
            
        }
        //Save New Password
        userService.changePassword(user, passwordModel.getNewPassword());
        return "client/index";
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

        return "client/index";
    }
    @GetMapping("/upload")
    public String upload(Model model, HttpServletRequest request, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "8") int size) {
        HttpSession session = request.getSession();
        session.setAttribute("user", session.getAttribute("user"));
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("list", service_song.getPage(pageNumber, size));

        model.addAttribute("service", service_song);
        return "client/upload/index";
    }  
    
     @GetMapping("/checkout")
    public String checkout(Model model,HttpServletRequest request, @RequestParam("subTotal") int subTotal){
        String duration = request.getParameter("duration");
        String price = request.getParameter("price");
       
        model.addAttribute("subTotal", price);
        return "client/song/checkout";
    }
    
//    @GetMapping("/checkout")
//    public String checkout(Model model, HttpServletRequest request, @RequestParam("cartId") int cartId, @RequestParam("subTotal") int subTotal) {
//        HttpSession session = request.getSession();
//        session.setAttribute("user", session.getAttribute("user"));
//        model.addAttribute("user", session.getAttribute("user"));
//        model.addAttribute("cart", service_cart.findById(cartId).get());
//        model.addAttribute("items", service_ci.findByCartId(cartId));
//        model.addAttribute("subTotal", subTotal);
//        return "client/song/checkout";
//    }
    
    
    
    
    
    
    
    
     @GetMapping("/create")
    public String create(Model model, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        model.addAttribute("song", new Song());
        model.addAttribute("listgenre", service_gen.findAll());
        model.addAttribute("listartist", service_artist.findAll());
        model.addAttribute("listalbum", service_album.findAll());  
        session.setAttribute("user", session.getAttribute("user"));
        model.addAttribute("user", session.getAttribute("user"));
        
        model.addAttribute("status", "Add Song");
        return "client/upload/createsong";
    }

    @PostMapping("/save")
    public String save(@RequestParam("file") MultipartFile file,HttpServletRequest request,
            @ModelAttribute("song") Song s, Model model, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam("accountId")  Long accountId,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) throws IOException {
        
        HttpSession session = request.getSession();
          session.setAttribute("user", session.getAttribute("user"));
        model.addAttribute("user", session.getAttribute("user"));
            if (!(file.isEmpty())) {
                s.setMedia(file.getOriginalFilename());
                s.setAccountId(accountId);
                s.setAccount(userService.findById(accountId).orElseThrow());
                s.setGenre(service_gen.findById(s.getGenreId()).orElseThrow());
                s.setAlbum(service_album.findById(s.getAlbumId()).orElseThrow());
                s.setView(0);
                s.setArtistId(s.getAlbum().getArtistId());
                s.setArtist(s.getAlbum().getArtist());
                Files.copy(file.getInputStream(), Paths.get(base_url + "\\webdata\\audio" + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
                service_song.save(s);
            } else {
                if (!(file.isEmpty())) {
                    s.setMedia( service_song.findById(s.getId()).orElseThrow().getMedia());
                    s.setAccountId(userService.findById(s.getAccountId()).orElseThrow().getId());
                    s.setAccount(userService.findById(accountId).orElseThrow());
                    s.setGenre(service_gen.findById(s.getGenreId()).orElseThrow());
                    s.setAlbum(service_album.findById(s.getAlbumId()).orElseThrow());
                    s.setView(0);
                    s.setArtistId(s.getAlbum().getArtistId());
                    s.setArtist(s.getAlbum().getArtist());
                     service_song.save(s);
                     
      
                } 
            }
        model.addAttribute("list", service_song.getPage(pageNumber, size));
        model.addAttribute("service", service_song);
        model.addAttribute("name", "null");
        model.addAttribute("mess", "Successfully");
        return "client/upload/index";
    }
    
     @GetMapping("/upload/search")
    public String search(Model model, @RequestParam("name") String name, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "1000") int size) {
        model.addAttribute("list", service_song.getPage(pageNumber, size));
        model.addAttribute("name", name);
        model.addAttribute("service", service_song);
        return "client/upload/index";
    }
    
     @GetMapping("/upload/update/{id}")
    public String update(@PathVariable("id") int id, Model model, HttpServletRequest request ) {
         HttpSession session = request.getSession();
         session.setAttribute("user", session.getAttribute("user"));
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("song", service_song.findById(id).orElseThrow());
        
        model.addAttribute("listgenre", service_gen.findAll());
        model.addAttribute("listartist", service_artist.findAll());
        model.addAttribute("listalbum", service_album.findAll());
        model.addAttribute("status", "update");
        return "client/upload/createsong";
    }
    
    @GetMapping("/upload/delete/{id}")
    public String delete(@PathVariable("id")int id, Model model,HttpServletRequest request, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size){
         HttpSession session = request.getSession();
         session.setAttribute("user", session.getAttribute("user"));
        model.addAttribute("user", session.getAttribute("user"));
       
            service_song.deleteById(id);
       
        model.addAttribute("list", service_song.getPage(pageNumber, size));
        model.addAttribute("service", service_song);
        model.addAttribute("name", "null");
        model.addAttribute("mess", "Successfully");
        
        return "client/upload/index";
    }


}
