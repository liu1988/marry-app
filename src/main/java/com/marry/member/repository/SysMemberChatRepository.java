package com.marry.member.repository;

import com.marry.common.domain.request.SysMemberChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysMemberChatRepository  extends JpaRepository<SysMemberChat,Long> {
}
