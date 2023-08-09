package com.bookstore.common.security.service;

import com.bookstore.common.entity.Role;
import com.bookstore.common.entity.User;
import com.bookstore.common.service.RoleService;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    private final RoleService roleService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.retrieveByUserName(username);
        if(user == null) throw new UsernameNotFoundException("User Not Found");
        List<Role> roles = roleService.retrieveRoleByUserName(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.stream().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails
                .User(user.get().getUsername(), user.get().getPassword(), authorities);
    }
}
