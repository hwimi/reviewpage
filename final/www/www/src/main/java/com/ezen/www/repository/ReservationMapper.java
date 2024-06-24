package com.ezen.www.repository;

import com.ezen.www.domain.ReservationVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    int insert(ReservationVO reservation);

    ReservationVO selectByReCode(long reCode);

    List<ReservationVO> getList();

    void update(ReservationVO reservation);

    void delete(long reCode);
}
