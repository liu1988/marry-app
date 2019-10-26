package com.marry.member.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marry.common.domain.request.SysMemberCare;
import com.marry.member.mapper.SysMemberCareMapper;
import com.marry.member.repository.SysMemberCareRepository;
import com.marry.member.service.SysMemberCareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysMemberCareServiceImpl implements SysMemberCareService {
    @Autowired
    private SysMemberCareRepository sysMemberCareRepository;

    @Autowired
    private SysMemberCareMapper sysMemberCareMapper;

    @Transactional
    @Override
    public boolean save(SysMemberCare sysMemberCare) {
        try {
            SysMemberCare save = sysMemberCareRepository.save(sysMemberCare);
            if (save != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Transactional
    @Override
    public boolean delete(SysMemberCare sysMemberCare) {
        try {
            sysMemberCareRepository.delete(sysMemberCare);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PageInfo<SysMemberCare> getSysMemberCareByPage(Integer pageNum, Integer pageSize, Integer userId) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<SysMemberCare> sysMemberCareList = sysMemberCareMapper.findSysMemberCareByPage(userId);
            if(sysMemberCareList!=null){
                PageInfo<SysMemberCare> sysMemberCarePage = new PageInfo<SysMemberCare>(sysMemberCareList);
                return sysMemberCarePage;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
