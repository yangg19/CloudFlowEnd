package com.cnpc.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cnpc.server.AdminUtils;
import com.cnpc.server.config.security.component.JwtTokenUtil;
import com.cnpc.server.mapper.AdminInfoMapper;
import com.cnpc.server.mapper.AdminMapper;
import com.cnpc.server.mapper.AdminRoleMapper;
import com.cnpc.server.mapper.RoleMapper;
import com.cnpc.server.pojo.*;
import com.cnpc.server.service.IAdminInfoService;
import com.cnpc.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
@Service
public class AdminInfoServiceImpl extends ServiceImpl<AdminInfoMapper, AdminInfo> implements IAdminInfoService {
    @Autowired
    private AdminInfoMapper adminInfoMapper;

    @Override
    public RespPageBean getAdminByPage(Integer currentPage, Integer size, AdminInfo adminInfo) {
        // 开启分页
        Page<AdminInfo> page = new Page<>(currentPage, size);
        IPage<AdminInfo> employeeByPage = adminInfoMapper.getAdminByPage(page, adminInfo);
        RespPageBean respPageBean = new RespPageBean(employeeByPage.getTotal(), employeeByPage.getRecords());
        return respPageBean;
    }


    /**
     * 添加员工
     *
     * @Params: [adminInfo]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/11 17:23
     * @Throws:
     */
    @Override
    public RespBean addAdminInfo(AdminInfo adminInfo) {
        if (1 == adminInfoMapper.insert(adminInfo)) {
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
    public List<AdminInfo> getAdminInfo(Integer id) {
        return adminInfoMapper.getAdminInfo(id);
    }

    /**
     * 补充注册信息
     *
     * @Params: [adminInfo]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2022/2/9 18:25
     * @Throws:
     */
    @Override
    public RespBean RegisterAdminSubInfo(AdminInfo adminInfo) {
        if (adminInfoMapper.RegisterAdminSubInfo(adminInfo)) {
            return RespBean.success("");
        }
        return RespBean.error("");
    }

    /**
     * 根据当前用户获取用户信息
     *
     * @Params: []
     * @Return: com.cnpc.server.pojo.RespPageBean
     * @Author: yangg19
     * @UpdateTime: 2022/2/10 15:51
     * @Throws:
     */
    @Override
    public AdminInfo getAdminInfoById() {

        return adminInfoMapper.getAdminInfoById(AdminUtils.getCurrentAdmin().getId());
    }
}
