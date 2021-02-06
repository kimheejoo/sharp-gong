package com.heeju.sharpGong.controller;

import com.heeju.sharpGong.domain.Member;
import com.heeju.sharpGong.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "/login";
    }
/*
    @PostMapping("/login")
    public String login(@Valid LoginForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/login";
        }
        Member member = memberService.Login(form);
        if (member!=null){
            model.addAttribute("member",member);
            return "redirect:/member/"+member.getMemberId()+"/todo";
        }
        else{
            return "/login";
        }
    }
 */
    /*
    @PostMapping("/login")
    public @ResponseBody Member login(@RequestBody LoginForm form){
        return memberService.Login(form);
    }
*/
    @PostMapping(value= "/login", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String login(@RequestBody LoginForm form){
        if(memberService.Login(form)!=null)
        {
            return "{status:200}";
        }
        else return "{status:401}";
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
    @PostMapping(value = "/member/register",produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String registerMember(@RequestBody MemberForm memberForm){
        Member member = new Member();
        member.setMemberId(memberForm.getMemberId());
        member.setMemberPassword(memberForm.getMemberPassword());
        if (memberForm.getMemberNickname()!=null){
            member.setMemberNickname(memberForm.getMemberNickname());
        }
        memberService.join(member);
        return "{status:200}";
    }

}
