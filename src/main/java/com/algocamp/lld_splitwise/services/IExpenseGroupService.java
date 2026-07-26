package com.algocamp.lld_splitwise.services;

import com.algocamp.lld_splitwise.models.ExpenseGroup;
import com.algocamp.lld_splitwise.models.User;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface IExpenseGroupService {

    ExpenseGroup createExpenseGroup(String name,Long createrId,  List<Long> memberIds);

    Boolean addMember(Long memberId, Long groupId);
}
