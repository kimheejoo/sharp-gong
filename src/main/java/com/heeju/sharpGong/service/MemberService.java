package com.heeju.sharpGong.service;

import com.heeju.sharpGong.controller.LoginForm;
import com.heeju.sharpGong.domain.Member;
import com.heeju.sharpGong.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    @Transactional
    public Long join(Member member){
        return memberRepository.save(member);
    }

    //로그인
    public Member Login(@Valid LoginForm login){
        Member member = memberRepository.findByEmail(login.getMemberEmail()).get();
        if (member.getMemberPassword().equals(login.getMemberPassword())){
            return member;
        }
        return null;
    }
    public List<Member>  findAll(){
        return memberRepository.findAll();
    }

    public Member authenticateByEmailAndPassword(String email, String password){
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        if(!passwordEncoder.matches(password, member.getPassword())){
            throw new BadCredentialsException("Password not matched");
        }
        return member;
    }
    public Optional<Member> findByEmail(String memberEmail){
        return memberRepository.findByEmail(memberEmail);
    }

}
