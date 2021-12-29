package com.cnpc.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cnpc.server.pojo.MenuRole;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    /**
     * 更新角色菜单
     *
     * @Params: [rid, mids]
     * @Return: java.lang.Integer
     * @Author: yangg19
     * @UpdateTime: 2021/12/29 13:53
     * @Throws:
     */
    Integer insertRecord(Integer rid, Integer[] mids);
}
