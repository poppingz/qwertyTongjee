package com.jsw.qwerty.service;

import com.jsw.qwerty.domain.Member;
import com.jsw.qwerty.repo.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /** 회원가입 **/
    public long join(Member member) {
        System.out.println("=====================================멤버 서비스");
        System.out.println(member);
        //중복제거
        duplicateMember(member);

        memberRepository.save(member);
        return member.getUserSeq();
    }

    /** 전체 회원조회 **/
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /** 아이디 조회 **/
    public Optional<Member> findOne(String userId) {
        return memberRepository.findByUserId(userId);
    }

    private void duplicateMember(Member member) {
        memberRepository.findByUserId(member.getUserId())
                .ifPresent(m ->  {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
