package com.ezen.www.service;

import com.ezen.www.domain.MemberVO;
import com.ezen.www.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;

    @Override
    public int register(MemberVO mvo) {
        int isOk = memberMapper.register(mvo);
        return isOk > 0 ? memberMapper.registerAuth(mvo.getId()) : 0;
    }

    @Override
    public MemberVO checkSignId(String id) {
        return memberMapper.checkSignId(id);
    }

    @Override
    public MemberVO getMemberId(String id) {
        return memberMapper.getMemberId(id);
    }

    @Override
    public void modify(MemberVO mvo) {
        memberMapper.modify(mvo);
    }

    @Override
    public void pwdModify(MemberVO mvo) {
        memberMapper.pwdModify(mvo);
    }

    @Override
    public void resign(String name) {
        memberMapper.resign(name);
    }

}
