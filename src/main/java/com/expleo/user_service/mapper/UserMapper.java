package com.expleo.user_service.mapper;

import com.expleo.user_service.dto.UserDto;
import com.expleo.user_service.dto.UserResponse;
import com.expleo.user_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


public class UserMapper {




    public static UserDto userToUserDTO(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setPassword(user.getPassword());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setEmailId(user.getEmailId());

        return userDto;
    }

    public static User userDTOToUser(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEmailId(userDto.getEmailId());

        return user;
    }

    public static UserResponse userToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getId());
        userResponse.setUserName(user.getUserName());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setEmailId(user.getEmailId());

        return userResponse;
    }




}
