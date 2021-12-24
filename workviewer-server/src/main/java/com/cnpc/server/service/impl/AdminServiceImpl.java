package com.cnpc.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cnpc.server.config.security.JwtTokenUtil;
import com.cnpc.server.mapper.AdminMapper;
import com.cnpc.server.pojo.Admin;
import com.cnpc.server.pojo.Menu;
import com.cnpc.server.pojo.RespBean;
import com.cnpc.server.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
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
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        // 验证码校验
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)) { // 忽略大小写
            return RespBean.error("验证码输入错误，请重新输入");
        }
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
        return RespBean.sucess("登录成功", tokenMap);
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

}
