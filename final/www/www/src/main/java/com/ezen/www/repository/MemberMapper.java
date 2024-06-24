package com.ezen.www.repository;

import com.ezen.www.domain.AuthVO;
import com.ezen.www.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    int register(MemberVO mvo);

    int registerAuth(String id);

    MemberVO selectId(String username);

    List<AuthVO> selectAuths(String username);

    MemberVO checkSignId(String id);

    MemberVO getMemberId(String id);

    void modify(MemberVO mvo);

    void pwdModify(MemberVO mvo);

    void resign(String name);
}
