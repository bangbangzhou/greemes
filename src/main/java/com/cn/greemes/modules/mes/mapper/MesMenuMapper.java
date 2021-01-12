package com.cn.greemes.modules.mes.mapper;

import com.cn.greemes.modules.mes.model.MesMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台菜单表 Mapper 接口
 * </p>
 *
 * @author zbb
 * @since 2021-01-11
 */
public interface MesMenuMapper extends BaseMapper<MesMenu> {
    /**
     * 根据后台用户ID获取菜单
     */
    List<MesMenu> getMenuList(@Param("adminId") Long adminId);
    /**
     * 根据角色ID获取菜单
     */
    List<MesMenu> getMenuListByRoleId(@Param("roleId") Long roleId);

}
