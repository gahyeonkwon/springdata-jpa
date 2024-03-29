package com.example.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"}) // 여기서 team 적으면 연관관계 때문에 무한루프 탈 수 있으니 주의
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;
    private  String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this.username = username;
    }

    public Member(String member1, int i, Team teamA) {
        this.username = member1;
        this.age = i;

        if(teamA != null) {
            changeTeam(teamA);
        }
    }

    public Member(String memberA, int i) {
        this.username = memberA;
        this.age = i;

    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
