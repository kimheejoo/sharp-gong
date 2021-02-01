package com.heeju.sharpGong.controller;

import com.heeju.sharpGong.domain.Member;
import com.heeju.sharpGong.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.Binding;
import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "/login";
    }

    @PostMapping("/login")
    public String login(@PathVariable("memberId") Long id, @Valid LoginForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "/login";
        }
        if (memberService.Login(form)){
            return "/members/{memberId}/todo";
        }
        else{
            return "/login";
        }
    }

    @GetMapping("/members/register")
    public String registerForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "/members/createMemberForm";
    }
    @PostMapping("/members/register")
    public String registerMember(@Valid MemberForm form, BindingResult result){
        if(result.hasErrors()){
            return "/members/register";
        }
        Member member = new Member();
        member.setMemberId(form.getMemberId());
        member.setMemberPassword(form.getMemberPassword());
        if(form.getNickname() != null){
            member.setNickname(form.getNickname());
        }
        memberService.join(member);
        return "redirect:/";
    }

}
