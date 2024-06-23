package com.expleo.user_service.controller;

import com.expleo.user_service.dto.*;
import com.expleo.user_service.entity.Expense;
import com.expleo.user_service.entity.User;
import com.expleo.user_service.logging.LogExecutionTime;
import com.expleo.user_service.mapper.UserMapper;
import com.expleo.user_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@RefreshScope
@Slf4j
public class UserController {


    @Autowired
    private UserService userService;

    @Value("${email.dummy.property}")
    private String emailDummyTest;


    @Operation(summary = "Create user REST API", description = "REST API to create new user inside MyFinEx App")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))})
    @PostMapping("/create")
    @LogExecutionTime
    ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserDto userDto) {


        User user = userService.registerUser(userDto);
        log.info("The property from config-server test is: {}",emailDummyTest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserMapper.userToUserResponse(user));
    }

    @Operation(summary = "Login user REST API", description = "REST API to login user inside MyFinEx App")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))})
    @PostMapping("/login")
    @LogExecutionTime
    ResponseEntity<UserResponse> loginUser(@RequestBody LoginDto loginDto) {


        User user = userService.loginUser(loginDto.getUserName(), loginDto.getPassword());


        return ResponseEntity.status(HttpStatus.OK)
                .body(UserMapper.userToUserResponse(user));
    }


    @Operation(summary = "Delete user REST API", description = "REST API to delete user inside MyFinEx App")
    @ApiResponses({@ApiResponse(responseCode = "204", description = "HTTP Status NO CONTENT"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))})
    @DeleteMapping("/delete/{id}")
    @LogExecutionTime
    ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {


        userService.deleteUser(id);


        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("User is deleted:" + id);
    }


    @Operation(summary = "Update user REST API", description = "REST API to update user inside MyFinEx App")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))})
    @PutMapping("/update/{id}")
    @LogExecutionTime
    ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("id") Long userId) {


        User user = userService.updateUser(userDto, userId);


        return ResponseEntity.status(HttpStatus.OK)
                .body(UserMapper.userToUserResponse(user));
    }

    @Operation(summary = "Get user REST API", description = "REST API to get user inside MyFinEx App")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))})
    @GetMapping("/find/{id}")
    @LogExecutionTime
    ResponseEntity<UserResponse> getUserDetails(@PathVariable("id") Long userId) {


        User user = userService.getUser(userId);


        return ResponseEntity.status(HttpStatus.OK)
                .body(UserMapper.userToUserResponse(user));
    }


    @Operation(summary = "Get user expenses REST API", description = "REST API to get user expenses inside MyFinEx App")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))})
    @GetMapping("/expenses/{userId}")
    @LogExecutionTime
    ResponseEntity<List<ExpenseDto>> getUserExpenses(@PathVariable("userId") Long userId) {


        List<ExpenseDto> userExpenses = userService.getUserExpenses(userId);


        return ResponseEntity.status(HttpStatus.OK)
                .body(userExpenses);
    }


    @Operation(summary = "Get  all users REST API", description = "REST API to get  all users  inside MyFinEx App")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))})
    @GetMapping("/all")
    @LogExecutionTime
    ResponseEntity<List<UserResponse>> getAllUserDetails() {


        List<UserResponse> userResponseList = userService.getAllUsers().stream()
                .map(UserMapper::userToUserResponse).toList();


        return ResponseEntity.status(HttpStatus.OK)
                .body(userResponseList);
    }


    @GetMapping("/test")
    public String getDummyProperty(){
        return emailDummyTest;
    }



}
