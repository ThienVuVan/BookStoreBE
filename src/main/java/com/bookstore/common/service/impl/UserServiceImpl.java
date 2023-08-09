package com.bookstore.common.service.impl;

import com.bookstore.common.entity.User;
import com.bookstore.common.repository.UserRepository;
import com.bookstore.common.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public List<User> retrieveAllUser() {
        return userRepository.findAll();
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
    public Optional<User> retrieveByUserName(String username) {
        return userRepository.findByUsername(username);
    }
}
