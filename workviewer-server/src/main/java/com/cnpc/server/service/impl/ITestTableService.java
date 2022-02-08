package com.cnpc.server.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cnpc.server.pojo.SysMsg;
import com.cnpc.server.pojo.TestTable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
public interface ITestTableService extends IService<TestTable> {

    List<TestTable> getAll();
}
