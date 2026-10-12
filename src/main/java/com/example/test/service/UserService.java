package com.example.test.service;

import com.example.test.dto.ApiResponse;
import com.example.test.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {
    ApiResponse<List<UserResponseDto>> getAllUsers();
}
