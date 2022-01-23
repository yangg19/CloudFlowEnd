package com.cnpc.service.impl;

import com.cnpc.pojo.Todolist;
import com.cnpc.mapper.TodolistMapper;
import com.cnpc.service.ITodolistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
