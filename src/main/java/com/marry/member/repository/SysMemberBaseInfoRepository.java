package com.marry.member.repository;

import com.marry.common.domain.request.SysMemberBaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysMemberBaseInfoRepository extends JpaRepository<SysMemberBaseInfo,Integer> {

}
