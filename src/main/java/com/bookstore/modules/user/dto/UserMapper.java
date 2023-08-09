package com.bookstore.modules.user.dto;

import com.bookstore.common.dto.UserDto;
import com.bookstore.common.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User UserDtoToUser(UserDto userDto);
    UserDto UserToUserDto(User user);
}
