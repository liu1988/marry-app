package com.marry.member.service;

import com.marry.common.domain.request.SysMemberDetailInfo;

public interface SysMemberDetailInfoService {

    public boolean save(SysMemberDetailInfo sysMemberDetailInfo);
    public SysMemberDetailInfo getSysMemberDetailInfoById(Integer id);
}
