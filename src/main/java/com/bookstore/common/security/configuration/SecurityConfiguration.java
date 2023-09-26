package com.bookstore.common.security.configuration;

import com.bookstore.common.enums.Uri;
import com.bookstore.common.security.exception.AuthenticationEntryPointImpl;
import com.bookstore.common.security.filter.JWTAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
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
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /* Authorization Request */
        /* Update role for request later */
        http.authorizeHttpRequests(request -> request
                .requestMatchers(HttpMethod.GET, Uri.USERS).hasAnyRole("USER", "SHOP", "ADMIN")
                .requestMatchers(HttpMethod.GET, Uri.SHOPS).hasAnyRole("SHOP", "ADMIN")
                .requestMatchers("/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**").permitAll()
                .requestMatchers(Uri.USERS_LOGIN, Uri.USERS_SIGNUP, Uri.SOCIAL).permitAll()
                .requestMatchers(Uri.BOOKS_PAGE, Uri.BOOKS_FILTER, Uri.BOOKS_DETAILS,
                        Uri.BOOKS_REVIEWS, Uri.BOOKS_RATES, Uri.BOOKS_SHOP).permitAll()
                .requestMatchers(HttpMethod.GET, Uri.CATEGORIES).permitAll()
                .requestMatchers(Uri.SHOPS_BOOK_ID, Uri.SHOPS_ID).permitAll()
                .anyRequest().authenticated()
        );
        /* Authentication JWT Filter */
        http.addFilterBefore((new JWTAuthenticationFilter()), UsernamePasswordAuthenticationFilter.class);

        /* Disable Basic Authentication */
        http.httpBasic(request -> request.disable());

        /* Disable Cross Site Request Forgery */
        http.csrf(request -> request.disable());

        /* Set CORS default */
        http.cors(Customizer.withDefaults());

        /* Set StateLess to Session */
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        /* Handle Authentication Exception */
        http.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint()));

        /* Build */
        return http.build();
    }
}

