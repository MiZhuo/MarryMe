package site.mizhuo.marry.portal.service;

import site.mizhuo.marry.common.CommonResult;
import site.mizhuo.marry.domain.UserDto;
import site.mizhuo.marry.portal.domain.UserGroup;
import site.mizhuo.marry.portal.domain.UserInfo;

/**
 * 用户管理Service
 * @author mizhuo
 */
public interface UserService {

    /**
     * 获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    UserDto loadUserByUsername(String username);
    
    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    UserInfo getByUsername(String username);

    /**
     * 根据编号获取用户信息
     * @param id 用户ID
     * @return 用户信息
     */
    UserInfo getById(Long id);

    /**
     * 生成验证码
     * @param telephone 手机号
     * @return 验证码
     */
    String generateAuthCode(String telephone);

    /**
     * 登录后获取token
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    CommonResult<?> login(String username, String password);

    /**
     * 获取用户组信息
     * @param user 用户信息
     * @return 用户组信息
     */
    UserGroup getUserGroupInfo(UserInfo user);
}
