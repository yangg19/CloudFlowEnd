package com.cnpc.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnpc.server.pojo.RespBean;
import com.cnpc.server.pojo.RetDic;
import com.cnpc.server.pojo.RetDicExplain;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * TODO
 *
 * @Author: yangg19
 * @version: 1.0.0
 * @Date: 2022年01月13日 10:53:00
 */
public interface RetDicExplainMapper extends BaseMapper<RetDicExplain> {

    /**
     * 获取对应字典补充说明
     *
     * @Params: [dicType, dicValue]
     * @Return: com.cnpc.server.pojo.RetDicExplain
     * @Author: yangg19
     * @UpdateTime: 2022/1/18 10:43
     * @Throws:
     */
    RetDicExplain getDicExplainInfo(@Param("dicType") String dicType, @Param("dicValue") String dicValue);

    /**
     *  更新字典补充说明
     *
     * @Params: [retDicExplain]
     * @Return: boolean
     * @Author: yangg19
     * @UpdateTime: 2022/1/18 13:58
     * @Throws:
     */
    boolean updateDicExplainInfo(@Param("retDicExplain") RetDicExplain retDicExplain);
}
