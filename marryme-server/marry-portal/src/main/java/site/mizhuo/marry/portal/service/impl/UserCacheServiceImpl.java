package site.mizhuo.marry.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import site.mizhuo.marry.annotation.CacheException;
import site.mizhuo.marry.portal.domain.UserInfo;
import site.mizhuo.marry.portal.service.UserCacheService;
import site.mizhuo.marry.service.RedisService;

/**
 * UserCacheServiceImpl实现类
 * @author mizhuo
 */
@Service
public class UserCacheServiceImpl implements UserCacheService {

    @Autowired
    private RedisService redisService;

    @Value("${redis.database}")
    private String REDIS_DATABASE;

    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;

    @Value("${redis.expire.authCode}")
    private Long REDIS_EXPIRE_AUTH_CODE;

    @Value("${redis.key.user}")
    private String REDIS_KEY_USER;

    @Value("${redis.key.authCode}")
    private String REDIS_KEY_AUTH_CODE;

    @Override
    public void delUser(Long UserId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + UserId;
        redisService.del(key);
    }

    @Override
    public UserInfo getUser(Long UserId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + UserId;
        return (UserInfo) redisService.get(key);
    }

    @Override
    public void setUser(UserInfo User) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + User.getId();
        redisService.set(key, User, REDIS_EXPIRE);
    }

    @CacheException
    @Override
    public void setAuthCode(String telephone, String authCode) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_AUTH_CODE + ":" + telephone;
        redisService.set(key, authCode, REDIS_EXPIRE_AUTH_CODE);
    }

    @CacheException
    @Override
    public String getAuthCode(String telephone) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_AUTH_CODE + ":" + telephone;
        return (String) redisService.get(key);
    }
}
