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

    /**
     * 查询待办事项
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
            return RespBean.success("状态变更成功！");
        }
        return RespBean.error("状态变更失败！");
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

}
