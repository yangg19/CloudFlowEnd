package com.cnpc.server.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cnpc.server.mapper.TestTableMapper;
import com.cnpc.server.pojo.TestTable;
import com.cnpc.server.service.ITestTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
@Service
@DS("testSql")
public class TestTableServiceImpl extends ServiceImpl<TestTableMapper, TestTable> implements ITestTableService {

    @Autowired
    private TestTableMapper testTableMapper;

    @Override
    public List<TestTable> getAll() {
        return testTableMapper.getAll();
    }
}
