package com.cnpc.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cnpc.server.pojo.RespBean;
import com.cnpc.server.pojo.RespPageBean;
import com.cnpc.server.pojo.Todolist;

import java.time.LocalDate;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangg19
 * @since 2022-01-23
 */
public interface ITodolistService extends IService<Todolist> {

    /**
     * 查询待办事项(完成或删除)
     *
     * @Params: [currentPage, size, taskStatusID, beginDateScope]
     * @Return: com.cnpc.server.pojo.RespPageBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/27 14:54
     * @Throws:
     */
    RespPageBean getTodolistByPage(Integer currentPage, Integer size, String taskStatusID, LocalDate[] beginDateScope);

    /**
     * 查询待办事项(新建/进行中/逾期)
     *
     * @Params: [currentPage, size, beginDateScope]
     * @Return: com.cnpc.server.pojo.RespPageBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/27 14:54
     * @Throws:
     */
    RespPageBean getInitTodolistByPage(Integer currentPage, Integer size, LocalDate[] beginDateScope);

    /**
     * 添加待办事项
     *
     * @Params: [todolist]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/27 14:54
     * @Throws:
     */
    RespBean addTodolist(Todolist todolist);

    /**
     * 完成待办
     *
     * @Params: [id]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/27 15:22
     * @Throws:
     */
    RespBean comTaskById(Integer id);

    /**
     * 删除待办（假）
     *
     * @Params: [id]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/27 16:19
     * @Throws:
     */
    RespBean deleteTaskById(Integer id);
}
