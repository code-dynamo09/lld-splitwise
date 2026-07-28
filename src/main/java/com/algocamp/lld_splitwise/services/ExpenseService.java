package com.algocamp.lld_splitwise.services;

import com.algocamp.lld_splitwise.models.*;
import com.algocamp.lld_splitwise.repositories.ExpenseGroupRepository;
import com.algocamp.lld_splitwise.repositories.ExpenseRepository;
import com.algocamp.lld_splitwise.repositories.SplitRepository;
import com.algocamp.lld_splitwise.repositories.UserRepository;
import com.algocamp.lld_splitwise.services.strategies.split.SplitStrategy;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExpenseService implements IExpenseService{

    private final ExpenseGroupRepository expenseGroupRepository;
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final SplitRepository splitRepository;
    private Map<SplitType, SplitStrategy> strategyMap;



    @Override
    public Expense addExpenseWithStrategy(String description, Double amount, String strategy,
                                          Long groupId, User user,
                                          Map<Long, Double> paidMap,
                                          Map<Long, Double> percentageMap) {

        ExpenseGroup group = expenseGroupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("group not found"));

        List<User> users = group.getMembers();
        List<Long> userIds = users.stream().map(User::getId).toList();

        SplitStrategy splitStrategy = this.strategyMap.get(SplitType.valueOf(strategy));
        if(splitStrategy != null) {
            Expense expense = Expense.builder()
                    .description(description)
                    .expenseGroup(group)
                    .createdBy(user)
                    .totalAmount(amount)
                    .userIds(userIds)
                    .splitType(SplitType.valueOf(strategy))
                    . build();
            List<Split> splits = splitStrategy.calculateSplit(expense, users, paidMap, percentageMap);

            expense.setSplits(splits);
            splitRepository.saveAll(splits);
            expenseRepository.save(expense);

            return expense;

        }
        return null;
    }

    @Override
    public Optional<Expense> getExpenseById(Long expenseId) {
        return Optional.of(expenseRepository.findById(expenseId).orElseThrow(() -> new RuntimeException("expense not found")));
    }

    @PostConstruct
    public void initialiseStrategyMap() {
        this.strategyMap.put("EQUAL", SplitType.EQUAL);
        this.strategyMap.put("PERCENTAGE", SplitType.PERCENTAGE);

    }
}
