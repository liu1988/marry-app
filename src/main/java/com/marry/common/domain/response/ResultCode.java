package com.marry.common.domain.response;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS(101,"成功"),
    FAILURE(102,"失败"),
    USER_NEED_AUTHORITIES(201,"用户未登录"),
    USER_LOGIN_FAILED(202,"用户账号或密码错误"),
    USER_ACCOUNT_NOT_EXISTS(203,"账号不存在"),
    USER_LOGIN_SUCCESS(204,"用户登录成功"),
    USER_NO_ACCESS(205,"用户没有访问权限"),
    USER_LOGOUT_SUCCESS(206,"用户登出成功"),
    TOKEN_IS_BLACKLIST(207,"此token为黑名单"),
    LOGIN_IS_OVERDUE(208,"登录已失效"),
    AUTH_LOGIN_TOKEN_SAVE_FAIL(209,"存储令牌失败！"),
    UNAUTHENTICATED(210,"此操作需要登陆系统！"),
    FILE_HANDLE_FAILURE(211,"文件处理失败！"),
    Video_HANDLE_FAILURE(212,"视频格式不支持！"),
    Video_NO_HANDLE(213,"视频格式是MP4,不需要转换！");

    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @deprecation:通过code返回枚举
     */
    public static ResultCode parse(int code){
        ResultCode[] values = values();
        for (ResultCode value : values) {
            if(value.getCode() == code){
                return value;
            }
        }
        throw  new RuntimeException("Unknown code of ResultEnum");
    }


}
