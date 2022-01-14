package com.cnpc.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cnpc.server.mapper.EmployeeMapper;
import com.cnpc.server.pojo.Employee;
import com.cnpc.server.pojo.RespBean;
import com.cnpc.server.pojo.RespPageBean;
import com.cnpc.server.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    /**
     * 获取所有员工（分页）
     *
     * @Params: [currentPage, size, employee, beginDateScope]
     * @Return: com.cnpc.server.pojo.RespPageBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/4 10:55
     * @Throws:
     */
    @Override
    public RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope) {
        // 开启分页
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> employeeByPage = employeeMapper.getEmployeeByPage(page, employee, beginDateScope);
        RespPageBean respPageBean = new RespPageBean(employeeByPage.getTotal(), employeeByPage.getRecords());
        return respPageBean;
    }

    /**
     * 获取工号
     *
     * @Params: []
     * @Return: String.format(" % 08d " = 获得固定8位长度的数字 （ 不足的前面补0 ）
     * @Author: yangg19
     * @UpdateTime: 2022/1/11 16:47
     * @Throws:
     */

    @Override
    public RespBean maxWorkID() {
        List<Map<String, Object>> maps = baseMapper.selectMaps(new QueryWrapper<Employee>().select("max(workID)"));
        return RespBean.success(null, String.format("%08d", Integer.parseInt(maps.get(0).get("max(workID)").toString()) + 1));
    }

    /**
     * 添加员工
     *
     * @Params: [employee]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/11 17:23
     * @Throws:
     */
    @Override
    public RespBean addEmp(Employee employee) {
        // 处理合同期限，保留两位小数
        LocalDate beginContract = employee.getBeginContract();// 合同开始时间
        LocalDate endContract = employee.getEndContract();// 合同结束时间
        // 计算 两个日期相差多少天
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        // 保留两位小数
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        // 计算以年为单位
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days / 365.00)));
        if (1 == employeeMapper.insert(employee)) {
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    /**
     * 查询员工
     *
     * @Params: [id]
     * @Return: java.util.List<com.cnpc.server.pojo.Employee>
     * @Author: yangg19
     * @UpdateTime: 2022/1/14 14:45
     * @Throws:
     */
    @Override
    public List<Employee> getEmployee(Integer id) {
        return employeeMapper.getEmployee(id);
    }
}
