package com.marry.common.domain.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * 会员择偶条件
 */
@Data
@ToString
@Entity
@Table(name="sys_member_spouse_info")
@ApiModel(value = "会员择偶条件信息类",description = "这是会员择偶条件信息对象")
public class SysMemberSpouseInfo {

    @Id
    //自增主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userId;

    /**
     * 籍贯
     */
    private String ageRange;

    /**
     * 身高
     */
    private String heightRange;

    /**
     * 所在地
     */
    private String location;

    /**
     * 学历
     */
    private String education;

    /**
     * 婚姻状况
     */
    private String maritalStatus;




}
