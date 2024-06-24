package com.ezen.www.domain;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {
    private int cno;
    private int tno;
    private String writer;
    private String content;
    private String regDate;
    private String teacherName;
    private int score;

}
