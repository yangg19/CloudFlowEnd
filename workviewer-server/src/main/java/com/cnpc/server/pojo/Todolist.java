package com.cnpc.server.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

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
@TableName("todolist")
@ApiModel(value="Todolist对象", description="")
public class Todolist implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer userID;

    @ApiModelProperty(value = "用户")
    @ExcelEntity(name = "用户")
    @TableField(exist = false)
    private AdminName adminName;

    @ApiModelProperty(value = "待办内容")
    @Excel(name = "待办内容", width = 50)
    private String todoTask;

    @ApiModelProperty(value = "待办详情")
    @Excel(name = "待办详情", width = 50)
    private String taskDetails;

    @ApiModelProperty(value = "计划时间")
    @Excel(name = "计划时间", width = 20)
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDate planTime;

    @ApiModelProperty(value = "待办状态")
    @Excel(name = "待办状态")
    private String taskStatusID;

    @ApiModelProperty(value = "任务分数")
    @Excel(name = "任务分数")
    private Integer taskScore;

    @ApiModelProperty(value = "延期次数")
    @Excel(name = "延期次数")
    private Integer postCount;

}
