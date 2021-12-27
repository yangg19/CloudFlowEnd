package com.cnpc.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface RoleMapper extends BaseMapper<Role> {
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
}
