package com.ezen.www.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {

    private String id;
    private String pwd;
    private String name;
    private String tell;
    private String address;
    private String regAt;
    private String upAt;
    private String isDel;
    private String gender;
    private String birthY;
    private String birthM;
    private String birthD;

    private List<AuthVO> authList;

}
