package com.springboot.security.config;

import com.springboot.security.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        // Disables CSRF
//        httpSecurity.csrf(customizer -> customizer.disable());
//        httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated());
//        // To enable formLogin from Client
//        httpSecurity.formLogin(Customizer.withDefaults());
//        //To enable authentication from Postman
//        httpSecurity.httpBasic(Customizer.withDefaults());
//        //To make HTTP call stateless
//        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        return httpSecurity.build();

        //Optimized coding style
        return httpSecurity
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        // This object will connect to DB
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        //While not using any encoder and saving password as text in DB
//        daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        // Mention the encoder used while saving the password....
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(10));
        //Override the default UserDetailsService
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        return daoAuthenticationProvider;
    }
}
