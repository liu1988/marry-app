package com.marry.member.mapper;

import com.marry.common.domain.request.SysMemberCare;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMemberCareMapper {

    List<SysMemberCare> findSysMemberCareByPage(Integer userId);
}
