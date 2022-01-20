package com.cnpc.server.controller;

import com.cnpc.server.pojo.Admin;
import com.cnpc.server.pojo.RespBean;
import com.cnpc.server.service.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  个人中心
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
@RestController
@RequestMapping("/admin")
public class AdminInfoController {

    @Autowired
    private IAdminService adminService;

//    @Autowired
//    private IRoleService roleService;
//
//    @ApiOperation(value = "获取所有操作员")
//    @GetMapping("/")
//    public List<Admin> getAllAdmin(String keywords) {
//        return adminService.getAllAdmins(keywords);
//    }

    @ApiOperation(value = "更新当前用户信息")
    @PutMapping("/info")
    public RespBean updateAdmin(@RequestBody Admin admin, Authentication authentication) {
        if (adminService.updateById(admin)) {
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(admin, null, authentication.getAuthorities()));
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "更新当前用户密码")
    @PutMapping("/password")
    public RespBean updatePassword(@RequestBody Map<String, Object> info) {
        String oldPass = (String) info.get("oldPass");
        String pass = (String) info.get("pass");
        Integer adminId = (Integer) info.get("adminId");
        return adminService.updateAdminPassword(oldPass, pass, adminId);

    }
}
