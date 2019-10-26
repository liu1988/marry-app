package com.marry.member.controller;

import com.github.pagehelper.PageInfo;
import com.marry.common.domain.request.SysMemberChat;
import com.marry.common.domain.response.Result;
import com.marry.common.domain.response.ResultCode;
import com.marry.member.service.SysMemberChatService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/member/chat")
public class SysMemberChatController {
    @Autowired
    private SysMemberChatService sysMemberChatService;

    @GetMapping("/delete")
    @ApiOperation(value = "删除会话信息", response = Result.class)
    @ApiParam(name = "id", value = "会话信息id", required = true)
    public Result delete(@RequestParam("id") Long id){
        boolean bool = sysMemberChatService.delete(id);
        if (bool) {
            return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), true);
        } else {
            return new Result(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMessage(), false);
        }
    }

    @GetMapping("/get")
    @ApiOperation(value = "获取会话信息", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, paramType = "String"),
            @ApiImplicitParam(name = "sendUserId", value = "发送人id", required = true),
            @ApiImplicitParam(name = "receiveUserId", value = "接收人id", required = true)
    })
    public Result getMyChat(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
                            @RequestParam(value = "sendUserId")String sendUserId, @RequestParam(value = "receiveUserId")String receiveUserId){
        PageInfo<SysMemberChat> sysMemberChatPageInfo = sysMemberChatService.getSysMemberChatByPage(pageNum, pageSize, sendUserId,receiveUserId);
        if (sysMemberChatPageInfo!=null) {
            return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), sysMemberChatPageInfo);
        } else {
            return new Result(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMessage(), null);
        }
    }

    @GetMapping("/update")
    @ApiOperation(value = "更新会话信息状态,说明：消息可能存在延迟，因此更新成功返回为null,更新失败说明还有消息没存储到数据库，需要再次更新，后台已经返回了为更新完毕的id集合",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "会话信息id数组", required = true),
            @ApiImplicitParam(name = "sendUserId", value = "发送人的id", required = true, paramType = "String")
    })
    public Result updateChatStatus(@RequestParam(value = "ids")String ids,@RequestParam(value = "sendUserId")String sendUserId){
        List<Long> list = sysMemberChatService.update(ids,sendUserId);
        if (CollectionUtils.isEmpty(list)) {
            return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
        }else {
            return new Result(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMessage(), list);
        }
    }

    @GetMapping("/get/all/chat")
    @ApiOperation(value = "登录之后获取会话所有未读信息", response = Result.class)
    @ApiParam(name = "receiveUserId", value = "自己的id，即接收者id", required = true)
    public Result getAllChat(@RequestParam(value = "receiveUserId")String receiveUserId){
        List<SysMemberChat> sysMemberChats = sysMemberChatService.getAllSysMemberChat(receiveUserId);
        if (sysMemberChats!=null) {
            return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), sysMemberChats);
        } else {
            return new Result(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMessage(), null);
        }
    }


}
