
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
        "/muziklogin",
        "logout"
    };

    @Autowired
    private AccountServiceImpl accountService;

    @Bean
    public PasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService).passwordEncoder(encodePassword());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

//           http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/muziklogin").permitAll().defaultSuccessUrl("/").loginProcessingUrl("/j_spring_security_check");
//        super.configure(http); //To change body of generated methods, choose Tools | Templates.
        http.csrf().disable();

        http.authorizeHttpRequests()
                .antMatchers(WHITE_LIST_URLS).permitAll()
                .antMatchers("/api/**").authenticated();
//                .antMatchers("/muziklogin", "logout").permitAll();
//        http.authorizeHttpRequests()
//                .antMatchers("/account/**").hasAnyRole("ADMIN","MODERATOR")
//                .antMatchers("/**").hasAnyRole("ADMIN", "USER","MODERATOR")
//                .anyRequest()
//                .authenticated();

        http.authorizeHttpRequests()
                .and().formLogin()
                .loginProcessingUrl("/j_spring_security_check_muziklogin")//submit url
                .loginPage("/muziklogin")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .failureUrl("/muziklogin?error=true")
                //cau hinh Logout Page
                .and().logout().logoutUrl("/logout")
                .logoutSuccessUrl("/muziklogin?logout=true")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
        http.authorizeHttpRequests().and().rememberMe();
        //config remember
//                .tokenRepository("abc")
//                .tokenValiditySeconds(24*60*60); //1 ngay
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/", "/song/**", "/result/**", "admintemplate/**", "/webdata/**");
    }
}
