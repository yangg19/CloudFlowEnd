package com.cnpc.server.controller;


import com.cnpc.server.pojo.*;
import com.cnpc.server.service.ITodolistService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangg19
 * @since 2022-01-23
 */
@RestController
@RequestMapping("/todolist")
public class TodolistController {

    @Autowired
    private ITodolistService todolistService;

    @ApiOperation(value = "查询待办")
    @GetMapping("/")
    public RespPageBean getTodolistInfo(@RequestParam(defaultValue = "1") Integer currentPage,
                                   @RequestParam(defaultValue = "10") Integer size,
                                   Todolist todolist,
                                   LocalDate[] beginDateScope) {
        return todolistService.getTodolistByPage(currentPage, size, todolist, beginDateScope);
    }

    @ApiOperation(value = "添加待办")
    @PostMapping("/")
    public RespBean addTodolist(@RequestBody Todolist todolist) {
        return todolistService.addTodolist(todolist);
    }

    @ApiOperation(value = "删除待办")
    @DeleteMapping("/{id}")
    public RespBean deleteTodolist(@PathVariable Integer id) {
        if (todolistService.removeById(id)) {
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }


}
