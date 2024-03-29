package com.example.springdatajpa.controller;


import com.example.springdatajpa.dto.MemberDto;
import com.example.springdatajpa.entity.Member;
import com.example.springdatajpa.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Member member =memberRepository.findById(id).get();
        return member.getUsername();
    }


    @GetMapping("/members2/{id}")
    public String findMember(@PathVariable("id") Member member) {
        return member.getUsername();
    }


    @GetMapping("/members")
    public Page<MemberDto> list(Pageable pageable) {
        Page<Member> result = memberRepository.findAll(pageable);
        Page<MemberDto> map = result.map(member -> new MemberDto(member.getId(), member.getUsername(), null));
        return map;

    }

    //@PostConstruct
    public void init() {
        for(int i = 0; i < 100; i++) {
            memberRepository.save(new Member("user" + i));
        }
    }

}
