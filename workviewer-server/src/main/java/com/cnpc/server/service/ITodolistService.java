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

    RespPageBean getTodolistByPage(Integer currentPage, Integer size, Todolist todolist, LocalDate[] beginDateScope);

    RespBean addTodolist(Todolist todolist);
}
