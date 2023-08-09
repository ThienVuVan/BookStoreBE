package com.bookstore.common.service.impl;

import com.bookstore.common.configuration.BeanUtils;
import com.bookstore.common.dto.UserDto;
import com.bookstore.common.entity.User;
import com.bookstore.common.repository.UserRepository;
import com.bookstore.common.service.UserService;
import com.bookstore.modules.user.dto.UserMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    @Override
    public List<UserDto> retrieveAllUser() {
        return userRepository.findAll().stream().map(user -> userMapper.UserToUserDto(user))
                .collect(Collectors.toList());
    }
    @Override
    public User saveUser(User user) {
        PasswordEncoder passwordEncoder = BeanUtils.getBean(PasswordEncoder.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Override
    public Boolean isExistUserName(String username) {
        return userRepository.existsUserByUsername(username);
    }

    @Override
    public Boolean isExistEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public Boolean isExistPhoneNumber(String phoneNumber) {
        return userRepository.existsUserByPhoneNumber(phoneNumber);
    }
}
