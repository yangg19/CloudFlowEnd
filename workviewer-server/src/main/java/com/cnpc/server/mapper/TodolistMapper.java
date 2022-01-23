package com.cnpc.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    IPage<Todolist> getTodolistByPage(Page<Todolist> page, @Param("todolist") Todolist todolist, @Param("beginDateScope") LocalDate[] beginDateScope);
}
