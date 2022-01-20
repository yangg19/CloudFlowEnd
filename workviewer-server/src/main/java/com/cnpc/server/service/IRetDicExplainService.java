package com.cnpc.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cnpc.server.pojo.RespBean;
import com.cnpc.server.pojo.RespPageBean;
import com.cnpc.server.pojo.RetDic;
import com.cnpc.server.pojo.RetDicExplain;

import java.time.LocalDate;
import java.util.List;

/**
 * TODO
 *
 * @Author: yangg19
 * @version: 1.0.0
 * @Date: 2022年01月13日 10:50:00
 */
public interface IRetDicExplainService extends IService<RetDicExplain> {
    /**
     * 获取对应字典的补充说明
     *
     * @Params: [retDic]
     * @Return: com.cnpc.server.pojo.RetDicExplain
     * @Author: yangg19
     * @UpdateTime: 2022/1/17 18:07
     * @Throws:
     */
    RetDicExplain getDicExplainInfo(String dicType, String dicValue);

    /**
     * 新增字典补充说明
     *
     * @Params: [retDicExplain]
     * @Return:
     * @Author: yangg19
     * @UpdateTime: 2022/1/18 10:41
     * @Throws:
     */
    RespBean addDicExplainInfo(RetDicExplain retDicExplain);

    /**
     * 更新字典补充说明
     *
     * @Params: [retDicExplain]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/18 13:56
     * @Throws:
     */
    RespBean updateDicExplainInfo(RetDicExplain retDicExplain);
}
