package com.cnpc.server.service.impl;

import com.cnpc.pojo.Admin;
import com.cnpc.mapper.AdminMapper;
import com.cnpc.service.IAdminService;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
