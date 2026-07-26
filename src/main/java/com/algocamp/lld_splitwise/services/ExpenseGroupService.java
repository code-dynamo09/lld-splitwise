package com.algocamp.lld_splitwise.services;

import com.algocamp.lld_splitwise.models.ExpenseGroup;
import com.algocamp.lld_splitwise.models.User;
import com.algocamp.lld_splitwise.repositories.ExpenseGroupRepository;
import com.algocamp.lld_splitwise.repositories.ExpenseRepository;
import com.algocamp.lld_splitwise.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseGroupService implements IExpenseGroupService {

    private final UserRepository userRepository;
    private final  ExpenseGroupRepository expenseGroupRepository;

    @Autowired
    public ExpenseGroupService(UserRepository userRepository, ExpenseGroupRepository expenseGroupRepository) {
        this.expenseGroupRepository = expenseGroupRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ExpenseGroup createExpenseGroup(String name, Long createrId, List<Long> memberIds) {
        User creater = userRepository.findById(createrId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<User> members = userRepository.findAllById(memberIds);

        ExpenseGroup expenseGroup = ExpenseGroup.builder()
                .name(name)
                .owner(creater)
                .members(members)
                .build();

        expenseGroupRepository.save(expenseGroup);
        return expenseGroup;

    }

    @Override
    public Boolean addMember(Long memberId, Long groupId) {

        User member = userRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ExpenseGroup expenseGroup = expenseGroupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Expense group not found"));

        expenseGroup.getMembers().add(member);
        expenseGroupRepository.save(expenseGroup);

        return null;
    }
}
