package com.cnpc.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cnpc.server.mapper.RetDicMapper;
import com.cnpc.server.pojo.Employee;
import com.cnpc.server.pojo.RespPageBean;
import com.cnpc.server.pojo.RetDic;
import com.cnpc.server.service.IRetDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * TODO
 *
 * @Author: yangg19
 * @version: 1.0.0
 * @Date: 2022年01月13日 10:52:00
 */
@Service
public class RetDicServiceImpl extends ServiceImpl<RetDicMapper, RetDic> implements IRetDicService {
    @Autowired
    private RetDicMapper retDicMapper;
    /**
     * 获取字典
     *
     * @Params: [currentPage, size, retDic, beginDateScope]
     * @Return: com.cnpc.server.pojo.RespPageBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/13 10:55
     * @Throws:
     */
    @Override
    public RespPageBean getDicByPage(Integer currentPage, Integer size, RetDic retDic, LocalDate[] beginDateScope) {
        // 开启分页
        Page<RetDic> page = new Page<>(currentPage, size);
        IPage<RetDic> dicByPage = retDicMapper.getDicByPage(page, retDic, beginDateScope);
        RespPageBean respPageBean = new RespPageBean(dicByPage.getTotal(), dicByPage.getRecords());
        return respPageBean;
    }
}
