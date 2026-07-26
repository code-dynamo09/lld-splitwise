package com.algocamp.lld_splitwise.services.strategies.split;

import com.algocamp.lld_splitwise.models.Expense;
import com.algocamp.lld_splitwise.models.Split;
import com.algocamp.lld_splitwise.models.User;

import java.util.List;
import java.util.Map;

public interface SplitStrategy {

    List<Split> calculateSplit(Expense expense,
                               List<User> users,
                               Map<Long, Double> paidMap,
                               Map<Long, Double> percentageMap);
    // paidMap is the amount users had paid for the expense
    // percentageMap is if splitType is percentWise,
    // then it shows which user owes what share of amoount
}
