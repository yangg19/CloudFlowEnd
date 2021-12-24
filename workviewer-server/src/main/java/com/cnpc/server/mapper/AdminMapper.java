package com.cnpc.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cnpc.server.pojo.Admin;
import com.cnpc.server.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
public interface AdminMapper extends BaseMapper<Admin> {

}
