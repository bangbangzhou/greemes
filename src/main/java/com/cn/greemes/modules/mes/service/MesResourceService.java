package com.cn.greemes.modules.mes.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.greemes.modules.mes.model.MesResource;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 后台资源表 服务类
 * </p>
 *
 * @author zbb
 * @since 2021-01-11
 */
public interface MesResourceService extends IService<MesResource> {
    /**
     * 添加资源
     */
    boolean create(MesResource umsResource);

    /**
     * 修改资源
     */
    boolean update(Long id, MesResource umsResource);

    /**
     * 删除资源
     */
    boolean delete(Long id);

    /**
     * 分页查询资源
     */
    Page<MesResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);
}
