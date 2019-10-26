package com.marry.common.domain.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一响应实体
 */
@Data
@NoArgsConstructor
public class Result implements Serializable {

    public Result(Integer status, String message, Object data, String jwtToken) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.jwtToken = jwtToken;
    }

    public Result(Integer status, String message, Object data){
        this.status = status;
        this.message = message;
        this.data = data;
    }
    /**
     * 响应状态
     */
    private Integer status;
    /**
     * 响应提示信息
     */
    private String message;

    /**
     * 响应实体数据
     */
    private Object  data;

    /**
     * 响应的jwt
     */
    private String jwtToken;



}
