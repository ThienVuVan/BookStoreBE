package com.bookstore.modules.auth.api;

import com.bookstore.common.entity.Role;
import com.bookstore.common.entity.User;
import com.bookstore.common.enums.Constant;
import com.bookstore.common.enums.Uri;
import com.bookstore.common.security.service.TokenAuthenticationService;
import com.bookstore.common.security.service.UserDetailsImpl;
import com.bookstore.common.service.RoleService;
import com.bookstore.common.service.UserService;
import com.bookstore.modules.auth.request.*;
import com.bookstore.modules.auth.response.JwtResponse;
import com.bookstore.modules.auth.response.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = {Uri.USERS_LOGIN})
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> listToken = new TokenAuthenticationService().generateToken(user.getUsername());
        return ResponseEntity.ok(new JwtResponse(
                user.getId(),
                user.getUsername(),
                user.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList()),
                listToken.get(0),
                listToken.get(1)
                ));
    }

    @PostMapping(value = {Uri.USERS_SIGNUP})
    public ResponseEntity<?> register(@Valid @RequestBody SignupRequest signupRequest){
        if(userService.isExistUserName(signupRequest.getUsername())){
            return new ResponseEntity<>(new MessageResponse("UserName is already taken!"), HttpStatus.BAD_REQUEST);
        }

        User user = new User(signupRequest.getUsername(), signupRequest.getPhoneNumber(), signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()), Constant.LOCAL, null);

        List<String> strRoles = signupRequest.getRoles();

        if(strRoles == null){
            Optional<Role> role = roleService.retrieveByName(Constant.ROLE_USER);
            if(!role.isPresent()) throw new RuntimeException("Role is not found!");
            user.addRole(role.get());
        }else{
            strRoles.forEach(strRole -> {
                switch (strRole) {
                    case "admin":
                        Optional<Role> roleAdmin = roleService.retrieveByName(Constant.ROLE_ADMIN);
                        if(!roleAdmin.isPresent()) throw new RuntimeException("Role is not found!");
                        user.addRole(roleAdmin.get());
                        break;
                    case "shop":
                        Optional<Role> roleShop = roleService.retrieveByName(Constant.ROLE_SHOP);
                        if(!roleShop.isPresent()) throw new RuntimeException("Role is not found!");
                        user.addRole(roleShop.get());
                        break;
                    default:
                        Optional<Role> roleUser = roleService.retrieveByName(Constant.ROLE_USER);
                        if(!roleUser.isPresent()) throw new RuntimeException("Role is not found!");
                        user.addRole(roleUser.get());
                }
            });
        }
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = Uri.SOCIAL)
    public ResponseEntity<?> SocialSignIn(@Valid @RequestBody SocialRequest socialRequest){
        if(userService.isExistSocialId(socialRequest.getSocialId())){
           User user = userService.retrieveByUserName(socialRequest.getUsername());
           List<Role> roles = roleService.retrieveRoleByUserName(socialRequest.getUsername());
           List<String> listToken = new TokenAuthenticationService().generateToken(user.getUsername());
           return ResponseEntity.ok(new JwtResponse(
                   user.getId(),
                   user.getUsername(),
                   roles.stream().map(item -> item.getName()).collect(Collectors.toList()),
                   listToken.get(0),
                   listToken.get(1)
           ));
        }
        else{
            if(userService.isExistUserName(socialRequest.getUsername())){
                return new ResponseEntity<>(new MessageResponse("UserName is already taken!"), HttpStatus.BAD_REQUEST);
            }
            User user = null;
            if(socialRequest.getType() == 0){
                user = userService.saveUser(
                        new User(socialRequest.getUsername(), "0000000000", socialRequest.getEmail(),
                                "00000000", Constant.GOOGLE, socialRequest.getSocialId()));
            }
            if(socialRequest.getType() == 1){
                user = userService.saveUser(
                        new User(socialRequest.getUsername(), "0000000000", socialRequest.getEmail(),
                                "00000000", Constant.FACEBOOK, socialRequest.getSocialId()));
            }
            // get role set for user;
            Optional<Role> role = roleService.retrieveByName(Constant.ROLE_USER);
            user.addRole(role.get());
            userService.updateUser(user);

            List<Role> roles = roleService.retrieveRoleByUserName(socialRequest.getUsername());
            List<String> listToken = new TokenAuthenticationService().generateToken(user.getUsername());
            return ResponseEntity.ok(new JwtResponse(
                    user.getId(),
                    user.getUsername(),
                    roles.stream().map(item -> item.getName()).collect(Collectors.toList()),
                    listToken.get(0),
                    listToken.get(1)
            ));
        }
    }
}
