package com.marry.member.controller;


import com.marry.common.domain.request.SysMemberBaseInfo;
import com.marry.common.domain.request.SysMemberDetailInfo;
import com.marry.common.domain.response.Result;
import com.marry.common.domain.response.ResultCode;
import com.marry.member.service.SysMemberDetailInfoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys/member/detail/info")
public class SysMemberDetailInfoController {
    @Autowired
    private SysMemberDetailInfoService sysMemberDetailInfoService;

    @PostMapping("/save")
    @ApiOperation(value = "保存会员的详细信息", response = Result.class)
    @ApiParam(name = "sysMemberDetailInfo", value = "会员基本详细实体", required = true)
    public Result save(SysMemberDetailInfo sysMemberDetailInfo){
        boolean bool = sysMemberDetailInfoService.save(sysMemberDetailInfo);
        if (bool) {
            return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), bool);
        } else {
            return new Result(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMessage(), bool);
        }
    }
    @GetMapping("/get/{id}")
    @ApiOperation(value = "获取会员的详细信息", response = Result.class)
    @ApiParam(name = "id", value = "会员详细信息id", required = true)
    public Result getById(@PathVariable("id") Integer id){
        SysMemberDetailInfo sysMemberDetailInfo = sysMemberDetailInfoService.getSysMemberDetailInfoById(id);
        if (sysMemberDetailInfo!=null) {
            return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), sysMemberDetailInfo);
        } else {
            return new Result(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMessage(), null);
        }
    }
}
