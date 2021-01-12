package com.cn.greemes.modules.mes.service;/*
 *Created by zbb on 2021/1/11
 **/

import com.cn.greemes.modules.mes.model.MesAdmin;
import com.cn.greemes.modules.mes.model.MesResource;

import java.util.List;

public interface MesAdminCacheService {

    /**
     * 删除后台用户缓存
     */
    void delAdmin(Long adminId);

    /**
     * 删除后台用户资源列表缓存
     */
    void delResourceList(Long adminId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     */
    void delResourceListByRole(Long roleId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     */
    void delResourceListByRoleIds(List<Long> roleIds);

    /**
     * 当资源信息改变时，删除资源项目后台用户缓存
     */
    void delResourceListByResource(Long resourceId);

    /**
     * 获取缓存后台用户信息
     */
    MesAdmin getAdmin(String username);

    /**
     * 设置缓存后台用户信息
     */
    void setAdmin(MesAdmin admin);

    /**
     * 获取缓存后台用户资源列表
     */
    List<MesResource> getResourceList(Long adminId);

    /**
     * 设置后台后台用户资源列表
     */
    void setResourceList(Long adminId, List<MesResource> resourceList);
}
