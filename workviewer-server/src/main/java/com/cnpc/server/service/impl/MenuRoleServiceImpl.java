package com.cnpc.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.cnpc.server.pojo.Menu;
import com.cnpc.server.pojo.MenuRole;
import com.cnpc.server.mapper.MenuRoleMapper;
import com.cnpc.server.pojo.RespBean;
import com.cnpc.server.service.IMenuRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    /**
     * 更新角色菜单
     *
     * @Params: [rid 角色id, mids 菜单id列表]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2021/12/29 13:39
     * @Throws:
     */
    @Override
    @Transactional
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid", rid));
        if (null == mids || 0 == mids.length) {
            return RespBean.sucess("更新成功！");
        }
        Integer result = menuRoleMapper.insertRecord(rid, mids);
        if (result == mids.length) {
            return RespBean.sucess("更新成功！");
        }
        return RespBean.error("更新失败！");
    }
}
