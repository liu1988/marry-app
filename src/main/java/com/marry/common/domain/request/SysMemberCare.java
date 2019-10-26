package com.marry.common.domain.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name="sys_member_care")
@ApiModel(value = "会员相互关注实体",description = "这是会员相互关注对象")
public class SysMemberCare {

    @Id
    //自增主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 关注
     */
    private String userId;

    /**
     * 被关注
     */
    private String careUserId;

}
