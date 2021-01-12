package com.cn.greemes.component;/*
 *Created by zbb on 2021/1/11
 **/

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;
/**
 * 动态权限相关业务类
 * 它是我自定义的一个动态权限业务接口 其主要用于加载所有的后台资源规则
 */
public interface MesDynamicSecurityService {
    /**
     * 加载资源ANT通配符和资源对应MAP
     */
    Map<String, ConfigAttribute> loadDataSource();
}
