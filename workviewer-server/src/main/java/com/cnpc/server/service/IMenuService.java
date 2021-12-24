package com.cnpc.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cnpc.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
public interface IMenuService extends IService<Menu> {

    /**
     *  根据用户id查询菜单列表
     *
     * @Params: []
     * @Return: java.util.List<com.cnpc.server.pojo.Menu>
     * @Author: yangg19
     * @UpdateTime: 2021/12/22 17:35
     * @Throws:
     */
    List<Menu> getMenuListByAdminId();
}
