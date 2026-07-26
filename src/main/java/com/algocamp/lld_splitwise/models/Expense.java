package com.algocamp.lld_splitwise.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Builder
@Table(name = "expense")
public class Expense extends BaseEntity{

    @Column(name ="description", nullable = false)
    private String description;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @Column(name = "split_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private SplitType splitType;

    @ManyToOne
    @JoinColumn(name = "expense_group_id", nullable = false)
    private ExpenseGroup expenseGroup;

    @ManyToOne
    @JoinColumn(name = "user_created_by_id", nullable = false)
    private User createdBy;

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL)
    @JsonManagedReference //
    private List<Split> splits = new ArrayList<>();


    @Transient
    private List<Long> userIds;
}
