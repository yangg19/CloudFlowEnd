package com.cnpc.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cnpc.server.pojo.Menu;
import com.cnpc.server.pojo.Role;

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
    
    
    /**
     * 根据角色获取菜单列表
     *
     * @Params: []
     * @Return: java.util.List<com.cnpc.server.pojo.Menu>
     * @Author: yangg19
     * @UpdateTime: 2021/12/27 13:41 
     * @Throws: 
     */
    List<Menu> getMenuListWithRole();

    /**
     * 查询所有菜单
     *
     * @Params: []
     * @Return: java.util.List<com.cnpc.server.pojo.Role>
     * @Author: yangg19
     * @UpdateTime: 2021/12/29 13:20
     * @Throws:
     */
    List<Menu> getMenuList();
}
