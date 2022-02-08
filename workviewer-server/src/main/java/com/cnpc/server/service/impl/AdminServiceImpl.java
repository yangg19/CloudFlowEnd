package com.cnpc.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnpc.server.AdminUtils;
import com.cnpc.server.config.security.component.JwtTokenUtil;
import com.cnpc.server.mapper.AdminInfoMapper;
import com.cnpc.server.mapper.AdminMapper;
import com.cnpc.server.mapper.AdminRoleMapper;
import com.cnpc.server.mapper.RoleMapper;
import com.cnpc.server.pojo.*;
import com.cnpc.server.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

import static com.cnpc.server.AdminUtils.getCurrentAdmin;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminInfoMapper adminInfoMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 登陆后返回token
     *
     * @Params: [username, password, request]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2021/12/20 10:16
     * @Throws:
     */
    @Override
    public RespBean login(String username, String password, HttpServletRequest request) {
//        public RespBean login(String username, String password, String code, HttpServletRequest request) {

//        // 验证码校验
//        String captcha = (String) request.getSession().getAttribute("captcha");
//        if (StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)) { // 忽略大小写
//            return RespBean.error("验证码输入错误，请重新输入");
//        }
        // 通过用户名获取用户信息
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // 将用户输入的密码与用户信息对应的密码进行匹配，若用户信息为空或匹配失败则输入信息有误
        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确");
        }
        if (!userDetails.isEnabled()) {
            return RespBean.error("账号被禁用，请联系管理员");
        }
        // 登录成功后要把目前登录对象放在Spring Security全文
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success("", tokenMap);
//        return RespBean.success("登录成功", tokenMap);

    }

    /**
     * 根据用户名获取完整用户信息
     *
     * @Params: [username]
     * @Return: com.cnpc.server.pojo.Admin
     * @Author: yangg19
     * @UpdateTime: 2021/12/20 13:27
     * @Throws:
     */
    @Override
    public Admin getAdminByUsername(String username) {
        // TODO MybatisPlus单个查询方法
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username).eq("enabled", true));
    }

    /**
     * 根据用户id查询角色列表
     *
     * @Params: [adminId]
     * @Return: java.util.List<com.cnpc.server.pojo.Role>
     * @Author: yangg19
     * @UpdateTime: 2021/12/27 15:16
     * @Throws:
     */
    @Override
    public List<Role> getRoles(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }

    /**
     * 获取所有操作员
     *
     * @Params: [keywords]
     * @Return: java.util.List<com.cnpc.server.pojo.Admin>
     * @Author: yangg19
     * @UpdateTime: 2021/12/31 9:56
     * @Throws:
     */
    @Override
    public List<Admin> getAllAdmins(String keywords) {
        // 不能查已登录的操作员信息
        return adminMapper.getAllAdmins(AdminUtils.getCurrentAdmin().getId(), keywords);
    }

    /**
     * 更新操作员角色
     *
     * @Params: [adminId, rids]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2021/12/31 10:58
     * @Throws:
     */
    @Override
    @Transactional
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        // 先删除再添加
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId", adminId));
        // 返回受影响的行数
        Integer result = adminRoleMapper.addAdminRole(adminId, rids);
        if(rids.length==result) {
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    /**
     * 更新用户密码
     *
     * @Params: [oldPass, pass, adminId]
     * @Return: com.cnpc.server.pojo.RespBean
     * @Author: yangg19
     * @UpdateTime: 2022/1/19 16:21
     * @Throws:
     */
    @Override
    public RespBean updateAdminPassword(String oldPass, String pass, Integer adminId) {
        Admin admin = adminMapper.selectById(adminId);
        // 密码加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(oldPass, admin.getPassword())) {
            admin.setPassword(encoder.encode(pass));
            int result = adminMapper.updateById(admin);
            if(1==result) {
                return RespBean.success("修改成功！");
            }
        }

        return RespBean.error("旧密码不正确，请重新输入！");
    }

    /**
     * 获取所有员工
     *
     * @Params: [keywords]
     * @Return: java.util.List<com.cnpc.server.pojo.Admin>
     * @Author: yangg19
     * @UpdateTime: 2022/1/27 11:20
     * @Throws:
     */
    @Override
    public List<Admin> getAllAdmin(String keywords) {
        return adminMapper.getAllAdmin(keywords);
    }

    /**
     * 更新用户头像
     * @param url
     * @param id
     * @param authentication
     * @return
     */
    @Override
    public RespBean updateAdminUserFace(String url, Integer id, Authentication authentication) {
        Admin admin = adminMapper.selectById(id);
        admin.setUserFace(url);
        int i = adminMapper.updateById(admin);
        if (i == 1){
            Admin principal = (Admin) authentication.getPrincipal();
            principal.setUserFace(url);
            //更新Authentication
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(admin,authentication.getCredentials(),authentication.getAuthorities()));
            return RespBean.success("更新成功",url);
        }
        return RespBean.error("更新失败");
    }

    @Override
    public String getAdminName(Integer id) {
        return adminMapper.getAdminName(id);
    }
}
