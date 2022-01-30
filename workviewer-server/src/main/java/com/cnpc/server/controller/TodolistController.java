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

    @ApiOperation(value = "查询新建/进行中/逾期待办")
    @GetMapping("/")
    public RespPageBean getInitTodolistInfo(@RequestParam(defaultValue = "1") Integer currentPage,
                                   @RequestParam(defaultValue = "10") Integer size,
                                   LocalDate[] beginDateScope) {
        return todolistService.getInitTodolistByPage(currentPage, size, beginDateScope);
    }

    @ApiOperation(value = "查询所有人新建/进行中/逾期待办")
    @GetMapping("/all/init")
    public RespPageBean getAllInitTodolistInfo(@RequestParam(defaultValue = "1") Integer currentPage,
                                            @RequestParam(defaultValue = "10") Integer size,
                                            LocalDate[] beginDateScope) {
        return todolistService.getAllInitTodolistByPage(currentPage, size, beginDateScope);
    }

    @ApiOperation(value = "查询所有人完成或删除待办")
    @GetMapping("/all/query")
    public RespPageBean getAllTodolistInfo(@RequestParam(defaultValue = "1") Integer currentPage,
                                        @RequestParam(defaultValue = "10") Integer size,
                                        @RequestParam String taskStatusID,
                                        LocalDate[] beginDateScope) {
        return todolistService.getAllTodolistByPage(currentPage, size, taskStatusID, beginDateScope);
    }

    @ApiOperation(value = "查询完成或删除待办")
    @GetMapping("/query")
    public RespPageBean getTodolistInfo(@RequestParam(defaultValue = "1") Integer currentPage,
                                        @RequestParam(defaultValue = "10") Integer size,
                                        @RequestParam String taskStatusID,
                                        LocalDate[] beginDateScope) {
        return todolistService.getTodolistByPage(currentPage, size, taskStatusID, beginDateScope);
    }

    @ApiOperation(value = "添加待办")
    @PostMapping("/")
    public RespBean addTodolist(@RequestBody Todolist todolist) {
        return todolistService.addTodolist(todolist);
    }

    @ApiOperation(value = "修改待办")
    @PutMapping("/")
    public RespBean updateTodolist(@RequestBody Todolist todolist) {
        if (todolistService.updateById(todolist)) {
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "完成待办")
    @PutMapping("/com")
    public RespBean comTaskById(@RequestParam Integer id) {
        return todolistService.comTaskById(id);
    }

    @ApiOperation(value = "重用待办")
    @PutMapping("/redo")
    public RespBean redoTaskById(@RequestParam Integer id) {
        return todolistService.redoTaskById(id);
    }

    @ApiOperation(value = "删除待办（真实）")
    @DeleteMapping("/{id}")
    public RespBean deleteTodolist(@PathVariable Integer id) {
        if (todolistService.removeById(id)) {
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation(value = "删除待办（假）")
    @PutMapping("/del")
    public RespBean deleteTaskById(@RequestParam Integer id) {
        return todolistService.deleteTaskById(id);
    }
}
