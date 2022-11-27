package site.mizhuo.marry.portal.service;

import site.mizhuo.marry.portal.domain.UserInfo;

/**
 /**
 * 用户信息缓存业务类
 * @author mizhuo
 */
public interface UserCacheService {
    /**
     * 删除用户用户缓存
     * @param userId 用户ID
     */
    void delUser(Long userId);

    /**
     * 获取用户用户缓存
     * @param userId 用户ID
     * @return 用户信息
     */
    UserInfo getUser(Long userId);

    /**
     * 设置用户用户缓存
     * @param user 用户信息
     */
    void setUser(UserInfo user);

    /**
     * 设置验证码
     * @param telephone 手机号
     * @param authCode 验证码
     */
    void setAuthCode(String telephone, String authCode);

    /**
     * 获取验证码
     * @param telephone 手机号
     * @return 验证码
     */
    String getAuthCode(String telephone);
}
