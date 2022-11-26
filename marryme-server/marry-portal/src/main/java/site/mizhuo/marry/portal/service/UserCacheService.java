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
     * @param UserId
     */
    void delUser(Long UserId);

    /**
     * 获取用户用户缓存
     * @param UserId
     * @return
     */
    UserInfo getUser(Long UserId);

    /**
     * 设置用户用户缓存
     * @param User
     */
    void setUser(UserInfo User);

    /**
     * 设置验证码
     * @param telephone
     * @param authCode
     */
    void setAuthCode(String telephone, String authCode);

    /**
     * 获取验证码
     * @param telephone
     * @return
     */
    String getAuthCode(String telephone);
}
