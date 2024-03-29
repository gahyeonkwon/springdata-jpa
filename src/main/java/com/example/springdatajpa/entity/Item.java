package com.example.springdatajpa.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static org.springframework.data.jpa.domain.AbstractAuditable_.createdDate;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Item implements Persistable <String> {

    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdate;
    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return createdDate == null;
    }
}
