package com.cnpc.server.service.impl;

import com.cnpc.server.pojo.Admin;
import com.cnpc.server.pojo.Menu;
import com.cnpc.server.mapper.MenuMapper;
import com.cnpc.server.pojo.Role;
import com.cnpc.server.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    @Autowired
    private RedisTemplate redisTemplate;

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
        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        // 从redis获取菜单数据，如果为空去数据库获取
        List<Menu> menulist = (List<Menu>) valueOperations.get("menu_" + adminId);
        if (CollectionUtils.isEmpty(menulist)) {
           menulist = menuMapper.getMenuListByAdminId(adminId);
           // 将数据放入redis
           valueOperations.set("menu_" + adminId, menulist);
        }
        return menulist;
    }

    /**
     * 根据角色获取菜单列表
     *
     * @Params: []
     * @Return: java.util.List<com.cnpc.server.pojo.Menu>
     * @Author: yangg19
     * @UpdateTime: 2021/12/27 13:41
     * @Throws:
     */
    @Override
    public List<Menu> getMenuListWithRole() {
        return menuMapper.getMenuListWithRole();
    }

    /**
     * 查询所有菜单
     *
     * @Params: []
     * @Return: java.util.List<com.cnpc.server.pojo.Role>
     * @Author: yangg19
     * @UpdateTime: 2021/12/29 13:20
     * @Throws:
     */
    @Override
    public List<Menu> getMenuList() {
        return menuMapper.getMenuList();
    }
}
