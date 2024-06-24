package com.ezen.www.service;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.TeacherVO;
import com.ezen.www.repository.ReviewPageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewPageServiceImpl implements ReviewPageService{
    private final ReviewPageMapper reviewPageMapper;

    @Override
    public int post(CommentVO cvo) {
        return reviewPageMapper.post(cvo);
    }

    @Override
    public TeacherVO  detail(int tno) {
        return reviewPageMapper.detail(tno);
    }

    @Override
    public List<CommentVO> getComments(int tno) {
        return reviewPageMapper.getComments(tno);
    }

    @Override
    public List<TeacherVO> getAllTeachers() {
        return reviewPageMapper.getAllTeachers();
    }


    @Override
    public int update(CommentVO cvo) {
        return reviewPageMapper.update(cvo);
    }

    @Override
    public int delete(int cno) {
        return reviewPageMapper.delete(cno);
    }
    @Override
    public int getCommentCount(int tno) {
        return reviewPageMapper.getCommentCount(tno);
    }

    @Override
    public double getAverageScore(int tno) {
        return reviewPageMapper.getAverageScore(tno);
    }

    @Override
    public CommentVO getComment(int cno) {
        return reviewPageMapper.getComment(cno);
    }

}
