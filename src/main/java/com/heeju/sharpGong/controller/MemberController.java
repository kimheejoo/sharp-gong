package com.heeju.sharpGong.controller;

import com.heeju.sharpGong.domain.Member;
import com.heeju.sharpGong.security.JwtTokenProvider;
import com.heeju.sharpGong.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "/login";
    }
    @PostMapping(value= "/login", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String login(@RequestBody LoginForm form){
        Member member = memberService.findByEmail(form.getMemberEmail())
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 계정입니다.(아이디)"));
        if (!passwordEncoder.matches(form.getMemberPassword(), member.getPassword())){
            throw new IllegalArgumentException("올바르지 않은 계정입니다.(비밀번호");
        }
        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
//        if(memberService.Login(form)!=null)
//        {
//            return "{status:200}";
//        }
//        else return "{status:401}";
    }
    @GetMapping("/member/{memberId}/todo")
    public String userHome(@PathVariable("memberId") String memberId, Model model){
        model.addAttribute("data",memberId);
        return "/home";
    }

    /*
    @GetMapping("/member/register")
    public String registerForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "/members/createMemberForm";
    }

     */
//    @PostMapping(value = "/member/register",produces = "application/json; charset=UTF-8")
//    @ResponseBody
//    public String registerMember(@RequestBody MemberForm memberForm){
//        Member member = new Member();
//        member.setMemberId(memberForm.getMemberId());
//        member.setMemberPassword(memberForm.getMemberPassword());
//        if (memberForm.getMemberNickname()!=null){
//            member.setMemberNickname(memberForm.getMemberNickname());
//        }
//        memberService.join(member);
//        return "{status:200}";
//    }
    @PostMapping(value = "/member/register", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String register(@RequestBody MemberForm memberForm){
        return "{member: "+memberService.join(Member.builder()
                .memberEmail(memberForm.getMemberEmail())
                .memberPassword(passwordEncoder.encode(memberForm.getMemberPassword()))
                .memberNickname(memberForm.getMemberNickname())
                .roles(Collections.singletonList("ROLE_USER"))
                .build())+"}";
    }

}
