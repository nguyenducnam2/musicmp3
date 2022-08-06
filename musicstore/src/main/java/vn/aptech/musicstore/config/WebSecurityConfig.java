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
 * @author Administrator
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

   

    @Configuration
    @Order(1)
    public class WebUserCustomerSecurityConfig extends WebSecurityConfigurerAdapter {

        public WebUserCustomerSecurityConfig() {
            super();
        }

        private final String[] WHITE_LIST_URLS = {
            "/",
            "/contact",
            "/api/**",
            "/register*",
            "/resetPassword*",
            "/resetChangePassword*",
//            "/resetChangePassword",
            "/register",
            "/verify*",
            "/verifyRegistration*",
            "/resendVerifyToken*",
            "/savePassword*",
            "/changePassword",
            "/login",
            "/news",
            "logout"
        };

        @Autowired
        private AccountServiceImpl accountService;

        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(accountService).passwordEncoder(encodePassword());
        }

        @Override
        protected void configure(final HttpSecurity http) throws Exception {

            http.cors().and().csrf().disable();

            http.authorizeHttpRequests()
                    .antMatchers(WHITE_LIST_URLS).permitAll()
//                    .antMatchers("/api/**").authenticated()
                    .antMatchers("/user/**").hasAnyRole("ADMIN", "EDITOR", "MODERATOR", "USER")
                    .antMatchers("/admin/account/**").hasAnyRole("ADMIN", "MODERATOR")
                    .antMatchers("/admin/**").hasAnyRole("ADMIN", "EDITOR", "MODERATOR")
                    .anyRequest().authenticated();

            http.authorizeHttpRequests()
                    .and().formLogin()
                    .loginProcessingUrl("/user-login-process")//submit url
                    .loginPage("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/user", true)
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

        @Override
        public void configure(WebSecurity web) throws Exception {
            web
                    .ignoring()
                    .antMatchers("/", "/login", "/admin/login", "/song/**", "/result/**", "/admintemplate/**", "/webdata/**");
        }
    }

//     @Configuration
//    @Order(2)
//    public class WebAdminSecurityConfig extends WebSecurityConfigurerAdapter {
//
//        public WebAdminSecurityConfig() {
//            super();
//        }
//
//        @Autowired
//        private AccountServiceImpl accountService;
//
////    @Bean
////    public BCryptPasswordEncoder passwordEncoder(){
////        return new BCryptPasswordEncoder();
////    }
//        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//            auth.userDetailsService(accountService).passwordEncoder(encodePassword());
//        }
//
//        @Override
//        protected void configure(final HttpSecurity http) throws Exception {
//
////            http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/admin/login").permitAll().defaultSuccessUrl("/admin").loginProcessingUrl("/j_spring_security_check");
////        super.configure(http); //To change body of generated methods, choose Tools | Templates.
//            http.cors().and().csrf().disable();
//
//            http.authorizeHttpRequests()
//                    .antMatchers("/admin/login", "logout", "/", "/contact, /user-login-process").permitAll();
//            http.authorizeHttpRequests()
//                    .antMatchers("/admin/account/**").hasAnyRole("ADMIN", "MODERATOR")
//                    .antMatchers("/admin/**").hasAnyRole("ADMIN", "EDITOR", "MODERATOR", "USER")
//                    //                    .anyRequest().hasAnyRole("ADMIN", "EDITOR", "MODERATOR");
//                    .anyRequest().authenticated();
//
//            http.authorizeHttpRequests()
//                    .and().formLogin()
//                    .loginProcessingUrl("/admin-login-process")//submit url
//                    .loginPage("/admin/login")
//                    .usernameParameter("username")
//                    .passwordParameter("password")
//                    .defaultSuccessUrl("/", true)
//                    .failureUrl("/admin/login?error=true")
//                    //cau hinh Logout Page
//                    .and().logout().logoutUrl("/admin/logout")
//                    .logoutSuccessUrl("/admin/login?logout=true")
//                    .and()
//                    .exceptionHandling()
//                    .accessDeniedPage("/admin/403");
//            http.authorizeHttpRequests().and().rememberMe();
//            //config remember
////                .tokenRepository("abc")
////                .tokenValiditySeconds(24*60*60); //1 ngay
//        }
//
//        @Override
//        public void configure(WebSecurity web) throws Exception {
//            web
//                    .ignoring()
//                    .antMatchers("/", "/login", "/admin/login", "/song/**", "/result/**", "/admintemplate/**", "/webdata/**");
//        }
//    }
}
