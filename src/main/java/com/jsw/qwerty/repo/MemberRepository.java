package com.jsw.qwerty.repo;

import com.jsw.qwerty.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByUserId(String userId);
    Optional<Member> findByUserName(String userName);
    List<Member> findAll();
}
