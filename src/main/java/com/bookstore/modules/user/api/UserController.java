package com.bookstore.modules.user.api;

import com.bookstore.common.entity.User;
import com.bookstore.common.enums.URI;
import com.bookstore.common.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Operation(summary = "Retrieve All Users", description = "Retrieve All Users")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = {URI.USERS})
    public ResponseEntity<List<?>> RetrieveAllUser(){
        return new ResponseEntity<>(userService.retrieveAllUser(), HttpStatus.OK);
    }

    @Operation(summary = "Post A New User", description = "Post A New User")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(value = {URI.USERS})
    public ResponseEntity CreateNewUser(@Valid @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String, Object> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(),  error.getDefaultMessage()));
            return new ResponseEntity(errors,HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PutMapping(value = {URI.USERS})
    public ResponseEntity<Object> UpdateUser(@Valid @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String, Object> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(),  error.getDefaultMessage()));
            return new ResponseEntity(errors,HttpStatus.BAD_REQUEST);
        }
        userService.updateUser(user);
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping(value = {URI.USERS})
    public ResponseEntity<Object> DeleteUser(@Valid @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String, Object> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(),  error.getDefaultMessage()));
            return new ResponseEntity(errors,HttpStatus.BAD_REQUEST);
        }
        userService.deleteUser(user);
        return new ResponseEntity(HttpStatus.OK);
    }
}
