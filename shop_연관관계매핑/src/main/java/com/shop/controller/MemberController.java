package com.shop.controller;

import com.shop.dto.MemberFormDto;
import com.shop.entity.Member;
import com.shop.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

    //'new' 경로로 들어오는 HTTP POST 요청을 처리합니다.
    @PostMapping(value = "/new")
    public String memberForm(@Valid MemberFormDto memberFormDto, //회원가입 폼에서 전달된 데이터를 검증(유효성 검사)
                             BindingResult bindingResult, Model model) {//뷰에 데이터를 전달 하기 위한 모덱 객체

        // 폼 데이터에 오류가 있는지 확인합니다.
        if (bindingResult.hasErrors()){
            return "member/memberForm";
        }
        try{
            // 폼 데이터를 기반으로 새로운 회원 객체를 생성합니다.
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMembr(member);
        }catch (IllegalStateException e) {

            // 회원 가입 중 예외가 발생하면 에러 메시지를 모델에 추가합니다.
            model.addAttribute("errorMessage", e.getMessage());

            // 에러 메시지와 함께 회원 가입 폼 페이지로 다시 이동합니다.
            return "member/memberForm";
        }
    // 회원 가입이 성공적으로 완료되면 홈 페이지로 리다이렉트합니다.
        return "redirect:/";
    }

    // 로그인 페이지 불러오기
    @GetMapping(value = "/login")
    public String loginMemeber() {
        return "/member/memberLoginForm";
    }

    // 에러 페이지
    @GetMapping(value = "/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "/member/memberLoginForm";
        }

        //로그아웃
    @GetMapping("/logout")
    public String performLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }
}
