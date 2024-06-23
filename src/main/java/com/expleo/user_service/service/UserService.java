package com.expleo.user_service.service;

import com.expleo.user_service.dto.ExpenseDto;
import com.expleo.user_service.dto.UserDto;
import com.expleo.user_service.entity.Expense;
import com.expleo.user_service.entity.User;

import java.util.List;

public interface UserService {


    public User registerUser(UserDto user);

    public User loginUser(String username, String password);


    public User updateUser(UserDto user,Long userId);

    public void deleteUser(Long userId);

    public User getUser(Long userId);

    public List<ExpenseDto> getUserExpenses(Long userId);

    public List<User> getAllUsers();



}
