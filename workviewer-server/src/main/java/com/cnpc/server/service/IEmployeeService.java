package com.cnpc.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cnpc.server.pojo.Employee;
import com.cnpc.server.pojo.RespBean;
import com.cnpc.server.pojo.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
public interface IEmployeeService extends IService<Employee> {
    /**
     * 获取所有员工（分页）
     *
     * @Params: [currentPage, size, employee, beginDateScope]
     * @Return: com.cnpc.server.pojo.RespPageBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/4 10:55
     * @Throws:
     */
    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    /**
     * 获取工号
     *
     * @Params: []
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/11 16:47
     * @Throws:
     */
    RespBean maxWorkID();

    /**
     * 添加员工
     *
     * @Params: [employee]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/11 17:23
     * @Throws:
     */
    RespBean addEmp(Employee employee);


    /**
     * 查询员工
     *
     * @Params: [id]
     * @Return: java.util.List<com.cnpc.server.pojo.Employee>
     * @Author: yangg19
     * @UpdateTime: 2022/1/14 14:45
     * @Throws:
     */
    List<Employee> getEmployee(Integer id);
}
