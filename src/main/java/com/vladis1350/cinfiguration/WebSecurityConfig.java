package com.vladis1350.cinfiguration;

import com.vladis1350.auth.service.MyUserDetailsService;
import com.vladis1350.constants.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
//                .usersByUsernameQuery("SELECT user_name, password, active FROM users WHERE user_name=?")
//                .authoritiesByUsernameQuery(
//                        "SELECT users.user_name, roles.role_name " +
//                                "FROM users INNER JOIN user_role " +
//                                "ON users.id_user=user_role.user_id " +
//                                "WHERE users.user_name=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers(Pages.CATEGORY).permitAll()
                .antMatchers(Pages.EDIT_PRODUCT).permitAll()
                .antMatchers(Pages.SET_DISCOUNT).permitAll()
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/signUp").permitAll()
                .antMatchers("/saveProduct").permitAll()
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/signIn")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
    }



    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/assets/**", "/styles/**");
    }
}