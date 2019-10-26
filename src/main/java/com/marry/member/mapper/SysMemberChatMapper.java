package com.marry.member.mapper;

import com.marry.common.domain.request.SysMemberChat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface SysMemberChatMapper {
    List<SysMemberChat> findSysMemberChatByPage(Map<String,String> map);
    public List<SysMemberChat> findSysMemberChatByReceiveUserIdAndStatus(Map<String,Object> map);
}
