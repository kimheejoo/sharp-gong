package com.heeju.sharpGong.repository;

import com.heeju.sharpGong.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public Long save(Member member){
        if(member.getId() == null)
            em.persist(member);
        else
            em.merge(member);
        return member.getId();
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }


    public Optional<Member> findByEmail(String userEmail){
        List<Member> member = em.createQuery("select m from Member m where m.memberEmail= :memberEmail", Member.class)
                .setParameter("memberEmail", userEmail)
                .getResultList();
        return member.stream().findAny();
    }

}
