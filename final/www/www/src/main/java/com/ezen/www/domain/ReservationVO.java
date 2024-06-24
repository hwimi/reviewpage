package com.ezen.www.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReservationVO {
    private long reCode;
    private String name;
    private LocalDateTime date;
    private int duration;
    private int price;
    private String status;
    private LocalDateTime regAt;
    private String memId;
    private long meCode;
    private long opCode;
}
