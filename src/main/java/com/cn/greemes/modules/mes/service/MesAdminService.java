package com.cn.greemes.modules.mes.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.greemes.dto.MyMesAdminParam;
import com.cn.greemes.dto.UpdateAdminPasswordParam;
import com.cn.greemes.modules.mes.model.MesAdmin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.greemes.modules.mes.model.MesResource;
import com.cn.greemes.modules.mes.model.MesRole;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author zbb
 * @since 2021-01-11
 */
public interface MesAdminService extends IService<MesAdmin> {
    /**
     * 根据用户名获取后台管理员
     */
    MesAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     */
    MesAdmin register(MyMesAdminParam umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username,String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户名或昵称分页查询用户
     */
    Page<MesAdmin> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     */
    boolean update(Long id, MesAdmin admin);

    /**
     * 删除指定用户
     */
    boolean delete(Long id);

    /**
     * 修改用户角色关系
     */
    @Transactional
    int updateRole(Long adminId, List<Long> roleIds);

    /**
     * 获取用户对于角色
     */
    List<MesRole> getRoleList(Long adminId);

    /**
     * 获取指定用户的可访问资源
     */
    List<MesResource> getResourceList(Long adminId);

    /**
     * 修改密码
     */
    int updatePassword(UpdateAdminPasswordParam updatePasswordParam);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);
}
