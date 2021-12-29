package com.cnpc.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cnpc.server.pojo.MenuRole;
import com.cnpc.server.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * 更新角色菜单
     *
     * @Params: [rid 角色id, mids 菜单id列表]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2021/12/29 13:39
     * @Throws:
     */
    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
