package com.algocamp.lld_splitwise.services.strategies.split;


import com.algocamp.lld_splitwise.models.Expense;
import com.algocamp.lld_splitwise.models.Split;
import com.algocamp.lld_splitwise.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PercentageStrategy implements SplitStrategy {
    @Override
    public List<Split> calculateSplit(Expense expense,
                                      List<User> users,
                                      Map<Long, Double> paidMap,
                                      Map<Long, Double> percentageMap) {

        if(users == null || users.isEmpty()) {
            throw new IllegalArgumentException("user list can't be empty");
        }

        double totalAmount = expense.getTotalAmount();
//        List<Split> splits = new ArrayList<>();
//        for(User user: users) {
//            if(!paidMap.containsKey(user.getId())) {
//                paidMap.put(user.getId(), 0.0);
//            }
//            if(!percentageMap.containsKey(user.getId())) {
//                percentageMap.put(user.getId(), 0.0);
//            }
//            double owe = (percentageMap.getOrDefault(user.getId(), 0.0)*totalAmount)/100.0;
//            splits.add(new Split(expense, user,
//                    paidMap.getOrDefault(user.getId(), 0.0),owe));
//        }

//        return splits;

        // using streams

        return users.stream().map(user -> {
           double paid = paidMap != null ? paidMap.getOrDefault(user.getId(), 0.0) : 0.0;
           double percent = percentageMap != null
                   ? percentageMap.getOrDefault(user.getId(), 0.0)
                   : 0.0;
           double owed = (percent * totalAmount) / 100.0;
           return new Split(expense, user, paid, owed);
        }).collect(Collectors.toList());
    }
}
