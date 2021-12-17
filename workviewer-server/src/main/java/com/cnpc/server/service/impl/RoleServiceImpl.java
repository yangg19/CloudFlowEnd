package com.cnpc.server.service.impl;

import com.cnpc.pojo.Role;
import com.cnpc.mapper.RoleMapper;
import com.cnpc.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
