package com.bookstore.modules.auth.api;

import com.bookstore.common.enums.URI;
import com.bookstore.common.security.credentials.AccountCredentials;
import com.bookstore.common.security.service.TokenAuthenticationService;
import com.bookstore.modules.auth.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    @PostMapping(value = {URI.USERS_LOGIN})
    public ResponseEntity<?> login(@RequestBody AccountCredentials accountCredentials, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("binding error");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(accountCredentials.getUsername(), accountCredentials.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        TokenResponse tokenResponse = new TokenAuthenticationService().generateToken(username);
        return ResponseEntity.ok(tokenResponse);
    }
}
