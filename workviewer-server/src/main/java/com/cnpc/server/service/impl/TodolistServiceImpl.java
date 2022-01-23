package com.cnpc.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cnpc.server.mapper.TodolistMapper;
import com.cnpc.server.pojo.RespBean;
import com.cnpc.server.pojo.RespPageBean;
import com.cnpc.server.pojo.RetDic;
import com.cnpc.server.pojo.Todolist;
import com.cnpc.server.service.ITodolistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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

    @Override
    public RespPageBean getTodolistByPage(Integer currentPage, Integer size, Todolist todolist, LocalDate[] beginDateScope) {
        // 开启分页
        Page<Todolist> page = new Page<>(currentPage, size);
        IPage<Todolist> todolistByPage = todolistMapper.getTodolistByPage(page, todolist, beginDateScope);
        RespPageBean respPageBean = new RespPageBean(todolistByPage.getTotal(), todolistByPage.getRecords());
        return respPageBean;
    }

    @Override
    public RespBean addTodolist(Todolist todolist) {

        if (1 == todolistMapper.insert(todolist)) {
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }
}
