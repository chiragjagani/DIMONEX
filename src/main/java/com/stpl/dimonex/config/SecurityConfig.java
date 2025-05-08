//package com.stpl.dimonex.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.stpl.dimonex.service.CustomUserDetailsService;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;
//    
//    @Autowired
//    private CustomUserDetailsService userDetailsService;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//                .antMatchers("/login", "/register").permitAll() // Allow public access to login and register pages
//                .antMatchers("/polisher/**").hasRole("POLISHER")   // Only Polishers can access this page
//                .antMatchers("/manager/**").hasRole("MANAGER")     // Only Managers can access this page
//                .antMatchers("/admin/**").hasRole("ADMIN")         // Only Admins can access this page
//                .anyRequest().authenticated()                     // Other requests need authentication
//            .and()
//            .formLogin()
//                .loginPage("/login")                               // Custom login page URL
//                .loginProcessingUrl("/login")                       // URL to process login form
//                .successHandler(authenticationSuccessHandler)       // Use custom handler after login
//                .permitAll()
//            .and()
//            .logout()
//                .permitAll();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // Configure the authentication manager to use the CustomUserDetailsService to load users from the database
//        auth.userDetailsService(userDetailsService);
//    }
//}
