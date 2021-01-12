package com.cn.greemes.config;

import com.cn.greemes.common.baseconfig.BaseSecurityConfig;
import com.cn.greemes.component.MesDynamicSecurityService;
import com.cn.greemes.modules.mes.model.MesResource;
import com.cn.greemes.modules.mes.service.MesAdminService;
import com.cn.greemes.modules.mes.service.MesResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * mall-security模块相关配置
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MesSecurityConfig  extends BaseSecurityConfig {

    @Autowired
    private MesAdminService adminService;
    @Autowired
    private MesResourceService resourceService;

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> adminService.loadUserByUsername(username);
    }

    @Bean
    public MesDynamicSecurityService dynamicSecurityService() {
        return new MesDynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
                List<MesResource> resourceList = resourceService.list();
                for (MesResource resource : resourceList) {
                    map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
                }
                return map;
            }
        };
    }
}
