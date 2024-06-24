package com.ezen.www.repository;

import com.ezen.www.domain.MenuVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<MenuVO> getMenuList();
}
