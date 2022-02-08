package com.cnpc.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnpc.server.pojo.Admin;
import com.cnpc.server.pojo.Employee;
import com.cnpc.server.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

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
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 获取所有操作员
     *
     * @Params: [id, keywords]
     * @Return: java.util.List<com.cnpc.server.pojo.Admin>
     * @Author: yangg19
     * @UpdateTime: 2021/12/31 10:04
     * @Throws:
     */
    List<Admin> getAllAdmins(@Param("id") Integer id, @Param("keywords") String keywords);

    /**
     * 获取所有员工
     *
     * @Params: [keywords]
     * @Return: java.util.List<com.cnpc.server.pojo.Admin>
     * @Author: yangg19
     * @UpdateTime: 2022/1/27 11:21
     * @Throws:
     */
    List<Admin> getAllAdmin(@Param("keywords") String keywords);

    String getAdminName(Integer id);

}
