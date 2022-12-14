package site.mizhuo.marry.portal.config;

import org.springframework.context.annotation.Configuration;
import site.mizhuo.marry.config.BaseSwaggerConfig;
import site.mizhuo.marry.domain.SwaggerProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 * @author mizhuo
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.macro.mall.portal.controller")
                .title("marry-app首页")
                .description("marry-app首页相关接口文档")
                .contactName("mizhuo")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
