package com.cnpc.server.controller;


import com.cnpc.server.pojo.Billboard;
import com.cnpc.server.pojo.RespBean;
import com.cnpc.server.pojo.Role;
import com.cnpc.server.service.IBillboardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangg19
 * @since 2022-02-06
 */
@RestController
@RequestMapping("/billboard")
public class BillboardController {

    @Autowired
    private IBillboardService billboardService;

    @ApiOperation(value = "获取公告信息")
    @GetMapping("/")
    public Billboard getAllRoles() {
        return billboardService.list().get(0);
    }

    @ApiOperation(value = "更新公告信息")
    @PutMapping("/")
    public RespBean findPasswordByProtect(@RequestBody Billboard billboard) {
        if (billboardService.updateById(billboard)) {
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }
}
