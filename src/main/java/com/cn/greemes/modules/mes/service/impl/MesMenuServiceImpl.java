package com.cn.greemes.modules.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.greemes.dto.MyMesMenuNode;
import com.cn.greemes.modules.mes.model.MesMenu;
import com.cn.greemes.modules.mes.mapper.MesMenuMapper;
import com.cn.greemes.modules.mes.service.MesMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 后台菜单表 服务实现类
 * </p>
 *
 * @author zbb
 * @since 2021-01-11
 */
@Service
public class MesMenuServiceImpl extends ServiceImpl<MesMenuMapper, MesMenu> implements MesMenuService {
    @Override
    public boolean create(MesMenu umsMenu) {
        umsMenu.setCreateTime(new Date());
        updateLevel(umsMenu);
        return save(umsMenu);
    }

    /**
     * 修改菜单层级
     */
    private void updateLevel(MesMenu umsMenu) {
        if (umsMenu.getParentId() == 0) {
            //没有父菜单时为一级菜单
            umsMenu.setLevel(0);
        } else {
            //有父菜单时选择根据父菜单level设置
            MesMenu parentMenu = getById(umsMenu.getParentId());
            if (parentMenu != null) {
                umsMenu.setLevel(parentMenu.getLevel() + 1);
            } else {
                umsMenu.setLevel(0);
            }
        }
    }

    @Override
    public boolean update(Long id, MesMenu umsMenu) {
        umsMenu.setId(id);
        updateLevel(umsMenu);
        return updateById(umsMenu);
    }

    @Override
    public Page<MesMenu> list(Long parentId, Integer pageSize, Integer pageNum) {
        Page<MesMenu> page = new Page<>(pageNum,pageSize);
        QueryWrapper<MesMenu> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(MesMenu::getParentId,parentId)
                .orderByDesc(MesMenu::getSort);
        return page(page,wrapper);
    }

    @Override
    public List<MyMesMenuNode> treeList() {
        List<MesMenu> menuList = list();
        List<MyMesMenuNode> result = menuList.stream()
                .filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> covertMenuNode(menu, menuList)).collect(Collectors.toList());
        return result;
    }

    @Override
    public boolean updateHidden(Long id, Integer hidden) {
        MesMenu umsMenu = new MesMenu();
        umsMenu.setId(id);
        umsMenu.setHidden(hidden);
        return updateById(umsMenu);
    }

    /**
     * 将UmsMenu转化为UmsMenuNode并设置children属性
     */
    private MyMesMenuNode covertMenuNode(MesMenu menu, List<MesMenu> menuList) {
        MyMesMenuNode node = new MyMesMenuNode();
        BeanUtils.copyProperties(menu, node);
        List<MyMesMenuNode> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
