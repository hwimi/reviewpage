package com.ezen.www.service;

import com.ezen.www.domain.MemberVO;

public interface MemberService {

    int register(MemberVO mvo);

    MemberVO checkSignId(String id);

    MemberVO getMemberId(String id);

    void modify(MemberVO mvo);

    void pwdModify(MemberVO mvo);

    void resign(String name);
}
