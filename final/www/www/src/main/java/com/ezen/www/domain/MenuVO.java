package com.ezen.www.domain;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MenuVO {
    private long meCode;
    private String name;
    private String size;
    private int price;
}
