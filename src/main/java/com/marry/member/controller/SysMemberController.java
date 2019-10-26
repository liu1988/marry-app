package com.marry.member.controller;

import com.marry.common.domain.request.SysMember;
import com.marry.common.domain.response.Result;
import com.marry.common.domain.response.ResultCode;
import com.marry.common.utils.SendSmsUtils;
import com.marry.member.service.SysMemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/sys/member")
public class SysMemberController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SysMemberService sysMemberService;

    @GetMapping("/getCode")
    @ApiOperation(value = "获取手机验证码", response = String.class)
    @ApiParam(name = "phone", value = "手机号", required = true)
    public String getCode(@RequestParam("phone")String phone){
        //生成短信验证码
        String verifyCode = String
                .valueOf(new Random().nextInt(899999) + 100000);
        String sendMsg = SendSmsUtils.sendMsg(phone,verifyCode);
        //缓存验证码
        redisTemplate.boundValueOps("verifyCode_"+phone).set(verifyCode, 3000, TimeUnit.SECONDS);
        return sendMsg;
    }

    @PostMapping("login")
    @ApiOperation(value = "会员登录", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysMember", value = "会员实体", required = true),
            @ApiImplicitParam(name = "type", value = "登录类型,验证码登录1，账号密码登录2", required = true, paramType = "String")
    })
    public Result login(SysMember sysMember,@RequestParam("type")char type) {
        sysMember.setCreatTime(new Date());
        sysMember.setUserId(sysMember.getPhone());
        boolean bool=false;
        switch(type)
        {
            case '1' :
                bool = sysMemberService.register(sysMember);
                break;
            case '2' :
                bool = sysMemberService.login(sysMember);
                break;
            default :
                System.out.println("未知登录方式");
        }

        if (bool) {
            //储存用户手机号，作为登录标识
            String phone = sysMember.getPhone();
            redisTemplate.boundValueOps("login_"+sysMember.getPhone()).set(sysMember.getPhone(),24,TimeUnit.HOURS);
            return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), bool);
        } else {
            return new Result(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMessage(), bool);
        }
    }

    @GetMapping("/login/out")
    @ApiOperation(value = "退出系统登录", response = String.class)
    @ApiParam(name = "id", value = "会员id", required = true)
    public Result loginOut(@RequestParam("id")String id){
        Boolean bool = redisTemplate.delete("login_" + id);
        if(bool) {
            return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), bool);
        }else {
            return new Result(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMessage(), bool);
        }
    }

}
