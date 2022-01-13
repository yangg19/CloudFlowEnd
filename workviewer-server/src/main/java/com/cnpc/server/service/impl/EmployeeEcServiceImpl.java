package com.cnpc.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnpc.server.mapper.EmployeeMapper;
import com.cnpc.server.pojo.Employee;
import com.cnpc.server.pojo.EmployeeEc;
import com.cnpc.server.mapper.EmployeeEcMapper;
import com.cnpc.server.pojo.RespBean;
import com.cnpc.server.pojo.RespPageBean;
import com.cnpc.server.service.IEmployeeEcService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
@Service
public class EmployeeEcServiceImpl extends ServiceImpl<EmployeeEcMapper, EmployeeEc> implements IEmployeeEcService {

}
