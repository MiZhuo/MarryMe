package site.mizhuo.marry.auth.config;

import org.springframework.context.annotation.Configuration;
import site.mizhuo.marry.config.BaseSwaggerConfig;
import site.mizhuo.marry.domain.SwaggerProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 *
 * @author mizhuo
 * @date 2022/11/03
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("site.mizhuo.marry.controller")
                .title("marry认证中心")
                .description("marry认证中心相关接口文档")
                .contactName("mizhuo")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
