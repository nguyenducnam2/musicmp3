/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.config.oauth2;

import java.util.Collection;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 *
 * @author Administrator
 */
public class ClientOauth2User implements OAuth2User{

    private String clientName;
    private OAuth2User oAuth2User;

    public ClientOauth2User(OAuth2User oAuth2User,String clientName) {
        this.oAuth2User = oAuth2User;
        this.clientName = clientName;
    }
    
    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oAuth2User.getAttribute("name");
    }
    
    public String getFullName(){
         return oAuth2User.<String>getAttribute("name");
    }
    
     public String getEmail(){
         return oAuth2User.<String>getAttribute("email");
    }
    public String getClientName(){
        return this.clientName;
    }
    
    public String getImageUrl() {
        return oAuth2User.getAttribute("picture");
    }

    
}
