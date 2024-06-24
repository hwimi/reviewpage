package com.ezen.www.service;

import com.ezen.www.domain.ReservationVO;

import java.util.List;

public interface ReservationService {
    int insert(ReservationVO reservation);

    ReservationVO getByReCode(long reCode);

    List<ReservationVO> getList();

    void update(ReservationVO reservation);

    void delete(long reCode);
}
