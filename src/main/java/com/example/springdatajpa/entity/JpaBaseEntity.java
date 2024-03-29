package com.example.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@MappedSuperclass
@Getter
public class JpaBaseEntity {

    @Column(updatable = false)
    private LocalDateTime createDated;
    private LocalDateTime updateDated;


    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createDated = now;
        updateDated = now;
    }


    @PreUpdate
    public void preUpdate() {
        updateDated = LocalDateTime.now();
    }
}
