package com.cn.greemes.modules.mes.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.cn.greemes.modules.mes.model.MesMenu;
import com.cn.greemes.modules.mes.model.MesResource;
import com.cn.greemes.modules.mes.model.MesRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务类
 * </p>
 *
 * @author zbb
 * @since 2021-01-11
 */
public interface MesRoleService extends IService<MesRole> {

    /**
     * 添加角色
     */
    boolean create(MesRole role);

    /**
     * 批量删除角色
     */
    boolean delete(List<Long> ids);

    /**
     * 分页获取角色列表
     */
    Page<MesRole> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 根据管理员ID获取对应菜单
     */
    List<MesMenu> getMenuList(Long adminId);

    /**
     * 获取角色相关菜单
     */
    List<MesMenu> listMenu(Long roleId);

    /**
     * 获取角色相关资源
     */
    List<MesResource> listResource(Long roleId);

    /**
     * 给角色分配菜单
     */
    @Transactional
    int allocMenu(Long roleId, List<Long> menuIds);

    /**
     * 给角色分配资源
     */
    @Transactional
    int allocResource(Long roleId, List<Long> resourceIds);

}
