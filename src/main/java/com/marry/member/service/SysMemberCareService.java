package com.marry.member.service;

import com.github.pagehelper.PageInfo;
import com.marry.common.domain.request.SysMemberCare;

public interface SysMemberCareService {

    public boolean save(SysMemberCare sysMemberCare);
    public boolean delete(SysMemberCare sysMemberCare);

    PageInfo<SysMemberCare> getSysMemberCareByPage(Integer pageNum, Integer pageSize, Integer userId);
}
