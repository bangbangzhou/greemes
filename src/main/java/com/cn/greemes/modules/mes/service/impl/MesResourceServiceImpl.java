package com.cn.greemes.modules.mes.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.greemes.modules.mes.model.MesResource;
import com.cn.greemes.modules.mes.mapper.MesResourceMapper;
import com.cn.greemes.modules.mes.service.MesAdminCacheService;
import com.cn.greemes.modules.mes.service.MesResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 后台资源表 服务实现类
 * </p>
 *
 * @author zbb
 * @since 2021-01-11
 */
@Service
public class MesResourceServiceImpl extends ServiceImpl<MesResourceMapper, MesResource> implements MesResourceService {
    @Autowired
    private MesAdminCacheService adminCacheService;
    @Override
    public boolean create(MesResource umsResource) {
        umsResource.setCreateTime(new Date());
        return save(umsResource);
    }

    @Override
    public boolean update(Long id, MesResource umsResource) {
        umsResource.setId(id);
        boolean success = updateById(umsResource);
        adminCacheService.delResourceListByResource(id);
        return success;
    }

    @Override
    public boolean delete(Long id) {
        boolean success = removeById(id);
        adminCacheService.delResourceListByResource(id);
        return success;
    }

    @Override
    public Page<MesResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        Page<MesResource> page = new Page<>(pageNum,pageSize);
        QueryWrapper<MesResource> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<MesResource> lambda = wrapper.lambda();
        if(categoryId!=null){
            lambda.eq(MesResource::getCategoryId,categoryId);
        }
        if(StrUtil.isNotEmpty(nameKeyword)){
            lambda.like(MesResource::getName,nameKeyword);
        }
        if(StrUtil.isNotEmpty(urlKeyword)){
            lambda.like(MesResource::getUrl,urlKeyword);
        }
        return page(page,wrapper);
    }
}
