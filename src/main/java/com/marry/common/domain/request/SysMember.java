package com.marry.common.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@Entity
@Table(name="sys_member")
@ApiModel(value = "会员实体类",description = "这是会员信息对象")
public class SysMember {
	@Id
	@Column(length = 32)
	@ApiModelProperty(value = "会员主键,后台生成前端不用传",required = false)
	private String userId;

	private String username;

	private String password;

	private String phone;

	/**
	 * 个人独白
	 */
	@Column(length = 255)
	private String personalSoliloquy;

	/**
	 * 自我描述
	 */
	private String selfDescription;


	/**
	 * 用户是否认证
	 */
	private boolean isValid;

	/**
	 * 浏览量
	 */
	private long  pageView;

	/**
	 * 魅力值
	 */
	private int charmValue;

	/**
	 * 靠谱度
	 */
	private int reliability;

	/**
	 * 是否是会员
	 */

	private boolean isMember;

	/**
	 * 会员等级
	 */
	private int memberLevel;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
	private Date creatTime;

	/**
	 * 验证码临时字段
	 */
	@Transient
	private String verifyCode;

	@ApiModelProperty(value = "是否上传头像1是0否",required = false)
	private int isHead;


}
