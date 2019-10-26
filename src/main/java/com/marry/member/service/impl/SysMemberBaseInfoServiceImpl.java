package com.marry.member.service.impl;

import com.marry.common.domain.request.SysMemberBaseInfo;
import com.marry.member.repository.SysMemberBaseInfoRepository;
import com.marry.member.service.SysMemberBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SysMemberBaseInfoServiceImpl implements SysMemberBaseInfoService {

    @Autowired
    private SysMemberBaseInfoRepository sysMemberBaseInfoRepository;

    @Transactional
    @Override
    public boolean save(SysMemberBaseInfo sysMemberBaseInfo){
        try {
            SysMemberBaseInfo save = sysMemberBaseInfoRepository.save(sysMemberBaseInfo);
            if(save!=null){
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public SysMemberBaseInfo getSysMemberBaseInfoById(Integer id){
        try {
            Optional<SysMemberBaseInfo> memberBaseInfo = sysMemberBaseInfoRepository.findById(id);
            SysMemberBaseInfo sysMemberBaseInfo = memberBaseInfo.get();
            if(sysMemberBaseInfo!=null){
                return sysMemberBaseInfo;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
