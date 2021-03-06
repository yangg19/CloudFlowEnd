package com.cnpc.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cnpc.server.AdminUtils;
import com.cnpc.server.mapper.TodolistMapper;
import com.cnpc.server.pojo.RespBean;
import com.cnpc.server.pojo.RespPageBean;


import com.cnpc.server.pojo.RetDic;
import com.cnpc.server.pojo.Todolist;
import com.cnpc.server.service.ITodolistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangg19
 * @since 2022-01-23
 */
@Service
public class TodolistServiceImpl extends ServiceImpl<TodolistMapper, Todolist> implements ITodolistService {

    @Autowired
    private TodolistMapper todolistMapper;

    /**
     * 查询待办事项(完成和删除)
     *
     * @Params: [currentPage, size, taskStatusID, beginDateScope]
     * @Return: com.cnpc.server.pojo.RespPageBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/27 14:54
     * @Throws:
     */
    @Override
    public RespPageBean getTodolistByPage(Integer currentPage, Integer size, String taskStatusID, LocalDate[] beginDateScope) {
        // 开启分页
        Page<Todolist> page = new Page<>(currentPage, size);
        IPage<Todolist> todolistByPage = todolistMapper.getTodolistByPage(page, AdminUtils.getCurrentAdmin().getId(), taskStatusID, beginDateScope);
        RespPageBean respPageBean = new RespPageBean(todolistByPage.getTotal(), todolistByPage.getRecords());
        return respPageBean;
    }
    /**
     * 查询待办事项(新建/进行中/逾期)
     *
     * @Params: [currentPage, size, beginDateScope]
     * @Return: com.cnpc.server.pojo.RespPageBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/27 14:54
     * @Throws:
     */
    @Override
    public RespPageBean getInitTodolistByPage(Integer currentPage, Integer size, LocalDate[] beginDateScope) {
        // 开启分页
        Page<Todolist> page = new Page<>(currentPage, size);
        IPage<Todolist> todolistByPage = todolistMapper.getInitTodolistByPage(page, AdminUtils.getCurrentAdmin().getId(), beginDateScope);
        RespPageBean respPageBean = new RespPageBean(todolistByPage.getTotal(), todolistByPage.getRecords());
        return respPageBean;
    }

    /**
     * 新增待办事项
     *
     * @Params: [todolist]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/27 14:54
     * @Throws:
     */
    @Override
    public RespBean addTodolist(Todolist todolist) {
        if (1 == todolistMapper.insert(todolist)) {
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    /**
     * 完成待办
     *
     * @Params: [id]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/27 16:19
     * @Throws:
     */
    @Override
    public RespBean comTaskById(Integer id) {
        if (todolistMapper.comTaskById(id)) {
            return RespBean.success("");
        }
        return RespBean.error("");
    }

    /**
     * 删除待办（假）
     *
     * @Params: [id]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/27 16:19
     * @Throws:
     */
    @Override
    public RespBean deleteTaskById(Integer id) {
        if (todolistMapper.deleteTaskById(id)) {
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @Override
    public RespBean redoTaskById(Integer id) {
        if (todolistMapper.redoTaskById(id)) {
            return RespBean.success("");
        }
        return RespBean.error("");
    }

    /**
     * 查询所有人新建/进行中/逾期待办
     *
     * @Params: [currentPage, size, beginDateScope]
     * @Return: com.cnpc.server.pojo.RespPageBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/30 9:44
     * @Throws:
     */
    @Override
    public RespPageBean getAllInitTodolistByPage(Integer currentPage, Integer size, LocalDate[] beginDateScope) {
        // 开启分页
        Page<Todolist> page = new Page<>(currentPage, size);
        IPage<Todolist> todolistByPage = todolistMapper.getAllInitTodolistByPage(page, beginDateScope);
        RespPageBean respPageBean = new RespPageBean(todolistByPage.getTotal(), todolistByPage.getRecords());
        return respPageBean;
    }

    /**
     * 查询所有人待办事项(完成和删除)
     *
     * @Params: [currentPage, size, taskStatusID, beginDateScope]
     * @Return: com.cnpc.server.pojo.RespPageBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/27 14:54
     * @Throws:
     */
    @Override
    public RespPageBean getAllTodolistByPage(Integer currentPage, Integer size, String taskStatusID, LocalDate[] beginDateScope) {
        // 开启分页
        Page<Todolist> page = new Page<>(currentPage, size);
        IPage<Todolist> todolistByPage = todolistMapper.getAllTodolistByPage(page, AdminUtils.getCurrentAdmin().getId(), taskStatusID, beginDateScope);
        RespPageBean respPageBean = new RespPageBean(todolistByPage.getTotal(), todolistByPage.getRecords());
        return respPageBean;
    }

    @Override
    public List<Todolist> getAllTodolist() {
        // 将日历设置为当前日期和时间
        Calendar c = GregorianCalendar.getInstance();
        System.out.println("Current week = " + Calendar.DAY_OF_WEEK);

        // 设置日历为当前周的周一
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//        System.out.println("Current week = " + Calendar.DAY_OF_WEEK);

        // 打印从周一开始的当前一周的日期
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String startDate = "", endDate = "";

        startDate = df.format(c.getTime());
        c.add(Calendar.DATE, 6);
//        endDate = df.format(c.getTime());
        endDate = "2999-01-01";
        System.out.println(startDate + ',' + endDate);
        return todolistMapper.getAllTodolist(startDate, endDate);
    }

    @Override
    public List<Todolist> getAllLastTodolist() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DATE, -i - 6);
        String startDate = "", endDate = "";
        startDate = df.format(c.getTime());
        c.add(Calendar.DATE, 6);
        endDate = df.format(c.getTime());
//        endDate = "2021-01-01";

        return todolistMapper.getAllLastTodolist(startDate, endDate);
    }

    @Override
    public Map<String, String> getCurrentWeek() {
        Map<String, String> map = new HashMap<String, String>();

        // 将日历设置为当前日期和时间
        Calendar c = GregorianCalendar.getInstance();
        System.out.println("Current week = " + Calendar.DAY_OF_WEEK);

        // 设置日历为当前周的周一
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//        System.out.println("Current week = " + Calendar.DAY_OF_WEEK);

        // 打印从周一开始的当前一周的日期
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String startDate = "", endDate = "";

        startDate = df.format(c.getTime());
        c.add(Calendar.DATE, 6);
        endDate = df.format(c.getTime());
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        return map;
    }

    @Override
    public Map<String, String> getLastWeek() {
        Map<String, String> map = new HashMap<String, String>();

        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DATE, -i - 6);
        String startDate = "", endDate = "";
        startDate = df.format(c.getTime());
        c.add(Calendar.DATE, 6);
        endDate = df.format(c.getTime());
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        return map;
    }

    @Override
    public void updateTaskStatus(Date date) {
        System.out.println(date);

        String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
        todolistMapper.updateTaskStatus(format);
    }
}
