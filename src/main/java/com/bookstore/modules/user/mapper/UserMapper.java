package com.bookstore.modules.user.mapper;

import com.bookstore.modules.user.dto.UserDto;
import com.bookstore.common.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    User UserDtoToUser(UserDto userDto);
    UserDto UserToUserDto(User user);
}
