package com.marry.member.repository;

import com.marry.common.domain.request.SysMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRepository extends JpaRepository<SysMember,String> {

    public SysMember getSysMemberByPhoneAndPasswordAndPasswordIsNotNull(String username, String password);

    public SysMember getSysMemberByPhone(String phone);
}
