package com.algocamp.lld_splitwise.services;

import com.algocamp.lld_splitwise.models.Expense;
import com.algocamp.lld_splitwise.models.SplitType;
import com.algocamp.lld_splitwise.models.User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public interface IExpenseService {

    Expense addExpenseWithStrategy(String description, Double amount, String strategy,
                                   Long groupId, User user,
                                   Map<Long, Double> paidMap,
                                   Map<Long, Double> percentageMap);

    Optional<Expense> getExpenseById(Long expenseId);

}
