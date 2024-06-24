package com.ezen.www.repository;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.TeacherVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewPageMapper {
    int post(CommentVO cvo);

    TeacherVO  detail(int tno);

    List<CommentVO> getComments(int tno);

    List<TeacherVO> getAllTeachers();

    int deleteComment(int cno);

    int update(CommentVO cvo);

    int delete(int cno);

    int getCommentCount(int tno);

    double getAverageScore(int tno);

    CommentVO getComment(int cno);
}
