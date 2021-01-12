package com.cn.greemes.modules.mes.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.greemes.common.util.JwtTokenUtil;
import com.cn.greemes.domain.AdminUserDetails;
import com.cn.greemes.dto.MyMesAdminParam;
import com.cn.greemes.dto.UpdateAdminPasswordParam;
import com.cn.greemes.except.Asserts;
import com.cn.greemes.modules.mes.mapper.MesAdminLoginLogMapper;
import com.cn.greemes.modules.mes.mapper.MesResourceMapper;
import com.cn.greemes.modules.mes.mapper.MesRoleMapper;
import com.cn.greemes.modules.mes.model.*;
import com.cn.greemes.modules.mes.mapper.MesAdminMapper;
import com.cn.greemes.modules.mes.service.MesAdminCacheService;
import com.cn.greemes.modules.mes.service.MesAdminRoleRelationService;
import com.cn.greemes.modules.mes.service.MesAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author zbb
 * @since 2021-01-11
 */
@Service
public class MesAdminServiceImpl extends ServiceImpl<MesAdminMapper, MesAdmin> implements MesAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MesAdminServiceImpl.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MesAdminLoginLogMapper loginLogMapper;
    @Autowired
    private MesAdminCacheService adminCacheService;
    @Autowired
    private MesAdminRoleRelationService adminRoleRelationService;
    @Autowired
    private MesRoleMapper roleMapper;
    @Autowired
    private MesResourceMapper resourceMapper;

    @Override
    public MesAdmin getAdminByUsername(String username) {
        MesAdmin admin = adminCacheService.getAdmin(username);
        if(admin!=null) {
            return  admin;
        }
        QueryWrapper<MesAdmin> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(MesAdmin::getUsername,username);
        List<MesAdmin> adminList = list(wrapper);
        if (adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
            adminCacheService.setAdmin(admin);
            return admin;
        }
        return null;
    }

    @Override
    public MesAdmin register(MyMesAdminParam umsAdminParam) {
        MesAdmin umsAdmin = new MesAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        QueryWrapper<MesAdmin> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(MesAdmin::getUsername,umsAdmin.getUsername());
        List<MesAdmin> umsAdminList = list(wrapper);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        baseMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                Asserts.fail("密码不正确");
            }
            if(!userDetails.isEnabled()){
                Asserts.fail("帐号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
//            updateLoginTimeByUsername(username);
            insertLoginLog(username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 添加登录记录
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        MesAdmin admin = getAdminByUsername(username);
        if(admin==null) return;
        MesAdminLoginLog loginLog = new MesAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        loginLogMapper.insert(loginLog);
    }

    /**
     * 根据用户名修改登录时间
     */
    private void updateLoginTimeByUsername(String username) {
        MesAdmin record = new MesAdmin();
        record.setLoginTime(new Date());
        QueryWrapper<MesAdmin> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(MesAdmin::getUsername,username);
        update(record,wrapper);
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public Page<MesAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
        Page<MesAdmin> page = new Page<>(pageNum,pageSize);
        QueryWrapper<MesAdmin> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<MesAdmin> lambda = wrapper.lambda();
        if(StrUtil.isNotEmpty(keyword)){
            lambda.like(MesAdmin::getUsername,keyword);
            lambda.or().like(MesAdmin::getNickName,keyword);
        }
        return page(page,wrapper);
    }

    @Override
    public boolean update(Long id, MesAdmin admin) {
        admin.setId(id);
        MesAdmin rawAdmin = getById(id);
        if(rawAdmin.getPassword().equals(admin.getPassword())){
            //与原加密密码相同的不需要修改
            admin.setPassword(null);
        }else{
            //与原加密密码不同的需要加密修改
            if(StrUtil.isEmpty(admin.getPassword())){
                admin.setPassword(null);
            }else{
                admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            }
        }
        boolean success = updateById(admin);
        adminCacheService.delAdmin(id);
        return success;
    }

    @Override
    public boolean delete(Long id) {
        adminCacheService.delAdmin(id);
        boolean success = removeById(id);
        adminCacheService.delResourceList(id);
        return success;
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        QueryWrapper<MesAdminRoleRelation> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(MesAdminRoleRelation::getAdminId,adminId);
        adminRoleRelationService.remove(wrapper);
        //建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<MesAdminRoleRelation> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                MesAdminRoleRelation roleRelation = new MesAdminRoleRelation();
                roleRelation.setAdminId(adminId);
                roleRelation.setRoleId(roleId);
                list.add(roleRelation);
            }
            adminRoleRelationService.saveBatch(list);
        }
        adminCacheService.delResourceList(adminId);
        return count;
    }

    @Override
    public List<MesRole> getRoleList(Long adminId) {
        return roleMapper.getRoleList(adminId);
    }

    @Override
    public List<MesResource> getResourceList(Long adminId) {
        List<MesResource> resourceList = adminCacheService.getResourceList(adminId);
        if(CollUtil.isNotEmpty(resourceList)){
            return  resourceList;
        }
        resourceList = resourceMapper.getResourceList(adminId);
        if(CollUtil.isNotEmpty(resourceList)){
            adminCacheService.setResourceList(adminId,resourceList);
        }
        return resourceList;
    }

    @Override
    public int updatePassword(UpdateAdminPasswordParam param) {
        if(StrUtil.isEmpty(param.getUsername())
                ||StrUtil.isEmpty(param.getOldPassword())
                ||StrUtil.isEmpty(param.getNewPassword())){
            return -1;
        }
        QueryWrapper<MesAdmin> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(MesAdmin::getUsername,param.getUsername());
        List<MesAdmin> adminList = list(wrapper);
        if(CollUtil.isEmpty(adminList)){
            return -2;
        }
        MesAdmin umsAdmin = adminList.get(0);
        if(!passwordEncoder.matches(param.getOldPassword(),umsAdmin.getPassword())){
            return -3;
        }
        umsAdmin.setPassword(passwordEncoder.encode(param.getNewPassword()));
        updateById(umsAdmin);
        adminCacheService.delAdmin(umsAdmin.getId());
        return 1;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        //获取用户信息
        MesAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            List<MesResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin,resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
