package com.bookstore.modules.user.service;

import com.bookstore.common.entity.User;
import com.bookstore.modules.user.dto.UserDto;
import com.bookstore.modules.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserModuleService {
    private final UserMapper userMapper;
    public UserDto convertToUserDto(User user){
        return userMapper.UserToUserDto(user);
    }
}
