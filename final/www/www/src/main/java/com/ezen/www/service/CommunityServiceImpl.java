package com.ezen.www.service;


import com.ezen.www.domain.CommunityVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.repository.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {
    private final BoardMapper boardMapper;


    @Override
    public int register(CommunityVO cvo) {
        return boardMapper.register(cvo);
    }

    @Override
    public List<CommunityVO> getQna(PagingVO pgvo) {
        return boardMapper.getQna(pgvo);
    }

    @Override
    public int getTotalCount(PagingVO pgvo) {
        return boardMapper.getTotalCount(pgvo);
    }


}
