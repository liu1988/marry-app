package com.marry.member.service;

import com.marry.common.domain.request.SysMemberBaseInfo;

public interface SysMemberBaseInfoService {

    public boolean save(SysMemberBaseInfo sysMemberBaseInfo);

    public SysMemberBaseInfo getSysMemberBaseInfoById(Integer id);
}
