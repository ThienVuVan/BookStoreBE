package com.bookstore.common.service;

import com.bookstore.common.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> retrieveAllUser();
    public User saveUser(User user);
    public User updateUser(User user);
    public void deleteUser(User user);
    public Optional<User> retrieveByUserName(String username);
}
