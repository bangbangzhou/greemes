package com.cn.greemes.modules.mes.service;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.greemes.modules.mes.model.MesResource;
import com.cn.greemes.modules.mes.model.MesResourceCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 资源分类表 服务类
 * </p>
 *
 * @author zbb
 * @since 2021-01-11
 */
public interface MesResourceCategoryService extends IService<MesResourceCategory> {
    /**
     * 获取所有资源分类
     */
    List<MesResourceCategory> listAll();

    /**
     * 创建资源分类
     */
    boolean create(MesResourceCategory umsResourceCategory);
}
