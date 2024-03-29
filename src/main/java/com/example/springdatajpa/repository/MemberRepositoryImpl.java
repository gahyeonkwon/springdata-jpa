package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepsositoryCustom{

    private final EntityManager entityManager;
    @Override
    public List<Member> findMemberCustom() {
        return entityManager.createQuery("select m from Member m").getResultList();
    }
}
