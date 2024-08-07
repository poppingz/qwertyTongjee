package com.jsw.qwerty.repo;

import com.jsw.qwerty.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<String, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setUserSeq(++sequence);
        store.put(member.getUserId(), member);
        store.put(member.getUserName(),member);
        store.put(member.getPassword(),member);
        System.out.println("=====================================메모리 멤버 리포");
        System.out.println(member);
        return member;
    }

    @Override
    public Optional<Member> findByUserId(String userId) {
        return Optional.ofNullable(store.get(userId));
    }

    @Override
    public Optional<Member> findByUserName(String userName) {
        return store.values().stream()
                .filter(member -> member.getUserName().equals(userName))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
