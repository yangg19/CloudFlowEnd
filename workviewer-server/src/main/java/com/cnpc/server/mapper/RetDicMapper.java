package com.cnpc.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnpc.server.pojo.RetDic;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

/**
 * TODO
 *
 * @Author: yangg19
 * @version: 1.0.0
 * @Date: 2022年01月13日 10:53:00
 */
public interface RetDicMapper extends BaseMapper<RetDic> {
    /**
     * 获取字典值
     *
     * @Params: [page, retDic, beginDateScope]
     * @Return: com.baomidou.mybatisplus.core.metadata.IPage<com.cnpc.server.pojo.RetDic>
     * @Author: yangg19
     * @UpdateTime: 2022/1/13 10:57
     * @Throws:
     */
    IPage<RetDic> getDicByPage(Page<RetDic> page, @Param("retDic") RetDic retDic, @Param("beginDateScope") LocalDate[] beginDateScope);
}
