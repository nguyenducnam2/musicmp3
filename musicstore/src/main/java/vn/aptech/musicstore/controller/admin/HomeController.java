/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.controller.admin;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.entity.Song;
import vn.aptech.musicstore.entity.SongOrderDetail;
import vn.aptech.musicstore.entity.UpgradeVipOrderDetails;
import vn.aptech.musicstore.service.AccountService;
import vn.aptech.musicstore.service.SongOrderDetailService;
import vn.aptech.musicstore.service.SongService;
import vn.aptech.musicstore.service.UpgradeVipOrderDetailsService;

/**
 *
 * @author namng
 */
@Controller
@RequestMapping("/admin")
public class HomeController {

    @Autowired
    private AccountService serviceAccount;

    @Autowired
    private SongService songService;

    @Autowired
    private UpgradeVipOrderDetailsService upgradeVipOrderDetailsService;

    @Autowired
    private SongOrderDetailService songOrderDetailService;

    @GetMapping
    public String index(Principal principal, Model model, HttpServletRequest request) {
        String username = principal.getName();
        Optional<Account> user = serviceAccount.findByUsername(username);

        double totalProfitBuySong = 0;
        HttpSession session = request.getSession();
        session.setAttribute("user", user.get());

        List<Account> accList = serviceAccount.findAll();
        List<Song> songList = songService.findAll();
        List<SongOrderDetail> songOrderDetails = songOrderDetailService.findAll();
         for (SongOrderDetail songOrderDetail : songOrderDetails) {
            totalProfitBuySong+=songOrderDetail.getSongOrder().getTotal();
        }

//        double totalProfitUpgradeAcc = 0;
//        List<UpgradeVipOrderDetails> upgradeVipOrderDetailses = upgradeVipOrderDetailsService.findAll();
//        for (UpgradeVipOrderDetails upgradeVipOrderDetailse : upgradeVipOrderDetailses) {
//            totalProfitUpgradeAcc += upgradeVipOrderDetailse.getTotal();
//        }

        model.addAttribute("user", user.get());
        model.addAttribute("totalAccount", accList.size());
        model.addAttribute("totalSong", songList.size());
//        model.addAttribute("totalProfitUpgradeAcc", totalProfitUpgradeAcc);
        model.addAttribute("totalProfitBuySong", totalProfitBuySong);
        return "admin/index";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        return "admin/login";
    }

    @GetMapping(value = "/403")
    public String error403(Model model) {
        return "error/403";
    }
}
