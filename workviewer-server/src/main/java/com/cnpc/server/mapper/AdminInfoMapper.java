package com.cnpc.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnpc.server.pojo.Admin;
import com.cnpc.server.pojo.AdminInfo;
import com.cnpc.server.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
public interface AdminInfoMapper extends BaseMapper<AdminInfo> {

    /**
     * 获取所有员工（分页）
     *
     * @Params: [page, adminInfo]
     * @Return: com.baomidou.mybatisplus.core.metadata.IPage<com.cnpc.server.pojo.AdminInfo>
     * @Author: yangg19
     * @UpdateTime: 2022/2/8 13:08
     * @Throws:
     */
    IPage<AdminInfo> getAdminByPage(Page<AdminInfo> page, @Param("adminInfo") AdminInfo adminInfo);

    /**
     * 查询员工
     *
     * @Params: [id]
     * @Return: java.util.List<com.cnpc.server.pojo.AdminInfo>
     * @Author: yangg19
     * @UpdateTime: 2022/1/14 14:45
     * @Throws:
     */
    List<AdminInfo> getAdminInfo(@Param("id") Integer id);
}
