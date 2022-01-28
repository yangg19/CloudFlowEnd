package com.cnpc.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnpc.server.pojo.RespBean;
import com.cnpc.server.pojo.Todolist;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

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
}
