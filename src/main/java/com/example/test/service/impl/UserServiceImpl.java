package com.example.test.service.impl;

import com.example.test.db.entity.user.User;
import com.example.test.db.repositories.UserRepository;
import com.example.test.dto.ApiResponse;
import com.example.test.dto.user.UserResponseDto;
import com.example.test.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ApiResponse<List<UserResponseDto>> getAllUsers() {
        List<User> list = userRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        for (User user : list) {
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setId(user.getPkey());
            userResponseDto.setFullName(user.getFullName());
            userResponseDto.setBirthdate(user.getBirthdate());
            userResponseDto.setRole(user.getRole());
            userResponseDto.setUsername(user.getUsername());
            userResponseDto.setEmail(user.getEmail());
            userResponseDto.setEnabled(user.getEnabled());
            userResponseDto.setCreatedAt(user.getCreatedAt());
            userResponseDto.setUpdatedAt(user.getUpdatedAt());
            userResponseDtoList.add(userResponseDto);
        }
        return new ApiResponse<>(true, "Success", "Tamam", userResponseDtoList);
    }
}
