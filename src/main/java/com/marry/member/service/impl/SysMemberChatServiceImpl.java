package com.marry.member.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marry.common.domain.request.SysMemberCare;
import com.marry.common.domain.request.SysMemberChat;
import com.marry.member.mapper.SysMemberChatMapper;
import com.marry.member.repository.SysMemberChatRepository;
import com.marry.member.service.SysMemberChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SysMemberChatServiceImpl implements SysMemberChatService {
    @Autowired
    private SysMemberChatRepository sysMemberChatRepository;
    @Autowired
    private SysMemberChatMapper sysMemberChatMapper;

    @Async
    @Transactional
    @Override
    public boolean save(JSONObject message) {
        try {
            Short online = message.getShort("online");
            Short status = message.getShort("status");
            String receiveUserId = message.getString("receiveUserId");
            String sendUserId = message.getString("sendUserId");
            String msg = message.getString("msg");
            Long id = message.getLong("id");
            SysMemberChat sysMemberChat = new SysMemberChat();
            sysMemberChat.setId(id);
            sysMemberChat.setContent(msg);
            sysMemberChat.setOnline(online);
            sysMemberChat.setStatus(status);
            sysMemberChat.setReceiveUserId(receiveUserId);
            sysMemberChat.setSendUserId(sendUserId);
            sysMemberChat.setCreateTime(new Date());
            SysMemberChat save = sysMemberChatRepository.save(sysMemberChat);
            if (save != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PageInfo<SysMemberChat> getSysMemberChatByPage(Integer pageNum, Integer pageSize, String sendUserId, String receiveUserId) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            Map<String, String> map = new HashMap<>();
            map.put("send_user_id", sendUserId);
            map.put("receive_user_id", receiveUserId);
            List<SysMemberChat> sysMemberChatList = sysMemberChatMapper.findSysMemberChatByPage(map);
            if (sysMemberChatList != null) {
                PageInfo<SysMemberChat> sysMemberChatPage = new PageInfo<SysMemberChat>(sysMemberChatList);
                return sysMemberChatPage;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        try {
            sysMemberChatRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Transactional
    @Override
    public List<Long> update(String ids, String sendUserId) {
        try {
            List<Long> array = JSONArray.parseArray(ids, Long.class);
            List<Long> chats = new ArrayList<>();
            List<SysMemberChat> sysMemberChats = sysMemberChatRepository.findAllById(array);
            if(!CollectionUtils.isEmpty(sysMemberChats)){
                sysMemberChats.forEach(sysMemberChat -> {
                    //更新为已读状态
                    short status = 1;
                    sysMemberChat.setStatus(status);
                    sysMemberChat.setSendUserId(sendUserId);
                    chats.add(sysMemberChat.getId());
                });
            }
            sysMemberChatRepository.saveAll(sysMemberChats);
            //可能有的消息沒有來得及保存，需要告知客戶端，下次過來再次更新
            List<Long> collect = array.stream().filter(chat ->
                    !chats.contains(chat)).collect(Collectors.toList());
            if (collect.size() > 0) {
                return collect;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SysMemberChat> getAllSysMemberChat(String receiveUserId) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("receive_user_id", receiveUserId);
            List<SysMemberChat> sysMemberChatList = sysMemberChatMapper.findSysMemberChatByReceiveUserIdAndStatus(map);
            if (sysMemberChatList != null) {
                return sysMemberChatList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
