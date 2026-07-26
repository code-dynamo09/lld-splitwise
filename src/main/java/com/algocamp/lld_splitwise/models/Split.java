package com.algocamp.lld_splitwise.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@Table(name = "split")
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Split extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "expense_id", nullable = false)
    @JsonBackReference
    private Expense expense;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "paid", nullable = false)
    private Double paid;

    @Column(name = "owed", nullable = false)
    private Double owed;


}
