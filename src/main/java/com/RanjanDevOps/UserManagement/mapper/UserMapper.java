package com.RanjanDevOps.UserManagement.mapper;

import com.RanjanDevOps.UserManagement.dto.UserDto;
import com.RanjanDevOps.UserManagement.entity.User;

public class UserMapper {

    public static UserDto mapToUserDTo(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return userDto;
    }

    public static User mapToUser(UserDto userDto) {

        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
        return user;

    }
}