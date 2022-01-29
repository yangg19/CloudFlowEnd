package com.cnpc.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cnpc.server.pojo.Admin;
import com.cnpc.server.pojo.Menu;
import com.cnpc.server.pojo.RespBean;
import com.cnpc.server.pojo.Role;
import org.springframework.security.core.Authentication;

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
//    RespBean login(String username, String password, String code, HttpServletRequest request);
    RespBean login(String username, String password, HttpServletRequest request);


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


    /**
     * 根据用户id查询角色列表
     *
     * @Params: [adminId]
     * @Return: java.util.List<com.cnpc.server.pojo.Role>
     * @Author: yangg19
     * @UpdateTime: 2021/12/27 15:16
     * @Throws:
     */
    List<Role> getRoles(Integer adminId);

    /**
     * 获取所有操作员
     *
     * @Params: [keywords]
     * @Return: java.util.List<com.cnpc.server.pojo.Admin>
     * @Author: yangg19
     * @UpdateTime: 2021/12/31 9:56
     * @Throws:
     */
    List<Admin> getAllAdmins(String keywords);

    /**
     * 更新操作员角色
     *
     * @Params: [adminId, rids]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2021/12/31 10:58
     * @Throws:
     */
    RespBean updateAdminRole(Integer adminId, Integer[] rids);

    /**
     * 更新用户密码
     *
     * @Params: [oldPass, pass, adminId]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/19 16:21
     * @Throws:
     */
    RespBean updateAdminPassword(String oldPass, String pass, Integer adminId);

    /**
     * 获取所有用户
     *
     * @Params: [keywords]
     * @Return: java.util.List<com.cnpc.server.pojo.Admin>
     * @Author: yangg19
     * @UpdateTime: 2022/1/27 11:20
     * @Throws:
     */
    List<Admin> getAllAdmin(String keywords);

    /**
     * 更新用户头像
     * @param url
     * @param id
     * @param authentication
     * @return
     */
    RespBean updateAdminUserFace(String url, Integer id, Authentication authentication);
}
