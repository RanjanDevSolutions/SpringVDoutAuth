package com.RanjanDevOps.UserManagement.service.Impl;

import com.RanjanDevOps.UserManagement.dto.UserDto;
import com.RanjanDevOps.UserManagement.entity.User;
//import com.RanjanDevOps.UserManagement.mapper.UserMapper;
import com.RanjanDevOps.UserManagement.repository.UserRepository;
import com.RanjanDevOps.UserManagement.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
//        convert UserDto to UserJPA entity
//        User user=new User(
//                userDto.getId(),
//                userDto.getFirstName(),
//                userDto.getLastName(),
//                userDto.getEmail()
//        ); use mapper instead of this .option 1 on how to do this
     //   //User user = UserMapper.mapToUser(userDto);
     //   //   use model mapper library instead.option 2 on how to do it

        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
//        convert User JPA Entity to UserDto option1
//        UserDto savedUserDto = new UserDto(
//                savedUser.getId(),
//                savedUser.getFirstName(),
//                savedUser.getLastName(),
//                savedUser.getEmail()
//        );
        //option2   //UserDto savedUserDto =UserMapper.mapToUserDTo(savedUser);
        UserDto savedUserDto =modelMapper.map(savedUser, UserDto.class);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> optionalUser=userRepository.findById(userId);
        User user =optionalUser.get();
     //option2  // return UserMapper.mapToUserDTo(user);
        return modelMapper.map(user, UserDto.class);

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
      //option2  //return user.stream().map(UserMapper::mapToUserDTo).collect(Collectors.toList());
        return users.stream().map((user) -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);

      // option2 //return UserMapper.mapToUserDTo(updatedUser);
        return modelMapper.map(updatedUser, UserDto.class);

    }

    @Override
    public void deleteUser(Long userId) {
    userRepository.deleteById(userId);
    }
}
