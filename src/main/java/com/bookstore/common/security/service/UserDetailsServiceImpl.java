package com.bookstore.common.security.service;

import com.bookstore.common.entity.User;
import com.bookstore.common.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.retrieveByUserName(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().stream().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new UserDetailsImpl(user.getUsername(), user.getPassword(), authorities);
    }
}
