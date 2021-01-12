package com.cn.greemes.modules.mes.mapper;

import com.cn.greemes.modules.mes.model.MesResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台资源表 Mapper 接口
 * </p>
 *
 * @author zbb
 * @since 2021-01-11
 */
public interface MesResourceMapper extends BaseMapper<MesResource> {
    /**
     * 获取用户所有可访问资源
     */
    List<MesResource> getResourceList(@Param("adminId") Long adminId);

    /**
     * 根据角色ID获取资源
     */
    List<MesResource> getResourceListByRoleId(@Param("roleId") Long roleId);
}
