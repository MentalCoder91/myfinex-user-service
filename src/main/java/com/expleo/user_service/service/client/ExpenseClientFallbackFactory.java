package com.expleo.user_service.service.client;


import com.expleo.user_service.entity.Expense;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ExpenseClientFallbackFactory implements FallbackFactory<ExpenseClient> {

    @Override
    public ExpenseClient create(Throwable cause) {
        return new ExpenseClient() {
            @Override
            public List<Expense> getExpensesForUsers(Long userId) {
                return null;
            }
        };
    }
}