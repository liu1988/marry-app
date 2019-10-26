package com.marry.common.domain.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@Entity
@Table(name = "sys_member_chat")
@ApiModel(value = "会员聊天对话实体", description = "这是会员聊天对话对象")
public class SysMemberChat {

    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 消息时间
     */
    private Date createTime;

    /**
     * 消息类型
     */
    private String type;

    /**
     * 消息内容
     */
    @Column(length = 500)
    private String content;

    /**
     * 消息发送人标识
     */
    private String sendUserId;

    /**
     * 群聊Id
     */
    private String groupId;

    /**
     * 是否在线-个人,0离线，1在线
     */
    private short online;

    /**
     * 是否在线-群聊
     */
    private String onlineGroup;

    /**
     * 消息接收人标识
     */
    private String receiveUserId;

    /**
     * 消息状态0未读，1已读
     */
    private Short status;

    /**
     * 对话窗口是否已打开 0关闭，1打开
     */
    @Transient
    private Short isOpen;
}
