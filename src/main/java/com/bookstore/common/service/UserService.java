package com.bookstore.common.service;

import com.bookstore.common.dto.UserDto;
import com.bookstore.common.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<UserDto> retrieveAllUser();
    public User saveUser(User user);
    public User updateUser(User user);
    public void deleteUser(User user);
    public Optional<User> retrieveByUserName(String username);
    public Boolean isExistUserName(String username);
    public Boolean isExistEmail(String email);
    public Boolean isExistPhoneNumber(String phoneNumber);


}
