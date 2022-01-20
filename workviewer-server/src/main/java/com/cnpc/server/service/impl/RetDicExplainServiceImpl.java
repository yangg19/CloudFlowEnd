package com.cnpc.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cnpc.server.mapper.RetDicExplainMapper;
import com.cnpc.server.pojo.RespBean;
import com.cnpc.server.pojo.RetDic;
import com.cnpc.server.pojo.RetDicExplain;
import com.cnpc.server.service.IRetDicExplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @Author: yangg19
 * @version: 1.0.0
 * @Date: 2022年01月13日 10:52:00
 */
@Service
public class RetDicExplainServiceImpl extends ServiceImpl<RetDicExplainMapper, RetDicExplain> implements IRetDicExplainService {
    @Autowired
    private RetDicExplainMapper retDicExplainMapper;

    /**
     * 获取对应字典的补充说明
     *
     * @Params: [retDic]
     * @Return: com.cnpc.server.pojo.RetDicExplain
     * @Author: yangg19
     * @UpdateTime: 2022/1/17 18:07
     * @Throws:
     */
    @Override
    public RetDicExplain getDicExplainInfo(String dicType, String dicValue) {
        return retDicExplainMapper.getDicExplainInfo(dicType, dicValue);
    }

    /**
     * 添加字典补充说明
     *
     * @Params: [retDicExplain]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/18 10:41
     * @Throws:
     */
    @Override
    public RespBean addDicExplainInfo(RetDicExplain retDicExplain) {
        if (1 == retDicExplainMapper.insert(retDicExplain)) {
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    /**
     * 更新字典补充说明
     *
     * @Params: [retDicExplain]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/18 13:57
     * @Throws:
     */
    @Override
    public RespBean updateDicExplainInfo(RetDicExplain retDicExplain) {
        if (retDicExplainMapper.updateDicExplainInfo(retDicExplain)) {
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

}
