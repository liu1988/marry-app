package com.marry.member.service;

import com.marry.common.domain.request.SysMemberSpouseInfo;

public interface SysMemberSpouseInfoService {

    public boolean save(SysMemberSpouseInfo sysMemberSpouseInfo);
    public SysMemberSpouseInfo getSysMemberSpouseInfoById(Integer id);
}
