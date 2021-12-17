package com.cnpc.service.impl;

import com.cnpc.pojo.Employee;
import com.cnpc.mapper.EmployeeMapper;
import com.cnpc.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
