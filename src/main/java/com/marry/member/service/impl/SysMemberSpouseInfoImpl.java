package com.marry.member.service.impl;

import com.marry.common.domain.request.SysMemberSpouseInfo;
import com.marry.member.repository.SysMemberSpouseInfoRepository;
import com.marry.member.service.SysMemberSpouseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class SysMemberSpouseInfoImpl implements SysMemberSpouseInfoService {

    @Autowired
    private SysMemberSpouseInfoRepository sysMemberSpouseInfoRepository;

    @Override
    public boolean save(SysMemberSpouseInfo sysMemberSpouseInfo) {
        try {
            SysMemberSpouseInfo save = sysMemberSpouseInfoRepository.save(sysMemberSpouseInfo);
            if(save!=null){
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public SysMemberSpouseInfo getSysMemberSpouseInfoById(Integer id) {
        try {
            Optional<SysMemberSpouseInfo> memberSpouseInfo = sysMemberSpouseInfoRepository.findById(id);
            SysMemberSpouseInfo sysMemberSpouseInfo = memberSpouseInfo.get();
            if(sysMemberSpouseInfo!=null){
                return sysMemberSpouseInfo;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
