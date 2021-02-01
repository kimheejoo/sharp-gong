package com.heeju.sharpGong.service;

import com.heeju.sharpGong.controller.LoginForm;
import com.heeju.sharpGong.domain.Member;
import com.heeju.sharpGong.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원가입
    @Transactional
    public void join(Member member){
        memberRepository.save(member);
    }

    //로그인
    public Boolean Login(@Valid LoginForm login){
        Member member = memberRepository.findById(login.getMemberId());
        if (member.getMemberPassword().equals(login.getMemberPassword())){
            return true;
        }
        return false;
    }

}
