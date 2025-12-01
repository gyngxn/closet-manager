package com.example.closet_manager.controller;

import com.example.closet_manager.domain.item.Clothes;
import com.example.closet_manager.domain.item.Member;
import com.example.closet_manager.service.ClothesService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor

public class ClothesController {
    private final ClothesService clothesService;

    //1. 메인 목록 페이지
    @GetMapping("/clothes")
    public String list(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");

        if(loginMember == null) {
            return "redirect:/login";
        }

        List<Clothes> clothes = clothesService.findMyClothes(loginMember);
        model.addAttribute("clothesList", clothes);
        return "clothes/list";
    }

    //2. 등록 페이지 이동
    @GetMapping("/clothes/new")
    public String createForm(Model model) {
        model.addAttribute("form", new Clothes());
        return "clothes/createForm";
    }

    //3. 옷 등록 처리
    @PostMapping("/clothes/new")
    public String create(Clothes clothes, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");

        if(loginMember == null) {
            return "redirect:/login";
        }

        clothesService.saveClothes(clothes, loginMember);
        return "redirect:/clothes";
    }

    //4. 옷 수정 페이지 이동
    @GetMapping("/clothes/{id}/edit")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        Clothes clothes = clothesService.findOne(id);
        model.addAttribute("form", clothes);
        return "clothes/updateForm";
    }

    //5. 옷 수정 처리
    @PostMapping("/clothes/{id}/edit")
    public String updateClothes(@PathVariable("id") Long id, @ModelAttribute("form") Clothes form) {
        clothesService.updateClothes(id,
                form.getName(),
                form.getCategory(),
                form.getBrand(),
                form.getColor(),
                form.getImageUrl());

        return "redirect:/clothes";
    }

    //6. 옷 삭제 처리
    @GetMapping("/clothes/{id}/delete")
    public String deleteClothes(@PathVariable("id") Long id) {
        clothesService.deleteClothes(id);

        return "redirect:/clothes";
    }
}
