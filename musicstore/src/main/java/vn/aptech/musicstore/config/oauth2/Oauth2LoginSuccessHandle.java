/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.config.oauth2;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.repository.AccountRepository;
import vn.aptech.musicstore.service.impl.AccountServiceImpl;

/**
 *
 * @author Administrator
 */
@Component
public class Oauth2LoginSuccessHandle extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private AccountRepository repoAccount;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        ClientOauth2User oauth2User = (ClientOauth2User) authentication.getPrincipal();
        String email = oauth2User.getEmail();
        String clientName = oauth2User.getClientName();
        String name = oauth2User.getName();

        Optional<Account> user = repoAccount.findByUsername(email);
        // trường hợp chưa có tài khoản => tạo mới
        if (user.isEmpty()) {
            Account u = new Account();
            // thiếu phần setId();
            u.setUsername(email);
            u.setFullname(name);
            u.setProvider(clientName);
            u.setEnabled(true);
            u.setRole("ROLE_USER");
            repoAccount.save(u);
        }
        request.getRequestDispatcher("/user");
        super.onAuthenticationSuccess(request, response, authentication); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
