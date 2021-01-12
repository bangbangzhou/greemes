package com.cn.greemes.config;



import com.cn.greemes.common.baseconfig.BaseRedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Redis配置类
 *
 */
@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig {

}
