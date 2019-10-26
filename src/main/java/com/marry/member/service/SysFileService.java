package com.marry.member.service;

import com.marry.common.domain.request.SysFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SysFileService {
    /**
     * 创建文件信息
     * @param sysFile
     * @return
     */
    SysFile save(SysFile sysFile);


    /**
     * 删除文件信息
     * @param id
     */
    boolean deleteSysFileById(Integer id);


    /**
     * 获取文件详细信息
     * @param id
     * @return
     */
    SysFile getSysFileById(Integer id);


    /**
     * 分页查询文件信息
     * @param map
     * @return
     */
    List<SysFile> findSysFileByPageAndUserId(Map<String,Object> map);


}
