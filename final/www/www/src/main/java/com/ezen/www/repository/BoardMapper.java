package com.ezen.www.repository;

import com.ezen.www.domain.CommunityVO;
import com.ezen.www.domain.PagingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    int register(CommunityVO cvo);


    List<CommunityVO> getQna(PagingVO pgvo);

    int getTotalCount(PagingVO pgvo);
}
