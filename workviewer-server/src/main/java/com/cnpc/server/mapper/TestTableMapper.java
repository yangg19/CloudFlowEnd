package com.cnpc.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cnpc.server.pojo.SysMsg;
import com.cnpc.server.pojo.TestTable;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
public interface TestTableMapper extends BaseMapper<TestTable> {

    List<TestTable> getAll();
}
