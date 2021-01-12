package com.cn.greemes.config;



import com.cn.greemes.common.baseconfig.BaseSwaggerConfig;
import com.cn.greemes.domain.MesSwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 *
 */
@Configuration
@EnableSwagger2
public class MesSwaggerConfig  extends BaseSwaggerConfig {

    @Override
    public MesSwaggerProperties swaggerProperties() {
        return MesSwaggerProperties.builder()
                .apiBasePackage("com.cn.greemes.modules")
                .title("mymes项目骨架")
                .description("mymes项目骨架相关接口文档")
                .contactName("zbb")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
