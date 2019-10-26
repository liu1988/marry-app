package com.marry.member.service.impl;

import com.marry.common.domain.request.SysMemberDetailInfo;
import com.marry.member.repository.SysMemberDetailInfoRepository;
import com.marry.member.service.SysMemberDetailInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SysMemberDetailInfoServiceImpl implements SysMemberDetailInfoService {

    @Autowired
    private SysMemberDetailInfoRepository sysMemberDetailInfoRepository;
    @Override
    public boolean save(SysMemberDetailInfo sysMemberDetailInfo) {
        try {
            SysMemberDetailInfo save = sysMemberDetailInfoRepository.save(sysMemberDetailInfo);
            if(save!=null){
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public SysMemberDetailInfo getSysMemberDetailInfoById(Integer id) {
        try {
            Optional<SysMemberDetailInfo> memberDetailInfo = sysMemberDetailInfoRepository.findById(id);
            SysMemberDetailInfo sysMemberDetailInfo = memberDetailInfo.get();
            if(sysMemberDetailInfo!=null){
                return sysMemberDetailInfo;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
