package com.cnpc.server.controller;


import com.cnpc.server.pojo.Menu;
import com.cnpc.server.service.IAdminService;
import com.cnpc.server.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
@RestController
@RequestMapping("/system/cfg") // 菜单在数据库中已定义，需要做过滤拦截，因此需要修改路径
public class MenuController {
    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "通过用户id查询菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenuListByAdminId() { // 登录成功后，后端可以直接获取用户信息，用前端有被篡改的风险
        return menuService.getMenuListByAdminId();
    }


}
