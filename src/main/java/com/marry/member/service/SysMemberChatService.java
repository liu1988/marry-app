package com.marry.member.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.marry.common.domain.request.SysMemberChat;

import java.util.List;

public interface SysMemberChatService {
    public boolean save(JSONObject message);
    PageInfo<SysMemberChat> getSysMemberChatByPage(Integer pageNum, Integer pageSize, String sendUserId,String receiveUserId);
    public boolean delete(Long id);
    public List<Long> update(String ids,String sendUserId);
    List<SysMemberChat> getAllSysMemberChat(String receiveUserId);
}
