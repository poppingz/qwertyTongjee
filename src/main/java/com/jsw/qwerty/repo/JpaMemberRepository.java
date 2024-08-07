package com.jsw.qwerty.repo;

import com.jsw.qwerty.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        System.out.println("=====================================JPA 메모리 멤버 리포");
        System.out.println(member);
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findByUserId(String userId) {
        System.out.println("=====================================JPA 메모리 멤버 findById");
        Member member = em.find(Member.class, userId);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByUserName(String userName) {
        List<Member> result = em.createQuery("select m from Member m where m.userName = :userName", Member.class)
                .setParameter("userName", userName)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m",Member.class).getResultList();
    }
}
