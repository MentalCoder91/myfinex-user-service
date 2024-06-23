package com.expleo.user_service.service.client;


import com.expleo.user_service.dto.Budget;
import com.expleo.user_service.dto.BudgetCategory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class BudgetClientFallbackFactory implements FallbackFactory<BudgetClient> {
    @Override
    public BudgetClient create(Throwable cause) {
        return new BudgetClient() {
            @Override
            public List<BudgetCategory> getAllCategories() {
                return null;
            }
        };
    }
}