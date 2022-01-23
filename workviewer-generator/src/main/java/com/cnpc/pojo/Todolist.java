package com.cnpc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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

    @ApiModelProperty(value = "计划时间")
    private LocalDateTime planTime;


}
