package com.cnpc.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cnpc.server.pojo.Admin;
import com.cnpc.server.pojo.Menu;
import com.cnpc.server.pojo.RespBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 登陆后返回Token
     *
     * @Params: [username, password, request]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2021/12/20 10:15
     * @Throws:
     */
    RespBean login(String username, String password, String code, HttpServletRequest request);

    /**
     * 根据用户名获取用户完整信息
     *
     * @Params: [username]
     * @Return: com.cnpc.server.pojo.Admin
     * @Author: yangg19
     * @UpdateTime: 2021/12/20 13:27
     * @Throws:
     */
    Admin getAdminByUsername(String username);

}
