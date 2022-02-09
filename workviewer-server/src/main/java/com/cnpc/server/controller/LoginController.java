package com.cnpc.server.controller;

import com.cnpc.server.pojo.Admin;
import com.cnpc.server.pojo.AdminInfo;
import com.cnpc.server.pojo.AdminLoginParam;
import com.cnpc.server.pojo.RespBean;
import com.cnpc.server.service.IAdminInfoService;
import com.cnpc.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cnpc.server.service.IAdminService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

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

    @Autowired
    private IAdminInfoService adminInfoService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request) {
//        return adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword(), adminLoginParam.getCode(),request);
        return adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword(), request);

    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/admin/info")
    // 登录之后，Spring Security当前登录对象已设置在全局，可以直接通过Principal对象获取当前登录对象
    public Admin getAdminInfo(Principal principal) {
        if (null == principal) {
            return null;
        }
        // 获取用户名
        String username = principal.getName();
        // 获取完整用户对象
        Admin admin = adminService.getAdminByUsername(username);
        // 密码不可以返回给前端，防止用户信息泄露
        admin.setPassword(null);
        // 返回用户角色信息
        admin.setRoles(adminService.getRoles(admin.getId()));
        return admin;
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    // 调用退出成功接口后，返回给前端状态码200，前端直接删除token，完成退出登录功能
    public RespBean loginout() {
//        return RespBean.success("注销成功！");
        return RespBean.success("");
    }

    @ApiOperation(value = "通过密码保护修改密码")
    @PutMapping("/findPass")
    public RespBean findPasswordByProtect(@RequestBody Map<String, Object> info) {
        String passAnswer = (String) info.get("passAnswer");
        String pass = (String) info.get("pass");
        Integer adminId = (Integer) info.get("adminId");
        return adminService.findPasswordByProtect(passAnswer, pass, adminId);
    }

    @ApiOperation(value = "注册用户")
    @PostMapping("/register")
    public RespBean RegisterAdmin(@RequestBody Admin admin) {
        return adminService.registerAdmin(admin);
    }

    @ApiOperation(value = "补充注册信息员工")
    @PutMapping("/registerSub")
    public RespBean RegisterAdminSubInfo(@RequestBody AdminInfo adminInfo) {
        System.out.println(adminInfo);
        System.out.println(adminInfo);
        System.out.println(adminInfo);
        System.out.println(adminInfo);

        return adminInfoService.RegisterAdminSubInfo(adminInfo);
    }
}
