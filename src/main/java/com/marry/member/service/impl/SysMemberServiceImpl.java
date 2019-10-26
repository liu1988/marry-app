package com.marry.member.service.impl;

import com.marry.common.domain.request.SysMember;
import com.marry.member.repository.SysUserRepository;
import com.marry.member.service.SysMemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class SysMemberServiceImpl implements SysMemberService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SysUserRepository sysUserRepository;

    /**
     * 密码账号登录
     * @param sysMember
     * @return
     */
    @Transactional
    @Override
    public boolean login(SysMember sysMember) {
        try {
            SysMember member = sysUserRepository.getSysMemberByPhoneAndPasswordAndPasswordIsNotNull(sysMember.getPhone(), sysMember.getPassword());
            if (member != null)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 验证码登录
     * @param sysMember
     * @return
     */
    @Transactional
    @Override
    public boolean register(SysMember sysMember) {
        try {
            String phone = sysMember.getPhone();
            SysMember member = sysUserRepository.getSysMemberByPhone(phone);
            //第一次进来，注册登录
            if (!StringUtils.isEmpty(phone)) {
                if (member == null) {
                    //获取到验证码
                    String verifyCode = sysMember.getVerifyCode();
                    String code = redisTemplate.opsForValue().get("verifyCode_" + phone).toString();
                    if(StringUtils.isNotEmpty(verifyCode)&&StringUtils.isNotEmpty(code)&&verifyCode.equals(code)){
                        SysMember save = sysUserRepository.save(sysMember);
                        if (save != null) {
                            return true;
                        }
                    }

                } else {
                   //后面用短信登录
                    String verifyCode = sysMember.getVerifyCode();
                    String code = redisTemplate.opsForValue().get("verifyCode_" + phone).toString();
                    if(StringUtils.isNotEmpty(verifyCode)&&StringUtils.isNotEmpty(code)&&verifyCode.equals(code)){
                        return true;
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
