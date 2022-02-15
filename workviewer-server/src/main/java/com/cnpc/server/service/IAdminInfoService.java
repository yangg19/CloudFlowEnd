package com.cnpc.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cnpc.server.pojo.*;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
public interface IAdminInfoService extends IService<AdminInfo> {

    /**
     * 获取所有员工信息（分页）
     *
     * @Params: [currentPage, size, admin, beginDateScope]
     * @Return: com.cnpc.server.pojo.RespPageBean
     * @Author: yangg19
     * @UpdateTime: 2022/2/8 10:33
     * @Throws:
     */
    RespPageBean getAdminByPage(Integer currentPage, Integer size, AdminInfo adminInfo);

    /**
     * 添加员工
     *
     * @Params: [admin]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/11 17:23
     * @Throws:
     */
    RespBean addAdminInfo(AdminInfo adminInfo);


    /**
     * 查询员工
     *
     * @Params: [id]
     * @Return: java.util.List<com.cnpc.server.pojo.AdminInfo>
     * @Author: yangg19
     * @UpdateTime: 2022/1/14 14:45
     * @Throws:
     */
    List<AdminInfo> getAdminInfo(Integer id);

    /**
     * 补充注册信息
     *
     * @Params: [adminInfo]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2022/2/9 18:25
     * @Throws:
     */
    RespBean RegisterAdminSubInfo(AdminInfo adminInfo);

    /**
     * 根据当前用户获取用户信息
     *
     * @Params: []
     * @Return: com.cnpc.server.pojo.RespPageBean
     * @Author: yangg19
     * @UpdateTime: 2022/2/10 15:51
     * @Throws:
     */
    AdminInfo getAdminInfoById();

    /**
     * 更新用户头像
     *
     * @Params: [fileName]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2022/2/15 9:30
     * @Throws:
     */
    RespBean updateAvatar(String fileName);
}
