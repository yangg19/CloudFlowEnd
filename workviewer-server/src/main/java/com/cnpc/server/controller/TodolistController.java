package com.cnpc.server.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.cnpc.server.pojo.*;
import com.cnpc.server.service.ITodolistService;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @ApiOperation(value = "查询所有人本周待办")
    @GetMapping("/all")
    public List<Todolist> getAllTodolist() {
        return todolistService.getAllTodolist();
    }

    @ApiOperation(value = "查询所有人上周待办")
    @GetMapping("/allLast")
    public List<Todolist> getAllLastTodolist() {
        return todolistService.getAllLastTodolist();
    }

    @ApiOperation(value = "查询本周日期")
    @GetMapping("/week")
    public Map<String, String> getCurrentWeek() {
        return todolistService.getCurrentWeek();
    }

    @ApiOperation(value = "查询上周日期")
    @GetMapping("/lastWeek")
    public Map<String, String> getLastWeek() {
        return todolistService.getLastWeek();
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

    @ApiOperation(value = "导出上周待办数据")
    @GetMapping(value = "/exportLast",produces = "application/octet-stream")
    public void exportLastTodolists(HttpServletResponse response){
        List<Todolist> list = todolistService.getAllLastTodolist();
        Map<String, String> lastWeek = todolistService.getLastWeek();
        String startDate = lastWeek.get("startDate");
        String endDate = lastWeek.get("endDate");
        ExportParams params = new ExportParams(startDate + "~" + endDate,"党群工作科工作时控表", ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params,Todolist.class,list);
        ServletOutputStream outputStream = null;

        try {
            //流形式
            response.setHeader("content-type","application/octet-stream");
            //中文乱码
            response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode("党群工作科工作时控表_" + startDate + "-" + endDate + ".xls","UTF-8"));
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (null != outputStream){
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @ApiOperation(value = "导出本周待办数据")
    @GetMapping(value = "/exportThis",produces = "application/octet-stream")
    public void exportThisTodolists(HttpServletResponse response){
        List<Todolist> list = todolistService.getAllTodolist();
        Map<String, String> currentWeek = todolistService.getCurrentWeek();
        String startDate = currentWeek.get("startDate");
        String endDate = currentWeek.get("endDate");
        ExportParams params = new ExportParams(startDate + "~" + endDate,"党群工作科工作时控表", ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params,Todolist.class,list);
        ServletOutputStream outputStream = null;

        try {
            //流形式
            response.setHeader("content-type","application/octet-stream");
            //中文乱码
            response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode("党群工作科工作时控表_" + startDate + "-" + endDate + ".xls","UTF-8"));
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (null != outputStream){
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
