package com.cnpc.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cnpc.server.pojo.Department;
import com.cnpc.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
public interface IDepartmentService extends IService<Department> {

    /**
     * 获取所有部门
     *
     * @Params: []
     * @Return: java.util.List<com.cnpc.server.pojo.Department>
     * @Author: yangg19
     * @UpdateTime: 2021/12/30 13:56
     * @Throws:
     */
    List<Department> getAllDepartments();

    /**
     * 添加部门
     *
     * @Params: [dep]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2021/12/30 14:13
     * @Throws:
     */
    RespBean addDep(Department dep);

    /**
     * 删除
     *
     * @Params: [id]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2021/12/30 14:34
     * @Throws:
     */
    RespBean deleteDep(Integer id);
}
