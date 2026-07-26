package com.algocamp.lld_splitwise.repositories;

import com.algocamp.lld_splitwise.models.Split;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SplitRepository extends JpaRepository<Split, Long> {
}
