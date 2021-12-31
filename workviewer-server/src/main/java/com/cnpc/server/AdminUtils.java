package com.cnpc.server;

import com.cnpc.server.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 操作员工具类
 *
 * @Author: yangg19
 * @version: 1.0.0
 * @Date: 2021年12月31日 10:02:00
 */
public class AdminUtils {

    public static Admin getCurrentAdmin() {
        return (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
