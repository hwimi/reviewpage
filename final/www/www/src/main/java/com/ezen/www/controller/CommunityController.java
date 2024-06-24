package com.ezen.www.controller;

import com.ezen.www.domain.CommunityVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.PagingHandler;
import com.ezen.www.service.CommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/comm/*")
@RequiredArgsConstructor
@Slf4j
public class CommunityController {
    private final CommunityService cts;

    @GetMapping("/register")
    public String register(){

        return "/community/register";
    }
    @PostMapping("/register")
    public String register(CommunityVO cvo){
       log.info(">>cvo>>{}",cvo);
        log.info(">>test>>");
        int isOk=cts.register(cvo);
        return "redirect:/comm/qna";
    }

    @GetMapping("/announcement")
    public String announcement() {

        return "/community/announcement";
    }
    @GetMapping("/qna")
    public String qna(Model m, PagingVO pgvo){
        log.info("test");
        log.info("pgvo>>{}",pgvo);
        int totalCount=cts.getTotalCount(pgvo);
        PagingHandler ph=new PagingHandler(pgvo,totalCount);
        List<CommunityVO> qna=cts.getQna(pgvo);
        m.addAttribute("qna",qna);
        m.addAttribute("ph",ph);
        return "/community/qna";
    }
    @GetMapping("/event")
    public String event() {
        return "/community/event";
    }



}

