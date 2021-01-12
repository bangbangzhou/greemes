package com.cn.greemes.modules.mes.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.greemes.modules.mes.mapper.MesMenuMapper;
import com.cn.greemes.modules.mes.mapper.MesResourceMapper;
import com.cn.greemes.modules.mes.model.*;
import com.cn.greemes.modules.mes.mapper.MesRoleMapper;
import com.cn.greemes.modules.mes.service.MesAdminCacheService;
import com.cn.greemes.modules.mes.service.MesRoleMenuRelationService;
import com.cn.greemes.modules.mes.service.MesRoleResourceRelationService;
import com.cn.greemes.modules.mes.service.MesRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author zbb
 * @since 2021-01-11
 */
@Service
public class MesRoleServiceImpl extends ServiceImpl<MesRoleMapper, MesRole> implements MesRoleService {
    @Autowired
    private MesAdminCacheService adminCacheService;
    @Autowired
    private MesRoleMenuRelationService roleMenuRelationService;
    @Autowired
    private MesRoleResourceRelationService roleResourceRelationService;
    @Autowired
    private MesMenuMapper menuMapper;
    @Autowired
    private MesResourceMapper resourceMapper;
    @Override
    public boolean create(MesRole role) {
        role.setCreateTime(new Date());
        role.setAdminCount(0);
        role.setSort(0);
        return save(role);
    }

    @Override
    public boolean delete(List<Long> ids) {
        boolean success = removeByIds(ids);
        adminCacheService.delResourceListByRoleIds(ids);
        return success;
    }

    @Override
    public Page<MesRole> list(String keyword, Integer pageSize, Integer pageNum) {
        Page<MesRole> page = new Page<>(pageNum,pageSize);
        QueryWrapper<MesRole> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<MesRole> lambda = wrapper.lambda();
        if(StrUtil.isNotEmpty(keyword)){
            lambda.like(MesRole::getName,keyword);
        }
        return page(page,wrapper);
    }

    @Override
    public List<MesMenu> getMenuList(Long adminId) {
        return menuMapper.getMenuList(adminId);
    }

    @Override
    public List<MesMenu> listMenu(Long roleId) {
        return menuMapper.getMenuListByRoleId(roleId);
    }

    @Override
    public List<MesResource> listResource(Long roleId) {
        return resourceMapper.getResourceListByRoleId(roleId);
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        //先删除原有关系
        QueryWrapper<MesRoleMenuRelation> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(MesRoleMenuRelation::getRoleId,roleId);
        roleMenuRelationService.remove(wrapper);
        //批量插入新关系
        List<MesRoleMenuRelation> relationList = new ArrayList<>();
        for (Long menuId : menuIds) {
            MesRoleMenuRelation relation = new MesRoleMenuRelation();
            relation.setRoleId(roleId);
            relation.setMenuId(menuId);
            relationList.add(relation);
        }
        roleMenuRelationService.saveBatch(relationList);
        return menuIds.size();
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        //先删除原有关系
        QueryWrapper<MesRoleResourceRelation> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(MesRoleResourceRelation::getRoleId,roleId);
        roleResourceRelationService.remove(wrapper);
        //批量插入新关系
        List<MesRoleResourceRelation> relationList = new ArrayList<>();
        for (Long resourceId : resourceIds) {
            MesRoleResourceRelation relation = new MesRoleResourceRelation();
            relation.setRoleId(roleId);
            relation.setResourceId(resourceId);
            relationList.add(relation);
        }
        roleResourceRelationService.saveBatch(relationList);
        adminCacheService.delResourceListByRole(roleId);
        return resourceIds.size();
    }
}
