package com.cnpc.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页公共返回对象
 *
 * @Author: yangg19
 * @version: 1.0.0
 * @Date: 2022年01月04日 10:30:00
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPageBean {
    /**
     * 总条数
     */
    private Long total;

    /**
     * 数据list
     */
    private List<?> data;
}
