package com.cnpc.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnpc.server.pojo.RespBean;
import com.cnpc.server.pojo.Todolist;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangg19
 * @since 2022-01-23
 */
public interface TodolistMapper extends BaseMapper<Todolist> {

    /**
     * 查询待办事项(完成或删除)
     *
     * @Params: [page, userID, taskStatusID, beginDateScope]
     * @Return: com.baomidou.mybatisplus.core.metadata.IPage<com.cnpc.server.pojo.Todolist>
     * @Author: yangg19
     * @UpdateTime: 2022/1/27 14:55
     * @Throws:
     */
    IPage<Todolist> getTodolistByPage(Page<Todolist> page, @Param("userID") Integer userID, @Param("taskStatusID") String taskStatusID, @Param("beginDateScope") LocalDate[] beginDateScope);

    /**
     * 查询待办事项(新建/进行中/逾期)
     *
     * @Params: [page, userID, beginDateScope]
     * @Return: com.baomidou.mybatisplus.core.metadata.IPage<com.cnpc.server.pojo.Todolist>
     * @Author: yangg19
     * @UpdateTime: 2022/1/27 14:55
     * @Throws:
     */
    IPage<Todolist> getInitTodolistByPage(Page<Todolist> page, @Param("userID") Integer userID, @Param("beginDateScope") LocalDate[] beginDateScope);
    
    /**
     * 完成待办
     *
     * @Params: [id]
     * @Return: boolean
     * @Author: yangg19
     * @UpdateTime: 2022/1/27 15:24 
     * @Throws: 
     */
    boolean comTaskById(@Param("id") Integer id);

    /**
     * 删除待办（假）
     *
     * @Params: [id]
     * @Return: boolean
     * @Author: yangg19
     * @UpdateTime: 2022/1/27 16:20
     * @Throws:
     */
    boolean deleteTaskById(@Param("id") Integer id);

    /**
     * 重用待办
     *
     * @Params: [id]
     * @Return: boolean
     * @Author: yangg19
     * @UpdateTime: 2022/1/29 15:19
     * @Throws:
     */
    boolean redoTaskById(@Param("id") Integer id);

    /**
     * 查询所有人新建/进行中/逾期待办
     *
     * @Params: [page, id, beginDateScope]
     * @Return: com.baomidou.mybatisplus.core.metadata.IPage<com.cnpc.server.pojo.Todolist>
     * @Author: yangg19
     * @UpdateTime: 2022/1/30 9:46
     * @Throws:
     */
    IPage<Todolist> getAllInitTodolistByPage(Page<Todolist> page, @Param("beginDateScope") LocalDate[] beginDateScope);

    /**
     * 查询所有人待办事项(完成或删除)
     *
     * @Params: [page, userID, taskStatusID, beginDateScope]
     * @Return: com.baomidou.mybatisplus.core.metadata.IPage<com.cnpc.server.pojo.Todolist>
     * @Author: yangg19
     * @UpdateTime: 2022/1/27 14:55
     * @Throws:
     */
    IPage<Todolist> getAllTodolistByPage(Page<Todolist> page, @Param("userID") Integer userID, @Param("taskStatusID") String taskStatusID, @Param("beginDateScope") LocalDate[] beginDateScope);

    List<Todolist> getAllTodolist(@Param("startDate") String startDate, @Param("endDate") String endDate);

    List<Todolist> getAllLastTodolist(@Param("startDate") String startDate, @Param("endDate") String endDate);

    void updateTaskStatus(@Param("date") Date date);
}
