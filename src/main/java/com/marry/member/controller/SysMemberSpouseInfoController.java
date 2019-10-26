package com.marry.member.controller;

import com.marry.common.domain.request.SysMemberSpouseInfo;
import com.marry.common.domain.response.Result;
import com.marry.common.domain.response.ResultCode;
import com.marry.member.service.SysMemberSpouseInfoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys/member/spouse/info")
public class SysMemberSpouseInfoController {

    @Autowired
    private SysMemberSpouseInfoService sysMemberSpouseInfoService;

    @PostMapping("/save")
    @ApiOperation(value = "保存会员择偶信息", response = Result.class)
    @ApiParam(name = "sysMemberSpouseInfo", value = "会员择偶信息实体", required = true)
    public Result save(SysMemberSpouseInfo sysMemberSpouseInfo){
        boolean bool = sysMemberSpouseInfoService.save(sysMemberSpouseInfo);
        if (bool) {
            return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), bool);
        } else {
            return new Result(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMessage(), bool);
        }
    }
    @GetMapping("/get/{id}")
    @ApiOperation(value = "获取会员择偶信息", response = Result.class)
    @ApiParam(name = "id", value = "会员择偶信息id", required = true)
    public Result getById(@PathVariable("id") Integer id){
        SysMemberSpouseInfo sysMemberSpouseInfo = sysMemberSpouseInfoService.getSysMemberSpouseInfoById(id);
        if (sysMemberSpouseInfo!=null) {
            return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), sysMemberSpouseInfo);
        } else {
            return new Result(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMessage(), null);
        }
    }

}
