package com.marry.member.service;

import com.marry.common.domain.request.SysMember;

public interface SysMemberService {

    public boolean login(SysMember sysMember);

    public boolean register(SysMember sysMember);

}
