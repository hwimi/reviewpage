package com.ezen.www.domain;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OptionsVO {
    private long opCode;
    private String name;
    private int price;
}
