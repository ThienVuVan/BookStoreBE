package com.bookstore.common.security.filter;

import com.bookstore.common.enums.Uri;
import com.bookstore.common.security.service.TokenAuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = new TokenAuthenticationService().authenticateRequestByToken(request);
        // Set into SecurityContextHolder to Authorization;
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Goto EndPoint;
        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        /* if request start with these request -> does not filter */
        String requestURI = request.getRequestURI();
        return requestURI.startsWith("/swagger-ui") || requestURI.startsWith("/swagger-resources")
                || requestURI.startsWith("/v3/api-docs")  || requestURI.startsWith(Uri.USERS_LOGIN)
                || requestURI.startsWith(Uri.USERS_SIGNUP);
    }
}
