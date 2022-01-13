package com.cnpc.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnpc.server.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 获取所有员工（分页）
     *
     * @Params: [currentPage, size, employee, beginDateScope]
     * @Return: com.cnpc.server.pojo.RespPageBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/4 10:55
     * @Throws:
     */
    IPage<Employee> getEmployeeByPage(Page<Employee> page, @Param("employee") Employee employee, @Param("beginDateScope") LocalDate[] beginDateScope);
}
