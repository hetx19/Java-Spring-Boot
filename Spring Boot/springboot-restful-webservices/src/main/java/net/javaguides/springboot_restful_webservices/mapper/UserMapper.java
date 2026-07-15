package net.javaguides.springboot_restful_webservices.mapper;

import net.javaguides.springboot_restful_webservices.dto.UserDto;
import net.javaguides.springboot_restful_webservices.entity.User;

public class UserMapper {
    // Convert User JPA Entity to UserDto
    public static UserDto mapToDto(User user) {
        UserDto userDto = new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
        return userDto;
    }

    // Convert UserDto to User JPA Entity
    public static User mapToEntity(UserDto userDto) {
        User user = new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
        return user;
    }
}
