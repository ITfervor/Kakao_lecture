package com.kakao.security.controller;

import com.kakao.security.dto.ClubMemberJoinDTO;
import com.kakao.security.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/login")
    //error는 로그인 실패했을 때의 파라미터
    //logout은 로그아웃 한 후 로그인으로 이동했을 때 의 파라미터
    public void login(String error, String logout){
        if(logout !=null){
            log.info("로그아웃");

        }
    }
    private final MemberService memberService;
    //회원가입 페이지로 이동
    @GetMapping("/join")
    public void join(){
        log.info("회원 가입 페이지로 이동");
    }

    //회원 가입 처리
    @PostMapping("/join")
    public String join(ClubMemberJoinDTO memberJoinDTO,
                       RedirectAttributes rattr){ //RedirectAttributes rattr는 끝나고 데이터를 가져가고 싶을 때 사용
            log.info(memberJoinDTO);
            try{
                memberService.join(memberJoinDTO);
            }catch (Exception e){
                rattr.addFlashAttribute("error" ,"mid");
                rattr.addFlashAttribute("dto" ,memberJoinDTO);
                return "redirect:/member/join"; // 아이디가 error 발생시에 리다이렉트 되는 페이지
            }
            rattr.addFlashAttribute("result", "success"); // result 값이 success

            return "redirect:/member/login"; //결과가 성공값일때 리다이렉트 되는 페이지

    }
}
