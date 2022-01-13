package com.cnpc.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cnpc.server.pojo.RespPageBean;
import com.cnpc.server.pojo.RetDic;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * TODO
 *
 * @Author: yangg19
 * @version: 1.0.0
 * @Date: 2022年01月13日 10:50:00
 */
public interface IRetDicService extends IService<RetDic> {
    /**
     * 获取字典
     *
     * @Params: [currentPage, size, retDic, beginDateScope]
     * @Return: com.cnpc.server.pojo.RespPageBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/13 10:54
     * @Throws:
     */
    RespPageBean getDicByPage(Integer currentPage, Integer size, RetDic retDic, LocalDate[] beginDateScope);
}
