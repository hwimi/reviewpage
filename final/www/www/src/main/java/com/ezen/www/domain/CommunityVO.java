package com.ezen.www.domain;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommunityVO {
    private int no;
    private String title;
    private String writer;
    private String regDate;

}
