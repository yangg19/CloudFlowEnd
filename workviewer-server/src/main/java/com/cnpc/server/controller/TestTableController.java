package com.cnpc.server.controller;


import com.cnpc.server.pojo.TestTable;
import com.cnpc.server.service.ITestTableService;
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
@RequestMapping("/test/table")
public class TestTableController {
    @Autowired
    private ITestTableService testTableService;

    @ApiOperation(value = "获取所有内容")
    @GetMapping("/")
    public List<TestTable> getAll() {
        return testTableService.getAll();
    }
}
