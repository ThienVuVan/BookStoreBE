package com.bookstore.common.service.impl;

import com.bookstore.common.entity.Order;
import com.bookstore.common.entity.User;
import com.bookstore.common.repository.UserRepository;
import com.bookstore.common.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User retrieveUserById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User retrieveByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public Boolean isExistUserName(String username) {
        return userRepository.existsUserByUsername(username);
    }

    @Override
    public Boolean isExistSocialId(String socialId) {
        return userRepository.existsUserBySocialId(socialId);
    }

    @Override
    public List<Order> retrieveOrdersByUserId(Integer id) {
        return userRepository.findOrderByUserId(id);
    }

    @Override
    public void deleteRoleForUser(Integer userId, Integer roleId) {
        userRepository.deleteRoleForUser(userId, roleId);
    }
}
