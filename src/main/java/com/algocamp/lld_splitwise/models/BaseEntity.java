package com.algocamp.lld_splitwise.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass // Tells JPA that fields of this class should be inherited by subclasses
@EntityListeners(AuditingEntityListener.class) // Captures auditing information on persistence events
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incrementing id
    protected Long id;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    protected Date created_at;


    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    protected Date updated_at;

    @PrePersist
    protected void initialise() {
        this.created_at = Date.from(Instant.from(LocalDateTime.now()));
        this.updated_at = Date.from(Instant.from(LocalDateTime.now()));
    }

}
