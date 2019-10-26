package com.marry.common.domain.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * 会员详细信息资料
 */
@Data
@ToString
@Entity
@Table(name="sys_member_detail_info")
@ApiModel(value = "会员详细信息资料类",description = "这是会员详细信息资料对象")
public class SysMemberDetailInfo {

    @Id
    //自增主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userId;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 户籍
     */
    private String householdRegister;

    /**
     * 血型
     */
    private String bloodType;

    /**
     * 信仰
     */
    private String faith;

    /**
     * 毕业院校
     */
    private String university;

    /**
     * 公司类型
     */
    private String companyType;

    /**
     * 职业
     */
    private String occupation;

    /**
     * 是否抽烟
     */
    private String  isSmoking;

    /**
     * 是否有孩子
     */
    private String isChildren;

    /**
     * 是否想要孩子
     */
    private String isWantChildren;

    /**
     * 何时结婚
     */
    private String marriedTime;


}
