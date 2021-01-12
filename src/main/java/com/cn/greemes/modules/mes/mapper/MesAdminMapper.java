package com.cn.greemes.modules.mes.mapper;

import com.cn.greemes.modules.mes.model.MesAdmin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author zbb
 * @since 2021-01-11
 */
public interface MesAdminMapper extends BaseMapper<MesAdmin> {
    /**
     * 获取资源相关用户ID列表
     */
    List<Long> getAdminIdList(@Param("resourceId") Long resourceId);
}
