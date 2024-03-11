package com.sparta.spring4.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

public class Timestamped {
    @CreatedDate
    @Temporal(TemporalType.DATE)
    @Column(updatable = false)
    private LocalDate createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    @Column
    private LocalDate modifiedAt;
}
