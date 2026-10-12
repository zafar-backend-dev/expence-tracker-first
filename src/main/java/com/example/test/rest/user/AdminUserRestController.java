package com.example.test.rest.user;

import com.example.test.dto.ApiResponse;
import com.example.test.dto.user.UserResponseDto;
import com.example.test.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/admin/user")
public class AdminUserRestController {
    private final UserService userService;

    public AdminUserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("list")
    public ApiResponse<List<UserResponseDto>> getAllUsers() {
        return userService.getAllUsers();
    }
}
