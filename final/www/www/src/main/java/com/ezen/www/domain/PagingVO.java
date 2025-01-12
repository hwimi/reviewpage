package com.ezen.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class PagingVO {
    private int pageNo;
    private int qty;
    private String type;
    private String Keyword;

    public PagingVO(){
        this.pageNo=1;
        this.qty=10;
    }
    public PagingVO(int pageNo,int qty,String type,String keyword){
    this.pageNo=pageNo;
    this.qty=qty;
    this.type=type;
    this.Keyword=keyword;
    }
    public int getPageStart(){
        return (this.pageNo-1)*qty;
    }
    public String[] getTypeToArray(){
        return this.type==null? new String[] {}:this.type.split("");
    }
}
