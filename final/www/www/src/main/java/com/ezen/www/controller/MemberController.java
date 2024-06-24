package com.ezen.www.controller;

import com.ezen.www.domain.MemberVO;
import com.ezen.www.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member/*")
public class MemberController {

    private final MemberService msv;
    private final PasswordEncoder passwordEncoder;


    // 가입 전 정책 동의
    @GetMapping("/registerpolicy")
    public void registerpolicy(){}

    @GetMapping("/register")
    public void register(){}


    // 회원가입
    @PostMapping("/register")
    public String register(MemberVO mvo){
        MemberVO mvo2 = msv.getMemberId(mvo.getId());
        if(mvo2 != null){
            return "redirect:/member/register";
        }

        mvo.setPwd(passwordEncoder.encode(mvo.getPwd()));
        int isOk = msv.register(mvo);
        return "member/login";
    }

    // 아이디 중복확인
    @GetMapping(value = "/checkSignId/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> checkSignId(@PathVariable("id") String id){
        MemberVO mvo = msv.checkSignId(id);
        return mvo == null ? new ResponseEntity<String>("1", HttpStatus.OK)
                : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/login")
    public void login(){}


    // 주문 조회
    @GetMapping("/myPage_reserve")
    public void myPage_reserve(){}


    // 회원정보수정
    @GetMapping("/myPage_info")
    public void myPage_info(){}

    @PostMapping("/myPage_info")
    public String myPage_info(MemberVO mvo){
        if(mvo.getPwd()==null || mvo.getPwd().equals("") || mvo.getPwd().length()==0){
            msv.modify(mvo);
        }else{
            mvo.setPwd(passwordEncoder.encode(mvo.getPwd()));
            msv.pwdModify(mvo);
        }
        return "redirect:/member/logout";
    }


    // 장바구니
    @GetMapping("/myPage_basket")
    public void myPage_basket(){}

    // 관리자페이지
    @GetMapping("/admin_page")
    public void admin_page(){}

    // 1:1문의
    @GetMapping("/myPage_ask")
    public void myPage_ask(){}

    // 회원탈퇴
    @GetMapping("/myPage_resign")
    public String myPage_resign(Principal principal){
        msv.resign(principal.getName());
        return "redirect:/member/logout?id="+principal.getName();
    }





}