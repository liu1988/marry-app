package com.marry.common.domain.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * 会员基本信息资料
 */
@Data
@ToString
@Entity
@Table(name="sys_member_base_info")
@ApiModel(value = "会员基本信息资料类",description = "这是会员基本信息资料对象")
public class SysMemberBaseInfo {

    @Id
    //自增主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userId;

    /**
     * 性别
     */
    private String gender;

    /**
     * 年龄
     */
    private String age;

    /**
     * 身高
     */
    private int height;

    /**
     * 体重
     */
    private int weight;

    /**
     * 学历
     */
    private String education;

    /**
     * 婚姻状况
     */
    private String maritalStatus;

    /**
     * 所在地
     */
    private String location;

    /**
     * 月薪
     */
    private String salary;

    /**
     * 住房情况
     */
    private String  house;

    /**
     * 购车情况 已购车，未购车，购
     */
    private String car;


}
