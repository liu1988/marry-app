package com.marry.member.repository;

import com.marry.common.domain.request.SysMemberDetailInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysMemberDetailInfoRepository extends JpaRepository<SysMemberDetailInfo,Integer> {
}
