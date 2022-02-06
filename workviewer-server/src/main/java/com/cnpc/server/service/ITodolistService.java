package com.cnpc.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cnpc.server.pojo.RespBean;
import com.cnpc.server.pojo.RespPageBean;
import com.cnpc.server.pojo.Todolist;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    /**
     * 重用待办
     *
     * @Params: [id]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/29 15:18
     * @Throws:
     */
    RespBean redoTaskById(Integer id);

    /**
     * 查询所有人新建/进行中/逾期待办
     *
     * @Params: [currentPage, size, beginDateScope]
     * @Return: com.cnpc.server.pojo.RespPageBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/30 9:44
     * @Throws:
     */
    RespPageBean getAllInitTodolistByPage(Integer currentPage, Integer size, LocalDate[] beginDateScope);

    /**
     * 查询所有人待办事项(完成或删除)
     *
     * @Params: [currentPage, size, taskStatusID, beginDateScope]
     * @Return: com.cnpc.server.pojo.RespPageBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/27 14:54
     * @Throws:
     */
    RespPageBean getAllTodolistByPage(Integer currentPage, Integer size, String taskStatusID, LocalDate[] beginDateScope);

    List<Todolist> getAllTodolist();

    List<Todolist> getAllLastTodolist();


    Map<String, String> getCurrentWeek();

    Map<String, String> getLastWeek();

    void updateTaskStatus(Date date);
}
