package com.ezen.www.service;

import com.ezen.www.domain.ReservationVO;

import com.ezen.www.repository.ReservationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationMapper reservationMapper;

    @Override
    public int insert(ReservationVO reservation) {
        return reservationMapper.insert(reservation);
    }

    @Override
    public ReservationVO getByReCode(long reCode) {
        return reservationMapper.selectByReCode(reCode);
    }

    @Override
    public List<ReservationVO> getList() {
        return reservationMapper.getList();
    }

    @Override
    public void update(ReservationVO reservation) {
        reservationMapper.update(reservation);
    }

    @Override
    public void delete(long reCode) {
        reservationMapper.delete(reCode);
    }
}
