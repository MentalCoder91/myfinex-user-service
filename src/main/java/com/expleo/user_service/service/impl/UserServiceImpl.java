package com.expleo.user_service.service.impl;

import com.expleo.user_service.dto.Budget;
import com.expleo.user_service.dto.BudgetCategory;
import com.expleo.user_service.dto.ExpenseDto;
import com.expleo.user_service.dto.UserDto;
import com.expleo.user_service.entity.Expense;
import com.expleo.user_service.entity.User;
import com.expleo.user_service.exception.ExpensesNotFoundException;
import com.expleo.user_service.exception.ResourceNotFoundException;
import com.expleo.user_service.exception.UserAlreadyExistsException;
import com.expleo.user_service.exception.UserNotFoundException;
import com.expleo.user_service.mapper.UserMapper;
import com.expleo.user_service.repository.UserRepository;
import com.expleo.user_service.service.UserService;
import com.expleo.user_service.service.client.BudgetClient;
import com.expleo.user_service.service.client.ExpenseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ExpenseClient expenseClient;

    @Autowired
    private BudgetClient budgetClient;


    @Override
    public User registerUser(UserDto user) {
        //Check before registering ->user exists
        String userName = user.getUserName();
        String password = user.getPassword();

        boolean b = userRepository.findByUserName(userName)
                .stream().anyMatch(user1 -> passwordEncoder.matches(password, user1.getPassword()));

        if (b) {
            throw new UserAlreadyExistsException("User already exists... Please login!!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User save = userRepository.save(UserMapper.userDTOToUser(user));
        save.setPassword(user.getPassword());
        return save;


    }

    @Override
    public User loginUser(String username, String password) {

        List<User> users = userRepository.findByUserName(username);

        Optional<User> first = users.stream()
                .filter(u -> passwordEncoder.matches(password, u.getPassword())).findFirst();

        if (first.isEmpty()) {
            throw new UserNotFoundException("Check the entered userName and password!!");

        }
        return first.get();

    }

    @Override
    public User updateUser(UserDto user, Long userId) {

        Optional<User> first = userRepository.findById(userId);

        if (first.isEmpty()) {
            throw new UserNotFoundException("Check the details Entered");
        }

        User userDb = first.get();
        userDb.setUserName(user.getUserName());
        userDb.setPassword(passwordEncoder.encode(user.getPassword()));
        userDb.setEmailId(user.getEmailId());
        userDb.setPhoneNumber(user.getPhoneNumber());

        return userRepository.save(userDb);
    }


    @Override
    public void deleteUser(Long userId) {

        Optional<User> byId = userRepository.findById(userId);
        if (byId.isEmpty()) {
            throw new UserNotFoundException("Check the userId exists!!");
        }
        userRepository.deleteById(byId.get().getId());

    }

    @Override
    public User getUser(Long userId) {
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isEmpty()) {
            throw new UserNotFoundException("Check the userId exists!!");
        }
        return byId.get();

    }

    @Override
    public List<ExpenseDto> getUserExpenses(Long userId) {

        List<Expense> expensesForUsers = expenseClient.getExpensesForUsers(userId);

        List<BudgetCategory> budgets = budgetClient.getAllCategories();

        if (expensesForUsers.isEmpty()) {
            throw new ExpensesNotFoundException("User does not have any expenses");
        }
        List<ExpenseDto> expenseDtoList = expensesForUsers
                .stream()
                .map(expense -> {
                    ExpenseDto dto = new ExpenseDto();
                    dto.setUserId(expense.getUserId());
                    dto.setId(expense.getId());
                    dto.setAmount(expense.getAmount());
                    dto.setDescription(expense.getDescription());
                    dto.setCategoryId(expense.getCategoryId());
                    Optional<BudgetCategory> first = budgets.stream()
                            .filter(budget -> Objects.equals(budget.getId(), expense.getCategoryId())).findFirst();
                    dto.setCategoryName(first.get().getName());
                    return dto;
                }).toList();


        return expenseDtoList;


    }

    @Override
    public List<User> getAllUsers() {
        List<User> all = userRepository.findAll();
        if (all.isEmpty()) {
            throw new UserNotFoundException("No users are found in the system");
        }

        return all;
    }


}
