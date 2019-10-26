package com.marry.member.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marry.common.domain.request.SysMemberCare;
import com.marry.common.domain.response.Result;
import com.marry.common.domain.response.ResultCode;
import com.marry.member.service.SysMemberCareService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys/member/care")
public class SysMemberCareController {

    @Autowired
    private SysMemberCareService sysMemberCareService;

    @PostMapping("/add")
    @ApiOperation(value = "保存会员的关注信息", response = Result.class)
    @ApiParam(name = "SysMemberCare", value = "会员关注信息实体", required = true)
    public Result save(SysMemberCare sysMemberCare){
        boolean bool = sysMemberCareService.save(sysMemberCare);
        if (bool) {
            return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), true);
        } else {
            return new Result(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMessage(), false);
        }
    }

    @GetMapping("/delete")
    @ApiOperation(value = "会员取消关注信息", response = Result.class)
    @ApiParam(name = "SysMemberCare", value = "会员关注信息实体", required = true)
    public Result delete(SysMemberCare sysMemberCare){
        boolean bool = sysMemberCareService.delete(sysMemberCare);
        if (bool) {
            return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), true);
        } else {
            return new Result(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMessage(), false);
        }
    }

    @GetMapping("/get")
    @ApiOperation(value = "我关注的会员", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码",required=true,paramType ="Integer" ),
            @ApiImplicitParam(name = "pageSize", value = "页大小",required=true,paramType="Integer"),
            @ApiImplicitParam(name = "userId", value = "会员id",required=true,paramType="Integer")
    })
    public Result getMyCare(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
                            @RequestParam(value = "userId")Integer userId){
        PageInfo<SysMemberCare> sysMemberCarePageInfo = sysMemberCareService.getSysMemberCareByPage(pageNum, pageSize, userId);
        if (sysMemberCarePageInfo!=null) {
            return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), sysMemberCarePageInfo);
        } else {
            return new Result(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMessage(), null);
        }
    }
}
