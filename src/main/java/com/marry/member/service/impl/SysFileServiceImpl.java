package com.marry.member.service.impl;

import com.marry.common.domain.request.SysFile;
import com.marry.member.repository.SysFileRepository;
import com.marry.member.service.SysFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SysFileServiceImpl implements SysFileService {

    @Value("${file.path}")
    private String filePath;

    @Autowired
    private SysFileRepository sysFileRepository;

    @Transactional
    @Override
    public SysFile save(SysFile sysFile) {
        try {
            SysFile save = sysFileRepository.save(sysFile);
            if(save!=null){
                return save;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    @Override
    public boolean deleteSysFileById(Integer id) {
        try {
            SysFile sysFile = this.getSysFileById(id);
            if(sysFile!=null){
                //先移除数据库数据
                sysFileRepository.deleteById(id);
                //删除文件
                String fileName = sysFile.getFileName();
                File file = new File(filePath+fileName);
                if(file.exists()){
                    file.delete();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public SysFile getSysFileById(Integer id) {
        try {
            Optional<SysFile> sysFileOptional = sysFileRepository.findById(id);
            SysFile sysFile = sysFileOptional.get();
            if(sysFile!=null){
                return sysFile;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SysFile> findSysFileByPageAndUserId(Map<String,Object> map) {
        return sysFileRepository.findAll();
    }
}
