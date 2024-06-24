package com.ezen.www.service;

import com.ezen.www.domain.CommunityVO;
import com.ezen.www.domain.PagingVO;

import java.util.List;

public interface CommunityService {


    int register(CommunityVO cvo);


    List<CommunityVO> getQna(PagingVO pgvo);

    int getTotalCount(PagingVO pgvo);
}
