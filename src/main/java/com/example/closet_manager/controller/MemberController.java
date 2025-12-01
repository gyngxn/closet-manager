package com.example.closet_manager.controller;

import com.example.closet_manager.domain.item.Member;
import com.example.closet_manager.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members") // 공통 주소
public class MemberController {

    private final MemberService memberService;

    //1. 회원가입 페이지 보여주기
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new Member());
        return "members/createMemberForm";
    }

    //2. 회원가입 처리
    @PostMapping("/new")
    public String create(@ModelAttribute Member member) {
        memberService.join(member);
        return "redirect:/";
    }

    //3. 마이페이지 이동
    @GetMapping("/my")
    public String myPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");

        if(loginMember == null) {
            return "redirect:/";
        }

        model.addAttribute("member", loginMember);
        return "members/myPage";
    }
}
