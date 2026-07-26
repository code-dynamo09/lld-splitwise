package com.algocamp.lld_splitwise.repositories;

import com.algocamp.lld_splitwise.models.ExpenseGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseGroupRepository extends JpaRepository<ExpenseGroup,Long > {
}
