package net.javaguides.springboot_restful_webservices.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot_restful_webservices.dto.UserDto;
import net.javaguides.springboot_restful_webservices.entity.User;
// import net.javaguides.springboot_restful_webservices.mapper.UserMapper;
import net.javaguides.springboot_restful_webservices.exception.EmailAlreadyExistsException;
import net.javaguides.springboot_restful_webservices.exception.ResourceNotFoundException;
import net.javaguides.springboot_restful_webservices.repository.UserRepository;
import net.javaguides.springboot_restful_webservices.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email Already Exists for User");
        }

        // Convert UserDto into user JPA Entity
        // User user = UserMapper.mapToEntity(userDto);
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);

        // Convert User JPA Entity into UserDto
        // UserDto userDtoResponse = UserMapper.mapToDto(savedUser);


        UserDto userDtoResponse = modelMapper.map(savedUser,  UserDto.class);

        return userDtoResponse;
    }

    @Override
    public UserDto getUserById(@RequestParam Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        // return UserMapper.mapToDto(user);
        return  modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        // return users.stream().map(UserMapper::mapToDto).collect(Collectors.toList());
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("User", "id", user.getId()));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());

        User updatedUser = userRepository.save(existingUser);
        // return UserMapper.mapToDto(updatedUser);
        return modelMapper.map(updatedUser,  UserDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        userRepository.deleteById(id);
    }
}
