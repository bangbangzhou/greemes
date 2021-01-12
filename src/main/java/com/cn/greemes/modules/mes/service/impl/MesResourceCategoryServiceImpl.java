package com.cn.greemes.modules.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cn.greemes.modules.mes.model.MesResourceCategory;
import com.cn.greemes.modules.mes.mapper.MesResourceCategoryMapper;
import com.cn.greemes.modules.mes.service.MesResourceCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 资源分类表 服务实现类
 * </p>
 *
 * @author zbb
 * @since 2021-01-11
 */
@Service
public class MesResourceCategoryServiceImpl extends ServiceImpl<MesResourceCategoryMapper, MesResourceCategory> implements MesResourceCategoryService {

    @Override
    public List<MesResourceCategory> listAll() {
        QueryWrapper<MesResourceCategory> wrapper = new QueryWrapper<>();
        wrapper.lambda().orderByDesc(MesResourceCategory::getSort);
        return list(wrapper);
    }

    @Override
    public boolean create(MesResourceCategory umsResourceCategory) {
        umsResourceCategory.setCreateTime(new Date());
        return save(umsResourceCategory);
    }
}
