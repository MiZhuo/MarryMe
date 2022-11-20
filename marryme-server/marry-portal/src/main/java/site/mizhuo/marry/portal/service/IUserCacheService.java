package site.mizhuo.marry.portal.service;

import site.mizhuo.marry.portal.domain.UserInfo;

/**
 * 会员信息缓存业务类
 *
 * @author macro
 * @date 2020/3/14
 */
public interface IUserCacheService {
    /**
     * 删除会员用户缓存
     */
    void delMember(Long memberId);

    /**
     * 获取会员用户缓存
     */
    UserInfo getMember(Long memberId);

    /**
     * 设置会员用户缓存
     */
    void setMember(UserInfo member);

    /**
     * 设置验证码
     */
    void setAuthCode(String telephone, String authCode);

    /**
     * 获取验证码
     */
    String getAuthCode(String telephone);
}
