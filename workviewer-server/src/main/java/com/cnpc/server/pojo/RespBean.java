package com.cnpc.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 公共返回对象
 * @ClassName: RespBean.java
 * @Author: yangg19
 * @version: 1.0.0
 * @Date: 2021年12月17日 14:51:00
 */

// lombok注解
@Data
// 无参构造
@NoArgsConstructor
// 全参构造
@AllArgsConstructor
public class RespBean {
    private long code; // 状态码
    private String message; // 提示信息
    private Object obj; // 对象

    /**
     * @Description: 成功返回结果
     * @Param: message
     * @Author: yangg19
     * @UpdateTime: 2021/12/17 14:55
     * @Return: com.cnpc.server.pojo.RespBean
     * @Throws:
     */
    public static RespBean sucess(String message) {
        return new RespBean(200, message, null);
    }

    /**
     * @Description: 成功返回结果
     * @Param: message
     * @Param: obj
     * @Author: yangg19
     * @UpdateTime: 2021/12/17 14:56
     * @Return: com.cnpc.server.pojo.RespBean
     * @Throws:
     */
    public static RespBean sucess(String message, Object obj) {
        return new RespBean(200, message, obj);
    }


    /**
     * @Description: 失败返回结果
     * @Param: message
     * @Author: yangg19
     * @UpdateTime: 2021/12/17 15:01
     * @Return: com.cnpc.server.pojo.RespBean
     * @Throws:
     */
    public static RespBean error(String message) {
        return new RespBean(500, message,null);
    }

    /**
     * @Description: 失败返回结果
     * @Param: message
     * @Param: obj
     * @Author: yangg19
     * @UpdateTime: 2021/12/17 15:01
     * @Return: com.cnpc.server.pojo.RespBean
     * @Throws:
     */
    public static RespBean error(String message, Object obj) {
        return new RespBean(500, message,obj);
    }

}
