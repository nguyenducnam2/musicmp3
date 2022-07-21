
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.aptech.musicstore.service.impl.AccountServiceImpl;

/**
 *
 * @author Thanh Sang
 */
@EnableWebSecurity
@Configuration
@Order(2)
public class WebUserCustomerSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] WHITE_LIST_URLS = {
        "/",
        "/register",
        "/verifyRegistration*",
        "/resendVerifyToken*",
        "/login",
        "logout"
    };

    @Autowired
    private AccountServiceImpl accountService;

    @Bean
    public PasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }


    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService).passwordEncoder(encodePassword());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();

        http.authorizeHttpRequests()
                .antMatchers(WHITE_LIST_URLS).permitAll()
                .antMatchers("/api/**").authenticated()
                .antMatchers("/user/**").hasAnyRole("USER")
                .anyRequest()
                .authenticated();
                


        http.authorizeHttpRequests()
                .and().formLogin()
                .loginProcessingUrl("/user-login-process")//submit url
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                //cau hinh Logout Page
                .and().logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
        http.authorizeHttpRequests().and().rememberMe();
        //config remember
//                .tokenRepository("abc")
//                .tokenValiditySeconds(24*60*60); //1 ngay
    }

  
}
