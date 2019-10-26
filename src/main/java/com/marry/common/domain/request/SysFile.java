package com.marry.common.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="sys_file")
@ApiModel(value = "文件信息实体类",description = "文件信息描述对象")
public class SysFile {
    @Id
    //自增主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "文件主键,后台生成前端不用传",required = false)
    private int id;
    /**
     * 上传之后的名称
     */
    @ApiModelProperty(value = "文件上传之后的名称",required = false)
    private String fileName;

    @ApiModelProperty(value = "文件上传之后的url",required = false)
    private String fileUrl;
    /**
     * 文件原来的名称
     */
    @ApiModelProperty(value = "文件原来的真实名称",required = false)
    private String fileRealName;


    @ApiModelProperty(value = "文件类型",required = false)
    private String contentType;

    /**
     * 文件类型
     * 1 视频文件，2 其他文件
     */
    @ApiModelProperty(value = "文件类型，1 视频文件，2 图片文件",required = false)
    private String type;


    @ApiModelProperty(value = "文件上传人",required = false)
    private String userId;

    @ApiModelProperty(value = "是否是头像1是0否",required = false)
    private int isHead;

    @ApiModelProperty(value = "文件次序",required = false)
    private int num;

}
