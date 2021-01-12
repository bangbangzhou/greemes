package com.cn.greemes.modules.mes.mapper;

import com.cn.greemes.modules.mes.model.MesRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 Mapper 接口
 * </p>
 *
 * @author zbb
 * @since 2021-01-11
 */
public interface MesRoleMapper extends BaseMapper<MesRole> {
    /**
     * 获取用户所有角色
     */
    List<MesRole> getRoleList(@Param("adminId") Long adminId);
}
