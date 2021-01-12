package com.cn.greemes.modules.mes.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.greemes.dto.MyMesMenuNode;
import com.cn.greemes.modules.mes.model.MesMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 后台菜单表 服务类
 * </p>
 *
 * @author zbb
 * @since 2021-01-11
 */
public interface MesMenuService extends IService<MesMenu> {
    /**
     * 创建后台菜单
     */
    boolean create(MesMenu mesMenu);

    /**
     * 修改后台菜单
     */
    boolean update(Long id, MesMenu mesMenu);

    /**
     * 分页查询后台菜单
     */
    Page<MesMenu> list(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 树形结构返回所有菜单列表
     */
    List<MyMesMenuNode> treeList();

    /**
     * 修改菜单显示状态
     */
    boolean updateHidden(Long id, Integer hidden);
}
