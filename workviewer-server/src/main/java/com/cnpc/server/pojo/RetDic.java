package com.cnpc.server.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * TODO
 *
 * @Author: yangg19
 * @version: 1.0.0
 * @Date: 2022年01月13日 10:47:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("RET_DIC")
@ApiModel(value="字典对象", description="")
public class RetDic {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "字典类型")
    @Excel(name="字典类型", width = 30)
    private String dicType;

    @ApiModelProperty(value = "字典类型名")
    @Excel(name="字典类型名", width = 30)
    private String dicTypeName;

    @ApiModelProperty(value = "字典值")
    @Excel(name="字典值", width = 30)
    private String dicValue;

    @ApiModelProperty(value = "字典值名")
    @Excel(name="字典值中文", width = 30)
    private String dicValueName;
}
