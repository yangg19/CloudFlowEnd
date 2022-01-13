package com.cnpc.server.service.impl;

import com.cnpc.server.pojo.Department;
import com.cnpc.server.mapper.DepartmentMapper;
import com.cnpc.server.pojo.RespBean;
import com.cnpc.server.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    /**
     * 获取所有部门
     *
     * @Params: []
     * @Return: java.util.List<com.cnpc.server.pojo.Department>
     * @Author: yangg19
     * @UpdateTime: 2021/12/30 13:56
     * @Throws:
     */
    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartments(-1);
    }

    /**
     * 添加部门
     *
     * @Params: [dep]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2021/12/30 14:13
     * @Throws:
     */
    @Override
    public RespBean addDep(Department dep) {
        dep.setEnabled(true);
        departmentMapper.addDep(dep);
        if (1==dep.getResult()) {
            return RespBean.success("添加成功！", dep);
        }
        return RespBean.error("添加失败！");
    }

    /**
     * 删除部门
     *
     * @Params: [id]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2021/12/30 14:34
     * @Throws:
     */
    @Override
    public RespBean deleteDep(Integer id) {
        Department dep = new Department();
        dep.setId(id);
        departmentMapper.deleteDep(dep);
        if (-2==dep.getResult()) {
            return RespBean.error("该部门下存在子部门，删除失败！");
        }
        if (-1 == dep.getResult()) {
            return RespBean.error("该部门下还有员工，删除失败！");
        }
        if (1== dep.getResult()) {
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }
}
