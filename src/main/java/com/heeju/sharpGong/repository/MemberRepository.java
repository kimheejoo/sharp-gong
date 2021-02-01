package com.heeju.sharpGong.repository;

import com.heeju.sharpGong.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member){
        if(member.getId() == null)
            em.persist(member);
        else
            em.merge(member);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public Member findById(String memberId){
        return em.createQuery("select m from Member m where m.memberId= :memberId", Member.class)
                .setParameter("memberId",memberId)
                .getSingleResult();
    }

}
