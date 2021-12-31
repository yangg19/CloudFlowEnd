package com.cnpc.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cnpc.server.pojo.Department;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 获取所有部门
     *
     * @Params: []
     * @Return: java.util.List<com.cnpc.server.pojo.Department>
     * @Author: yangg19
     * @UpdateTime: 2021/12/30 13:57
     * @Throws:
     */
    List<Department> getAllDepartments(Integer parentId);

    /**
     * 添加部门
     *
     * @Params: [dep]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2021/12/30 14:13
     * @Throws:
     */
    void addDep(Department dep);

    /**
     * 删除部门
     *
     * @Params: [dep]
     * @Return: void
     * @Author: yangg19
     * @UpdateTime: 2021/12/30 14:35
     * @Throws:
     */
    void deleteDep(Department dep);
}
