package site.mizhuo.marry.portal.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import site.mizhuo.marry.config.BaseRedisConfig;

/**
 * Redis相关配置
 * @author mizhuo
 */
@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig {

}
