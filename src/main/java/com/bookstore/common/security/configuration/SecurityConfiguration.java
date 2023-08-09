package com.bookstore.common.security.configuration;

import com.bookstore.common.enums.URI;
import com.bookstore.common.security.exception.AuthenticationEntryPointImpl;
import com.bookstore.common.security.filter.JWTAuthenticationFilter;
import com.bookstore.common.security.filter.JWTLoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new AuthenticationEntryPointImpl();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        return new ProviderManager(Collections.singletonList(authenticationProvider()));
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        /* Authorization Request */
        http.authorizeHttpRequests(request -> request
                .requestMatchers(HttpMethod.GET,URI.USERS).hasAnyRole("ADMIN")
                .requestMatchers("/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**").permitAll()
                .requestMatchers(HttpMethod.POST,URI.USERS_LOGIN).permitAll()
                .anyRequest().authenticated()
        );

        /* Login Filter */
        /* Using API */
        //http.addFilterBefore(new JWTLoginFilter(URI.USERS_LOGIN, authenticationManager()), UsernamePasswordAuthenticationFilter.class);

        /* Authentication JWT Filter */
        http.addFilterBefore((new JWTAuthenticationFilter()), UsernamePasswordAuthenticationFilter.class);

        /* Disable Basic Authentication */
        http.httpBasic(request -> request.disable());

        /* Basic Authentication */
        //http.httpBasic(Customizer.withDefaults());

        /* Disable Cross Site Request Forgery */
        http.csrf(request -> request.disable());

        /* Set StateLess to Session */
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        /* Handle Authentication Exception */
        //http.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint()));
        /* Build */
        return http.build();
    }
}

