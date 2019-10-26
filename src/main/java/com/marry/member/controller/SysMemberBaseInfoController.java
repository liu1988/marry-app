package com.marry.member.controller;

import com.marry.common.domain.request.SysMemberBaseInfo;
import com.marry.common.domain.response.Result;
import com.marry.common.domain.response.ResultCode;
import com.marry.member.service.SysMemberBaseInfoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys/member/base/info")
public class SysMemberBaseInfoController {
    @Autowired
    private SysMemberBaseInfoService sysMemberBaseInfoService;

    @PostMapping("/save")
    @ApiOperation(value = "保存会员的基本信息", response = Result.class)
    @ApiParam(name = "SysMemberBaseInfo", value = "会员基本信息实体", required = true)
    public Result save(SysMemberBaseInfo sysMemberBaseInfo){
        boolean bool = sysMemberBaseInfoService.save(sysMemberBaseInfo);
        if (bool) {
            return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), bool);
        } else {
            return new Result(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMessage(), bool);
        }
    }
    @GetMapping("/get/{id}")
    @ApiOperation(value = "获取会员的基本信息", response = Result.class)
    @ApiParam(name = "id", value = "会员基本信息id", required = true)
    public Result getById(@PathVariable("id") Integer id){
        SysMemberBaseInfo sysMemberBaseInfo = sysMemberBaseInfoService.getSysMemberBaseInfoById(id);
        if (sysMemberBaseInfo!=null) {
            return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), sysMemberBaseInfo);
        } else {
            return new Result(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMessage(), null);
        }
    }
}
