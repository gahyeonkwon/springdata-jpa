package com.example.springdatajpa.entity;

import com.example.springdatajpa.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;
    @Test
    public void testEntity() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("team");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamA);
        Member member4 = new Member("member4", 40, teamA);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush(); // em.persist 한다고해서 db insert 를 하지 않아서 강제로 데이터를 넣어주는 작업이 필요함.
        em.clear();

        //확인
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

        for(Member member : members) {
            System.out.println("member =>" + member);
            System.out.println("member.team =>" + member.getTeam());
        }


    }

    @Test
    public void JpaEventBaseEntity() throws InterruptedException {
        Member member = new Member("member1");
        memberRepository.save(member); // @PrePersist;

        Thread.sleep(100);
        member.setUsername("member2");

        em.flush(); // @Pre Persist
        em.clear();

        //when
        Member findMember = memberRepository.findById(member.getId()).get();

        //then
        System.out.println("findMember.getLastModifiedDated = >" + findMember.getLastModifiedDated());
        System.out.println("findMember.getCreatedDated = >" + findMember.getCreatedDated());
        System.out.println("findMember.createdDate = >" + findMember.getCreatedBy());
        System.out.println("findMember.getLastModifiedDated = >" + findMember.getLastModifiedDated());
    }

}