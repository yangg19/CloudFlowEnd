package com.cnpc.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cnpc.server.pojo.Menu;
import com.cnpc.server.pojo.Role;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户id查询菜单列表
     *
     * @Params: [id]
     * @Return: java.util.List<com.cnpc.server.pojo.Menu>
     * @Author: yangg19
     * @UpdateTime: 2021/12/22 17:38
     * @Throws:
     */
    List<Menu> getMenuListByAdminId(Integer id);

    /**
     * 根据角色获取菜单列表
     *
     * @Params: []
     * @Return: java.util.List<com.cnpc.server.pojo.Menu>
     * @Author: yangg19
     * @UpdateTime: 2021/12/27 13:43
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
