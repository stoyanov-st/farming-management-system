package com.styoanovst.task.farmingmanagementsystem.service;

import com.styoanovst.task.farmingmanagementsystem.dto.UserDto;
import com.styoanovst.task.farmingmanagementsystem.model.User;
import com.styoanovst.task.farmingmanagementsystem.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void registerUser(UserDto userDto) throws Exception {

        if (userRepository.findByUsername(userDto.getUsername()) == null) {
            User user = dtoToEntity(userDto);
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            userRepository.save(user);
        } else throw  new Exception("Existing username!");
    }

    private User dtoToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    private UserDto entityToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
