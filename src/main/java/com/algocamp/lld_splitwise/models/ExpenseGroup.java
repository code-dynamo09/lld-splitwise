package com.algocamp.lld_splitwise.models;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Table(name = "expense_group")
@RequiredArgsConstructor
public class ExpenseGroup extends BaseEntity{


    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @ManyToMany
    @JoinTable(name = "expense_group_members",
            joinColumns = @JoinColumn(name = "exoense_group_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")

    )
    private List<User> members;

}
