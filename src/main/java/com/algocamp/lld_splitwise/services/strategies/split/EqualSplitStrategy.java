package com.algocamp.lld_splitwise.services.strategies.split;

import com.algocamp.lld_splitwise.models.Expense;
import com.algocamp.lld_splitwise.models.Split;
import com.algocamp.lld_splitwise.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EqualSplitStrategy implements SplitStrategy{



    @Override
    public List<Split> calculateSplit(Expense expense, List<User> users, Map<Long, Double> paidMap, Map<Long, Double> percentageMap) {

        if(users == null || users.isEmpty()) {
            throw new IllegalArgumentException("user list can't be emoty");
        }

        double totalAmount = expense.getTotalAmount();
        double equalShare = totalAmount / users.size();

        List<Split> splits = new ArrayList<>();

        for (User user: users) {
            if(!paidMap.containsKey(user.getId()) ){
                paidMap.put(user.getId(), 0.0);
            }
            splits.add(new Split(expense, user, paidMap.get(user.getId()), equalShare));
        }

        return splits;
    }
}
