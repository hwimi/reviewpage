package com.ezen.www.controller;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.TeacherVO;
import com.ezen.www.service.ReviewPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/rev/*")
public class ReviewPageController {
    private final ReviewPageService rps;

    @GetMapping("/reviewPage")
    public String review(Model m, @RequestParam("tno") int tno, Principal principal) {
        TeacherVO tvo = rps.detail(tno);
        List<TeacherVO> teacherList = rps.getAllTeachers();
        String username = principal != null ? principal.getName() : "";
        m.addAttribute("tvo", tvo);
        m.addAttribute("teacherList", teacherList);
        m.addAttribute("username", username);
        log.info(">>tvo>>{}", tvo);
        return "/review/reviewPage";
    }

    @PostMapping("/post")
    @ResponseBody
    public String post(@RequestBody CommentVO cvo,Principal principal) {
        log.info("Received comment: {}", cvo);
        try {
            cvo.setWriter(principal.getName());
            int isOk = rps.post(cvo);
            return isOk > 0 ? "1" : "0";
        } catch (Exception e) {
            return "0";
        }
    }
    @GetMapping("/list")
    @ResponseBody
    public List<CommentVO> getComments(@RequestParam("tno") int tno, @RequestParam(value = "page", defaultValue = "1") int page) {
        List<CommentVO> comments = rps.getComments(tno);
        return comments;
    }
    @DeleteMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("cno") int cno,Principal principal) {
        try {
            CommentVO cvo = rps.getComment(cno);
            if (cvo.getWriter().equals(principal.getName())) {
                int isOk = rps.delete(cno);
                return isOk > 0 ? "1" : "0";
            } else {
                return "0"; // 권한이 없는 경우
            }
        } catch (Exception e) {
            return "0";
        }
    }
    @PutMapping("/modify")
    @ResponseBody
    public String modify(@RequestBody CommentVO cvo,Principal principal) {
        log.info("Modifying comment: {}", cvo);
        try {
            CommentVO existingComment = rps.getComment(cvo.getCno());
            if (existingComment.getWriter().equals(principal.getName())) {
                int isOk = rps.update(cvo);
                return isOk > 0 ? "1" : "0";
            } else {
                return "0"; // 권한이 없는 경우
            }
        } catch (Exception e) {
            return "0";
        }
    }
    @GetMapping("/commentCount")
    @ResponseBody
    public int getCommentCount(@RequestParam("tno") int tno) {
        return rps.getCommentCount(tno);
    }
    @GetMapping("/averageScore")
    @ResponseBody
    public double getAverageScore(@RequestParam("tno") int tno) {
        return rps.getAverageScore(tno);
    }
}
