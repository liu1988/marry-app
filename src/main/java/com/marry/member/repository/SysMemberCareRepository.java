package com.marry.member.repository;

import com.marry.common.domain.request.SysMemberCare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysMemberCareRepository extends JpaRepository<SysMemberCare,Integer> {

}
