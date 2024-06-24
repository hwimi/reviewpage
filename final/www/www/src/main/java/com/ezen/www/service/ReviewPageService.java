package com.ezen.www.service;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.TeacherVO;

import java.util.List;

public interface ReviewPageService {
    int post(CommentVO cvo);

    TeacherVO  detail(int tno);

    List<CommentVO> getComments(int tno);

    List<TeacherVO> getAllTeachers();

    int update(CommentVO cvo);

    int delete(int cno);

    int getCommentCount(int tno);

    double getAverageScore(int tno);

    CommentVO getComment(int cno);
}
