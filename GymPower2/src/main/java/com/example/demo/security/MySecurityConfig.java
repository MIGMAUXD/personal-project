package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.service.impl.UserDetailsServiceImpl;


import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class MySecurityConfig {


    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    
    @Autowired
    public JwtAuthenticationFilter jwtAuthenticationFilter;
    
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       
    	return http
        .csrf(csrf -> csrf.disable())
                .cors(withDefaults())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/login","/usuarios").permitAll()
                .requestMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    	
    	/*http
        .csrf(csrf -> csrf.disable())
                .cors(withDefaults())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/generate-token","/usuarios").permitAll()
                .requestMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated());
                
        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
     
        	return  http.build();*/
//    	return http
//        		.csrf().disable()
//        		.authorizeRequests()
//        		.anyRequest()
//        		.authenticated()
//        		.and()
//        		.httpBasic()
//        		.and()
//        		.sessionManagement()
//        		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        		.and()
//        		.build();
    	
    }
    
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder ) throws Exception {
      return http.getSharedObject(AuthenticationManagerBuilder.class)
    		  .userDetailsService(userDetailsServiceImpl)
    		  .passwordEncoder(passwordEncoder())
    		  .and()
    		  .build();
    }
    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
     return new BCryptPasswordEncoder();
    }
    
    
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
         
        authProvider.setUserDetailsService(userDetailsServiceImpl);
        authProvider.setPasswordEncoder(passwordEncoder());
     
        return authProvider;
    }
    
   
    
}
