package com.cnpc.server.controller;


import com.cnpc.server.pojo.Joblevel;
import com.cnpc.server.pojo.RespBean;
import com.cnpc.server.service.IJoblevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
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
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {
    
    @Autowired
    private IJoblevelService joblevelService;
    
    @ApiOperation(value = "获取所有职称信息")
    @GetMapping("/")
    public List<Joblevel> getAllJobLevels() {
        return joblevelService.list();
    }

    @ApiOperation(value = "添加职称信息")
    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody Joblevel joblevel) {
        joblevel.setCreateDate(LocalDateTime.now());
        if (joblevelService.save(joblevel)) {
            return RespBean.sucess("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新职称信息")
    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody Joblevel joblevel) {
        if (joblevelService.updateById(joblevel)) {
            return RespBean.sucess("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "删除职称信息")
    @DeleteMapping("/{id}")
    public RespBean deleteJobLevel(@PathVariable Integer id) {
        if (joblevelService.removeById(id)) {
            return RespBean.sucess("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation(value = "批量删除职称信息")
    @DeleteMapping("/")
    public RespBean deleteJobLevelByIds(Integer[] ids) {
        if (joblevelService.removeByIds(Arrays.asList(ids))) {
            return RespBean.sucess("删除成功！");
        }
        return RespBean.error("删除失败！");
    }
}
