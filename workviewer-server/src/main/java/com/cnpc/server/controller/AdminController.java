package com.cnpc.server.controller;


import com.cnpc.server.pojo.*;
import com.cnpc.server.service.IAdminService;
import com.cnpc.server.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  操作员管理
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
@RestController
@RequestMapping("/system/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "获取所有操作员")
    @GetMapping("/")
    public List<Admin> getAllAdmin(String keywords) {
        return adminService.getAllAdmins(keywords);
    }

    @ApiOperation(value = "根据ID获取操作员名字")
    @GetMapping("/getName/{id}")
    public String getAllAdmin(@PathVariable Integer id) {
        return adminService.getAdminName(id);
    }

    @ApiOperation(value = "更新操作员")
    @PutMapping("/")
    public RespBean updateAdmin(@RequestBody Admin admin) {
        if (adminService.updateById(admin)) {
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "删除操作员")
    @DeleteMapping("/{id}")
    public RespBean deleteAdmin(@PathVariable Integer id) {
        if (adminService.removeById(id)) {
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.list();
    }

    @ApiOperation(value = "更新操作员角色")
    @PutMapping("/role")
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        return adminService.updateAdminRole(adminId, rids);
    }

    @ApiOperation(value = "更新密码")
    @PutMapping("/password")
    public RespBean updateAdminPassword(@RequestBody Map<String, Object> info) {
        String oldPass = (String) info.get("oldPass");
        String pass = (String) info.get("pass");
        Integer adminId = (Integer) info.get("adminId");
        return adminService.updateAdminPassword(oldPass, pass, adminId);
    }

    @ApiOperation(value = "设置密码保护")
    @PutMapping("/passPro")
    public RespBean updatePasswordProtect(@RequestBody Map<String, Object> info) {
        String passQuestion = (String) info.get("passQuestion");
        String passAnswer = (String) info.get("passAnswer");
        Integer adminId = (Integer) info.get("adminId");
        return adminService.updatePasswordProtect(passQuestion, passAnswer, adminId);
    }
}
