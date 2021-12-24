package com.cnpc.server.controller;

import com.cnpc.server.pojo.Admin;
import com.cnpc.server.pojo.AdminLoginParam;
import com.cnpc.server.pojo.RespBean;
import com.cnpc.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cnpc.server.service.IAdminService;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * TODO
 *
 * @Author: yangg19
 * @version: 1.0.0
 * @Date: 2021年12月20日 09:59:00
 */
@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request) {
        return adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword(), adminLoginParam.getCode(),request);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/admin/info")
    // 登录之后，Spring Security当前登录对象已设置在全局，可以直接通过Principal对象获取当前登录对象
    public Admin getAdminInfo(Principal principal) {
        if(null == principal) {
            return null;
        }
        // 获取用户名
        String username = principal.getName();
        // 获取完整用户对象
        Admin admin = adminService.getAdminByUsername(username);
        // 密码不可以返回给前端，防止用户信息泄露
        admin.setPassword(null);
        return admin;
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    // 调用退出成功接口后，返回给前端状态码200，前端直接删除token，完成退出登录功能
    public RespBean loginout() {
        return RespBean.sucess("注销成功！");
    }
}
