package com.timber.timberspring.service;

// 서비스 패키지는 회원 repository와 domain을 활용해서 비즈니스 로직을 작성하는 쪽

import com.timber.timberspring.domain.Member;
import com.timber.timberspring.repository.MemberRepository;
import com.timber.timberspring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /***
     * 회원 가입.
     */
    public Long join(Member member)
    {
        // 같은 이름의 회원은 등록이 안됨.
        // Optional 으로 감싸면 Optional 안에 member가 있는 느낌으로 Optional의 기능을 사용할 수 있다.
        // Optional<Member> result = memberRepository.findByName(member.getName());
        // Member member1 = result.get();

        /*memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/

        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    public List<Member> findMembers()
    {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId)
    {
        return memberRepository.findById(memberId);
    }

}
