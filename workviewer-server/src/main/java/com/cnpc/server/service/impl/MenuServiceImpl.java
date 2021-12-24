package com.cnpc.server.service.impl;

import com.cnpc.server.pojo.Admin;
import com.cnpc.server.pojo.Menu;
import com.cnpc.server.mapper.MenuMapper;
import com.cnpc.server.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 根据用户id查询菜单列表
     *
     * @Params: []
     * @Return: java.util.List<com.cnpc.server.pojo.Menu>
     * @Author: yangg19
     * @UpdateTime: 2021/12/22 17:36
     * @Throws:
     */
    @Override
    public List<Menu> getMenuListByAdminId() {
        return menuMapper.getMenuListByAdminId(((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }
}
