package com.cnpc.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangg19
 * @since 2022-01-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Todolist对象", description="")
public class Todolist implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer userID;

    @ApiModelProperty(value = "待办内容")
    private String todoTask;

    @ApiModelProperty(value = "任务分数")
    private Integer taskScore;

    @ApiModelProperty(value = "延期次数")
    private Integer postCount;

    @ApiModelProperty(value = "待办状态")
    private String taskStatusID;

    @ApiModelProperty(value = "是否进行")
    private String tagColor;

    @ApiModelProperty(value = "待办详情")
    private String taskDetails;

    @ApiModelProperty(value = "计划时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDate planTime;
}
